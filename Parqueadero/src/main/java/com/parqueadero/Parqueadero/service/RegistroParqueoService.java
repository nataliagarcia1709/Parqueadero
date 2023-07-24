package com.parqueadero.Parqueadero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parqueadero.Parqueadero.entity.Parqueadero;
import com.parqueadero.Parqueadero.entity.RegistroParqueo;
import com.parqueadero.Parqueadero.entity.Vehiculo;
import com.parqueadero.Parqueadero.exception.FormatoInvalidoException;
import com.parqueadero.Parqueadero.exception.RegistroExistenteException;
import com.parqueadero.Parqueadero.exception.RegistroParqueoException;
import com.parqueadero.Parqueadero.repository.RegistroParqueoRepository;
import com.parqueadero.Parqueadero.repository.VehiculoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroParqueoService {

    @Autowired
    private RegistroParqueoRepository registroParqueoRepository;
    private VehiculoRepository vehiculoRepository;

    // Método para registrar el ingreso de un vehículo a un parqueadero
    public RegistroParqueo registrarIngreso(Vehiculo vehiculo, Parqueadero parqueadero) {
        
    	// Validar que la placa tenga 6 caracteres y no contenga la letra "ñ"
        String placa = vehiculo.getPlaca();
        if (placa.length() != 6 || placa.toLowerCase().contains("ñ")) {
            throw new FormatoInvalidoException("El formato de la placa es inválido, debe tener 6 caracteres sin incluir la ñ.");
        }
     // Validar si el vehículo ya está registrado en algún parqueadero 
    	List<RegistroParqueo> registrosExistentes = registroParqueoRepository.findByVehiculo(vehiculo);
        if (!registrosExistentes.isEmpty()) {
            throw new RegistroExistenteException("No se puede Registrar Ingreso, ya existe la placa en este u otro parqueadero");
        }

        // Validar la capacidad del parqueadero antes de registrar el ingreso
        int capacidadDisponible = parqueadero.getCapacidad() - registroParqueoRepository.countByParqueaderoAndFechaHoraSalidaIsNull(parqueadero);
        if (capacidadDisponible <= 0) {
            throw new RegistroParqueoException("No se puede Registrar Ingreso, el parqueadero está lleno");
        }

        RegistroParqueo registro = new RegistroParqueo();
        registro.setVehiculo(vehiculo);
        registro.setParqueadero(parqueadero);
        registro.setFechaHoraIngreso(LocalDateTime.now());

        return registroParqueoRepository.save(registro);
    }

    // Método para registrar la salida de un vehículo de un parqueadero
    public void registrarSalida(Vehiculo vehiculo, Parqueadero parqueadero) {
        RegistroParqueo registro = registroParqueoRepository.findByVehiculoAndParqueaderoAndFechaHoraSalidaIsNull(vehiculo, parqueadero);
        if (registro == null) {
            throw new RegistroParqueoException("No se puede Registrar Salida, no existe la placa en el parqueadero");
        }

        registro.setFechaHoraSalida(LocalDateTime.now());
        registroParqueoRepository.save(registro);
    }

    // Método para obtener las ganancias de hoy, esta semana, este mes y este año de un parqueadero en específico
    public BigDecimal obtenerGananciasPorPeriodo(Parqueadero parqueadero, LocalDate fechaInicio, LocalDate fechaFin) {
        List<RegistroParqueo> registros = registroParqueoRepository.findByParqueaderoAndFechaHoraIngresoBetween(parqueadero, fechaInicio.atStartOfDay(), fechaFin.atStartOfDay().plusDays(1));
        BigDecimal totalGanancias = BigDecimal.ZERO;

        for (RegistroParqueo registro : registros) {
            if (registro.getFechaHoraSalida() != null) {
                // Calcular el tiempo de estancia y aplicar el costo por hora del parqueadero
                long tiempoEstancia = registro.getFechaHoraSalida().toLocalDate().toEpochDay() - registro.getFechaHoraIngreso().toLocalDate().toEpochDay();
                BigDecimal tiempoEstanciaBigDecimal = BigDecimal.valueOf(tiempoEstancia);
                BigDecimal ganancias = parqueadero.getCostoHora().multiply(tiempoEstanciaBigDecimal);
                totalGanancias = totalGanancias.add(ganancias);
            }
        }

        return totalGanancias;
    }

    // Método para obtener los vehículos más registrados en todos los parqueaderos
    public List<Vehiculo> obtenerTop10VehiculosMasRegistrados() {
        return registroParqueoRepository.findTop10VehiculosMasRegistrados();
    }

    // Método para buscar vehículos parqueados mediante coincidencia en la placa
    public List<Vehiculo> buscarVehiculosPorPlaca(String placa) {
        return vehiculoRepository.buscarVehiculoPorPlaca(placa);
    }

    // Otros métodos y lógica según tus necesidades
}
package com.parqueadero.Parqueadero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parqueadero.Parqueadero.dto.VehiculoIndicadorResponse;
import com.parqueadero.Parqueadero.entity.Vehiculo;
import com.parqueadero.Parqueadero.repository.RegistroParqueoRepository;
import com.parqueadero.Parqueadero.repository.VehiculoRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class IndicadoresService {

    @Autowired
    private RegistroParqueoRepository registroParqueoRepository;
    private VehiculoRepository vehiculoRepository;

    // Método para obtener los 10 vehículos más registrados en los diferentes parqueaderos
    public List<VehiculoIndicadorResponse> obtenerTop10VehiculosMasRegistrados() {
        List<Vehiculo> resultados = registroParqueoRepository.findTop10VehiculosMasRegistrados();
        return mapearResultados(resultados);
    }

    // Método para obtener los 10 vehículos más registrados en un parqueadero específico
    public List<VehiculoIndicadorResponse> obtenerTop10VehiculosMasRegistradosPorParqueadero(Long idParqueadero) {
        List<Vehiculo> resultados = registroParqueoRepository.findTop10VehiculosMasRegistradosPorParqueadero(idParqueadero);
        return mapearResultados(resultados);
    }

    // Método para verificar qué vehículos están siendo registrados por primera vez en un parqueadero
    public List<VehiculoIndicadorResponse> obtenerVehiculosRegistradosPorPrimeraVezEnParqueadero(Long idParqueadero) {
        List<Vehiculo> resultados = registroParqueoRepository.findVehiculosRegistradosPorPrimeraVezEnParqueadero(idParqueadero);
        return mapearResultados(resultados);
    }

    // Método para buscar vehículos parqueados mediante coincidencia en la placa
    public List<Vehiculo> buscarVehiculosPorPlaca(String placa) {
    	
        return vehiculoRepository.buscarVehiculoPorPlaca(placa);
    }

    // Método privado para mapear los resultados obtenidos de las consultas
 // Método privado para mapear los resultados obtenidos de las consultas
    private List<VehiculoIndicadorResponse> mapearResultados(List<Vehiculo> resultados) {
        List<VehiculoIndicadorResponse> response = new ArrayList<>();

        for (Vehiculo vehiculo : resultados) {
            Long vehiculoId = vehiculo.getId();
            Long vecesRegistrado = vehiculo.getVecesRegistrado();
            response.add(new VehiculoIndicadorResponse(vehiculoId, vecesRegistrado));
        }

        return response;
    }
}

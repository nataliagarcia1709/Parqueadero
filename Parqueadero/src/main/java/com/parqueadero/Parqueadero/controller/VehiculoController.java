package com.parqueadero.Parqueadero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.Parqueadero.entity.Parqueadero;
import com.parqueadero.Parqueadero.entity.Vehiculo;
import com.parqueadero.Parqueadero.exception.ElementoNoEncontradoException;
import com.parqueadero.Parqueadero.exception.FormatoInvalidoException;
import com.parqueadero.Parqueadero.exception.RegistroExistenteException;
import com.parqueadero.Parqueadero.service.RegistroParqueoService;
import com.parqueadero.Parqueadero.service.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;
    private final RegistroParqueoService registroParqueoService;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService, RegistroParqueoService registroParqueoService) {
		this.vehiculoService = vehiculoService;
		this.registroParqueoService = registroParqueoService;
	}
	// Endpoint para registrar el ingreso de un vehículo
    @PostMapping("/ingresos")
    public ResponseEntity<Vehiculo> registrarIngreso(@RequestBody Vehiculo vehiculo, Parqueadero parqueadero) {
        try {
            registroParqueoService.registrarIngreso(vehiculo, parqueadero);
            return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
        } catch (FormatoInvalidoException | RegistroExistenteException e) {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para registrar la salida de un vehículo
    @PostMapping("/salidas")
    public ResponseEntity<String> registrarSalida(@RequestParam Vehiculo vehiculo, Parqueadero parqueadero) {
        try {
            registroParqueoService.registrarSalida(vehiculo, parqueadero);
            return new ResponseEntity<>("Salida registrada", HttpStatus.OK);
        } catch (ElementoNoEncontradoException e) {
            // Manejar excepción si el vehículo no se encontró en el parqueadero
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener el listado de vehículos parqueados en un parqueadero
    @GetMapping("/{parqueaderoId}/vehiculos")
    public ResponseEntity<List<Vehiculo>> obtenerVehiculosParqueados(@PathVariable Long parqueaderoId) {
        List<Vehiculo> vehiculosParqueados = vehiculoService.obtenerVehiculosPorParqueaderoId(parqueaderoId);
        return new ResponseEntity<>(vehiculosParqueados, HttpStatus.OK);
    }

}
package com.parqueadero.Parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.Parqueadero.dto.VehiculoIndicadorResponse;
import com.parqueadero.Parqueadero.entity.Vehiculo;
import com.parqueadero.Parqueadero.service.IndicadoresService;

import java.util.List;

@RestController
@RequestMapping("/indicadores")
public class IndicadoresController {

    @Autowired
    private IndicadoresService indicadoresService;

    @GetMapping("/top-10-vehiculos-mas-registrados")
    public ResponseEntity<List<VehiculoIndicadorResponse>> obtenerTop10VehiculosMasRegistrados() {
        List<VehiculoIndicadorResponse> top10Vehiculos = indicadoresService.obtenerTop10VehiculosMasRegistrados();
        return ResponseEntity.ok(top10Vehiculos);
    }

    @GetMapping("/top-10-vehiculos-mas-registrados-por-parqueadero/{idParqueadero}")
    public ResponseEntity<List<VehiculoIndicadorResponse>> obtenerTop10VehiculosMasRegistradosPorParqueadero(@PathVariable Long idParqueadero) {
        List<VehiculoIndicadorResponse> top10VehiculosPorParqueadero = indicadoresService.obtenerTop10VehiculosMasRegistradosPorParqueadero(idParqueadero);
        return ResponseEntity.ok(top10VehiculosPorParqueadero);
    }

    @GetMapping("/vehiculos-por-primera-vez-en-parqueadero/{idParqueadero}")
    public ResponseEntity<List<VehiculoIndicadorResponse>> obtenerVehiculosRegistradosPorPrimeraVezEnParqueadero(@PathVariable Long idParqueadero) {
        List<VehiculoIndicadorResponse> vehiculosPorPrimeraVez = indicadoresService.obtenerVehiculosRegistradosPorPrimeraVezEnParqueadero(idParqueadero);
        return ResponseEntity.ok(vehiculosPorPrimeraVez);
    }

    @GetMapping("/buscar-vehiculos-por-placa/{placa}")
    public ResponseEntity<List<Vehiculo>> buscarVehiculosPorPlaca(@PathVariable String placa) {
        List<Vehiculo> vehiculos = indicadoresService.buscarVehiculosPorPlaca(placa);
        return ResponseEntity.ok(vehiculos);
    }
}

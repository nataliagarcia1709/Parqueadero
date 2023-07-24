package com.parqueadero.Parqueadero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parqueadero.Parqueadero.entity.Vehiculo;
import com.parqueadero.Parqueadero.repository.VehiculoRepository;

@Service
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> obtenerVehiculosPorParqueaderoId(Long parqueaderoId) {
        return vehiculoRepository.findByParqueaderoId(parqueaderoId);
    }

    public List<Vehiculo> obtenerVehiculosParqueados() {
        return vehiculoRepository.findByFechaHoraSalidaIsNull();
    }
    
    public Vehiculo findVehiculoByPlacaAndParqueaderoId(String placa, Long parqueaderoId) {
        return vehiculoRepository.findByPlacaAndParqueaderoId(placa, parqueaderoId);
    }

}

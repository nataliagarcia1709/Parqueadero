package com.parqueadero.Parqueadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueadero.Parqueadero.entity.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

	List<Vehiculo> findByParqueaderoId(Long parqueaderoId);

	List<Vehiculo> findByFechaHoraSalidaIsNull();
	
	List<Vehiculo> buscarVehiculoPorPlaca(String placa);

	Vehiculo findByPlacaAndParqueaderoId(String placa, Long parqueaderoId);
	
	boolean existsByPlacaAndParqueaderoId(String placa, Long parqueaderoId);
	
}

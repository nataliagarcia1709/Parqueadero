package com.parqueadero.Parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueadero.Parqueadero.entity.Parqueadero;

@Repository
public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Long> {
	Parqueadero findByNombre(String nombre);
}

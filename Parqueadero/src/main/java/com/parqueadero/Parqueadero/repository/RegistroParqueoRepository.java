package com.parqueadero.Parqueadero.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parqueadero.Parqueadero.entity.Parqueadero;
import com.parqueadero.Parqueadero.entity.RegistroParqueo;
import com.parqueadero.Parqueadero.entity.Vehiculo;

@Repository
public interface RegistroParqueoRepository extends JpaRepository<RegistroParqueo, Long> {
    List<RegistroParqueo> findByVehiculo(Vehiculo vehiculo);
    List<RegistroParqueo> findByParqueadero(Parqueadero parqueadero);
    RegistroParqueo findByVehiculoAndParqueaderoAndFechaHoraSalidaIsNull(Vehiculo vehiculo, Parqueadero parqueadero);
    List<RegistroParqueo> findByParqueaderoAndFechaHoraIngresoBetween(Parqueadero parqueadero, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin);

    @Query("SELECT rp.vehiculo, COUNT(rp.id) AS vecesRegistrado " +
           "FROM RegistroParqueo rp " +
           "GROUP BY rp.vehiculo " +
           "ORDER BY vecesRegistrado DESC")
    int countByParqueaderoAndFechaHoraSalidaIsNull(Parqueadero parqueadero);
	// Consulta para obtener los 10 vehículos más registrados en los diferentes parqueaderos
    @Query("SELECT rp.vehiculo.id, COUNT(rp.vehiculo.id) FROM RegistroParqueo rp GROUP BY rp.vehiculo.id ORDER BY COUNT(rp.vehiculo.id) DESC")
    List<Vehiculo> findTop10VehiculosMasRegistrados();

    // Consulta para obtener los 10 vehículos más registrados en un parqueadero específico
    @Query("SELECT rp.vehiculo.id, COUNT(rp.vehiculo.id) FROM RegistroParqueo rp WHERE rp.parqueadero.id = :idParqueadero GROUP BY rp.vehiculo.id ORDER BY COUNT(rp.vehiculo.id) DESC")
    List<Vehiculo> findTop10VehiculosMasRegistradosPorParqueadero(@Param("idParqueadero") Long idParqueadero);

    // Consulta para verificar qué vehículos están siendo registrados por primera vez en un parqueadero
    @Query("SELECT rp.vehiculo.id, COUNT(rp.vehiculo.id) FROM RegistroParqueo rp WHERE rp.parqueadero.id = :idParqueadero GROUP BY rp.vehiculo.id HAVING COUNT(rp.vehiculo.id) = 1")
    List<Vehiculo> findVehiculosRegistradosPorPrimeraVezEnParqueadero(@Param("idParqueadero") Long idParqueadero);

   

}
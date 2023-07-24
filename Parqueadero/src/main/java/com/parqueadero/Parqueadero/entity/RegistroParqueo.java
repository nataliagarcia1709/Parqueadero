package com.parqueadero.Parqueadero.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_parqueo")
public class RegistroParqueo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_parqueadero")
    private Parqueadero parqueadero;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaHoraIngreso;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaHoraSalida;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parqueadero getParqueadero() {
		return parqueadero;
	}

	public void setParqueadero(Parqueadero parqueadero) {
		this.parqueadero = parqueadero;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getFechaHoraIngreso() {
		return fechaHoraIngreso;
	}

	public void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
		this.fechaHoraIngreso = fechaHoraIngreso;
	}

	public LocalDateTime getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public RegistroParqueo() {
		
	}

	public RegistroParqueo(Long id, Parqueadero parqueadero, Vehiculo vehiculo, LocalDateTime fechaHoraIngreso,
			LocalDateTime fechaHoraSalida) {
		this.id = id;
		this.parqueadero = parqueadero;
		this.vehiculo = vehiculo;
		this.fechaHoraIngreso = fechaHoraIngreso;
		this.fechaHoraSalida = fechaHoraSalida;
	}

    
}

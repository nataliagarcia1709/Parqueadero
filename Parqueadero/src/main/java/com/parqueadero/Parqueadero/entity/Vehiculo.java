package com.parqueadero.Parqueadero.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 6)
	private String placa;

	@Column(nullable = false)
	private LocalDateTime fechaHoraIngreso;

	@Column
	private LocalDateTime fechaHoraSalida;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parqueadero_id", nullable = false)
	private Parqueadero parqueadero;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	 @Column(nullable = false)
	    private Long vecesRegistrado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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

	public Parqueadero getParqueadero() {
		return parqueadero;
	}

	public void setParqueadero(Parqueadero parqueadero) {
		this.parqueadero = parqueadero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getVecesRegistrado() {
		return vecesRegistrado;
	}

	public void setVecesRegistrado(Long vecesRegistrado) {
		this.vecesRegistrado = vecesRegistrado;
	}

	public Vehiculo() {
	}

	public Vehiculo(Long id, String placa, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida,
			Parqueadero parqueadero, Usuario usuario, Long vecesRegistrado) {
		super();
		this.id = id;
		this.placa = placa;
		this.fechaHoraIngreso = fechaHoraIngreso;
		this.fechaHoraSalida = fechaHoraSalida;
		this.parqueadero = parqueadero;
		this.usuario = usuario;
		this.vecesRegistrado = vecesRegistrado;
	}

	public Long getParqueaderoId() {
		return parqueadero != null ? parqueadero.getId() : null;
	}

	public void setParqueaderoId(Long parqueaderoId) {
		this.parqueadero = new Parqueadero();
		this.parqueadero.setId(parqueaderoId);
	}
}

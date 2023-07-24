package com.parqueadero.Parqueadero.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Parqueadero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private int capacidad;

	@Column(nullable = false)
	private BigDecimal costoHora;

	@ManyToMany(mappedBy = "parqueaderos")
	private List<Usuario> socios = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public BigDecimal getCostoHora() {
		return costoHora;
	}

	public void setCostoHora(BigDecimal costoHora) {
		this.costoHora = costoHora;
	}

	public List<Usuario> getSocios() {
		return socios;
	}

	public void setSocios(List<Usuario> socios) {
		this.socios = socios;
	}

	public Parqueadero() {
	}

	public Parqueadero(Long id, String nombre, int capacidad, BigDecimal costoHora, List<Usuario> socios) {
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.costoHora = costoHora;
		this.socios = socios;
	}
}

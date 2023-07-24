package com.parqueadero.Parqueadero.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.JoinColumn;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	private String token;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_parqueadero", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "parqueadero_id") })
	private List<Parqueadero> parqueaderos = new ArrayList<>();

	public static final String ROL_SOCIO = "SOCIO";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Parqueadero> getParqueaderos() {
		return parqueaderos;
	}

	public void setParqueaderos(List<Parqueadero> parqueaderos) {
		this.parqueaderos = parqueaderos;
	}

	public Usuario() {

	}

	public Usuario(Long id, String email, String password, List<Role> roles, List<Parqueadero> parqueaderos) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.parqueaderos = parqueaderos;
	}

}
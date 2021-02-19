package com.uniovi.entities;

import javax.persistence.*;

@Entity
public class Professor {

	@Id
	@GeneratedValue
	private Long id;
	private String dni;
	private String name;
	private String surname;
	private String categoria;
	
	
	
	public Professor(Long id, String dni, String name, String surname, String categoria) {
		super();
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.categoria = categoria;
	}
	
	public Professor(){ }
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
}

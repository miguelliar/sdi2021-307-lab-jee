package com.uniovi.entities;

import javax.persistence.*;

@Entity
public class Professor {

	@Id
	@GeneratedValue
	private Long dni;
	private String name;
	private String surname;
	private String categoria;
	
	
	
	public Professor(Long dni, String name, String surname, String categoria) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.categoria = categoria;
	}
	

	
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
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



	@Override
	public String toString() {
		return "Professor [dni=" + dni + ", name=" + name + ", surname=" + surname + ", categoria=" + categoria + "]";
	}
	
	
	
}

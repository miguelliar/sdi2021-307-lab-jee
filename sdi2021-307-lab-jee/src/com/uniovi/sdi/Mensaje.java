package com.uniovi.sdi;

public class Mensaje {
	private String usuario;
	private String texto;
	
	public Mensaje() {
		
	}
	
	public Mensaje(String usuario, String texto) {
		this.usuario = usuario;
		this.texto = texto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}

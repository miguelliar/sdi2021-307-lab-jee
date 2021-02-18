package com.uniovi.sdi;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class MensajesService {
	public List<Mensaje> getMensajes() {
		List<Mensaje> mensaje= new LinkedList<Mensaje>();
		ObjectContainer db= null;
		
		try {
			db= Db4oEmbedded.openFile("bdMensajes");
			List<Mensaje> respuesta= db.queryByExample(Mensaje.class);
			// NO RETORNAR LA MISMA LISTA DE LA RESPUESTA
			mensaje.addAll(respuesta);
			} 
		finally {
			db.close();
		}
		
		return mensaje;
		
	}
	
	public void setNuevoMensaje(Mensaje mensaje) {
		ObjectContainer db= null;
		try { 
			db= Db4oEmbedded.openFile("bdMensajes");
			db.store(mensaje);
		} finally {
			db.close();
		}
	}
}

package com.uniovi.sdi;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mensajes")
public class ServletMensajes  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ServletMensajes() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		// No hay productos, creamos uno y lo insertamos en sesi�n
		
		List<Mensaje> mensaje = new MensajesService().getMensajes();
		request.getSession().setAttribute("mensajesPublicados", mensaje);
		
		getServletContext().getRequestDispatcher("/vista-mensajes.jsp").forward(request, response);
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

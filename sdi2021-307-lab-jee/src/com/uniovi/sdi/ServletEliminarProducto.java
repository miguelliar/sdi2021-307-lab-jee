package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/eliminarDelCarrito")
public class ServletEliminarProducto extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ServletEliminarProducto() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		HashMap<String,Integer> carrito = 
				(HashMap<String,Integer>) request.getSession().getAttribute("carrito");
		
		String producto = request.getParameter("producto");
		if ( carrito.containsKey(producto)){
			eliminarDelCarrito(carrito, producto);
		}
		
		// Retornar la vista con parámetro "carrito"
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);
	}
	
	private void eliminarDelCarrito(HashMap<String, Integer> carrito, String claveProducto) {
		int elements = carrito.get(claveProducto);
		if ( elements > 1) {
			carrito.replace(claveProducto, elements - 1);
		} else {
			carrito.remove(claveProducto);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

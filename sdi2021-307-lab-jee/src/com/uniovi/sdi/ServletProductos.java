package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/productos")
public class ServletProductos extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ServletProductos() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		List<Producto> productos = 
				(List<Producto>) request.getSession().getAttribute("productosTienda");
		// No hay productos, creamos uno y lo insertamos en sesión
		if (productos == null) {
			productos = new ProductosService().getProductos();
			request.getSession().setAttribute("productosTienda", productos);
		}
		getServletContext().getRequestDispatcher("/vista-productos.jsp").forward(request, response);
	}
	
	private void insertarEnCarrito(HashMap<String, Integer> carrito, String claveProducto) {
		if (carrito.get(claveProducto) == null) {
			carrito.put(claveProducto, new Integer(1));
		} else  {
			int numeroArticulos=(Integer)carrito.get(claveProducto).intValue();
			carrito.put(claveProducto, new Integer(numeroArticulos) + 1);
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

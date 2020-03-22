package controlador;

import modelo.ProductoPOJO;
import modelo.Producto_DAO_Imp;
import modelo.RegistroVentasDAO_Imp;
import vista.Mensajes;

public class Operaciones {
	private Producto_DAO_Imp prod = new Producto_DAO_Imp();
	private Mensajes mensaje = new Mensajes();
	private boolean activado = true;
	
	public void run()
	{
		mensaje.mensajeBienvenido();
		char userInput = 'q';
		
		while( activado )
		{
			mensaje.mensajeMenuInventario();
			userInput = mensaje.opcionChar();
			
			if( userInput == 'A' || userInput == 'a' )
			{
				prod.create( new ProductoPOJO( mensaje.getNombre(), mensaje.getClave(), mensaje.getDescripcion(), mensaje.getUnidad(),
						                   mensaje.getCantidad(), mensaje.getPrecio() ) );
			}
			if( userInput == 'M' || userInput == 'm' )
			{
				mensaje.mostrarProductos( prod.readAll() );
			}
			if( userInput == 'P' || userInput == 'p' )
			{
				mensaje.mostrarProducto( prod.readProducto( mensaje.getClave() ) );
			}
			if( userInput == 'B' || userInput == 'b' )
			{
				prod.delete( mensaje.getClave() );
			}
			if( userInput == 'I' || userInput == 'i' )
			{
				prod.incrementaInventario( mensaje.getClaveIncremento(), mensaje.mensajeGetCantidadIncremento() );
			}
			if( userInput == 'E' || userInput == 'e' )
			{
				String Clave = mensaje.getClave();
				prod.update( mensaje.getNombre(), Clave, mensaje.getDescripcion(), mensaje.getUnidad(), mensaje.getCantidad(), mensaje.getPrecio());
			}
			if( userInput == 'S' || userInput == 's' )
			{
				activado = false;
				mensaje.mensajeSalir();
			}
			if( userInput == 'V' || userInput == 'v' )
			{
				iniciarVentas( userInput, prod, mensaje );
			}
		}
	}
	
	private void iniciarVentas( char userInput_in, Producto_DAO_Imp sistemaProductos, Mensajes mensajes )
	{
		boolean ventasActivadas = true;
		RegistroVentasDAO_Imp ventas = new RegistroVentasDAO_Imp();
		mensaje.mensajeBienvenidoVentas();
		
		while( ventasActivadas )
		{
			mensaje.mensajeMenuVentas();
			userInput_in = mensaje.opcionChar();
		
			if( userInput_in == 'A' || userInput_in == 'a' )
			{
				ventas.agregarProducto( sistemaProductos.readProducto( mensajes.getClaveVentas() ), mensajes.getCantidadVentas() );
			}
			if( userInput_in == 'E' || userInput_in == 'e' )
			{
				ventas.eliminarProducto( mensajes.getClaveBorrarProductoVentas() );
			}
			if( userInput_in == 'M' || userInput_in == 'm' )
			{
				mensajes.mostrarCarritoVentas( ventas );
			}
			if( userInput_in == 'R' || userInput_in == 'r' )
			{
				ventas.realizarVenta();
				mensajes.mensajeVentaExitosa();
				mensajes.mensajeSistemaVentasCerrado();
				ventasActivadas = false;
				userInput_in = 'I';
			}
			if( userInput_in == 'C' || userInput_in == 'c' )
			{
				ventasActivadas = false;
				ventas.cancelarVenta();
				mensajes.mensajeVentaCancelada();
				mensajes.mensajeSistemaVentasCerrado();
				userInput_in = 'I';
			}
		}
	}
}

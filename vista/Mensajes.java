package vista;

import java.util.Iterator;
import java.util.List;
import modelo.ProductoPOJO;
import modelo.RegistroVentasDAO_Imp;
import modelo.VentaPOJO;

public class Mensajes {
	private Teclado teclado;
	
	public Mensajes()
	{
		teclado = new Teclado();
	}
	
	public void mensajeBienvenido()
	{
		System.out.println( "Bienvenidos al sistema de inventario." );
	}
	
	public void mensajeBienvenidoVentas()
	{
		System.out.println( "Sistema de ventas inicializado.\n" );
	}
	
	public void mensajeSalir()
	{
		System.out.println( "Hasta luego..." );
	}
	
	public int mensajeGetCantidadIncremento()
	{
		System.out.println( "Por cuanto quieres incrementar el inventario?" );
		return teclado.leerEntero();
	}
	
	public void mensajeSistemaVentasCerrado()
	{
		System.out.println( "El sistema de ventas se ha cerrado exitosamente." );
	}
	
	public void mensajeMenuInventario()
	{
		System.out.println( "( A )gregar producto, ( M )ostrar todo, ( P )oner un producto, ( I )ncrementar inventario, \n"
							 + "( B )orrar producto, ( E )ditar producto, ( V )entas, ( S )alir" );
	}
	
	public void mensajeMenuVentas()
	{
		System.out.println( "( A )gregar a carrito, ( E )liminar de carrito, ( M )ostrar articulos, ( R )ealizar venta, ( C )ancelar" );
	}
	
	public void mensajeVentaCancelada()
	{
		System.out.println( "Se cancelo exitosamente la venta.\n" );
	}
	
	public void mensajeVentaExitosa()
	{
		System.out.println( "Se realizo la venta exitosamente." );
	}
	
	public int opcionEntero()
	{
		System.out.println( "Introduce el numero de la opcion que desea.\n" );
		return teclado.leerEntero();
	}
	
	public char opcionChar()
	{
		System.out.println( "Introduce el character de la opcion que desea.\n" );
		return teclado.leerChar();
	}
	
	public String opcionString()
	{
		System.out.println( "Introduce la informacion que desea.\n" );
		return teclado.leerCadena();
	}
	
	public String getNombre()
	{
		System.out.println( "Introduce el nombre del producto." );
		return teclado.leerCadena();
	}
	
	public String getClave()
	{
		System.out.println( "Introduce la clave del producto." );
		return teclado.leerCadena();
	}
	
	public String getClaveVentas()
	{
		System.out.println( "Introduce la clave del producto que deseas comprar." );
		return teclado.leerCadena();
	}
	
	public String getClaveIncremento()
	{
		System.out.println( "Introduce la clave del producto al que deseas aumentar su cantidad." );
		return teclado.leerCadena();
	}
	public String getClaveBorrarProductoVentas()
	{
		System.out.println( "Introduce la clave del producto que deseas eliminar del carrito." );
		return teclado.leerCadena();
	}
	
	public String getDescripcion()
	{
		System.out.println( "Introduce la descripcion del producto." );
		return teclado.leerCadena();
	}
	
	public String getUnidad()
	{
		System.out.println( "Introduce el tipo de unidad." );
		return teclado.leerCadena();
	}
	
	public int getCantidad()
	{
		System.out.println( "Introduce la cantidad disponible." );
		return teclado.leerEntero();
	}
	
	public int getCantidadVentas()
	{
		System.out.println( "Introduce la cantidad que deseas comprar." );
		return teclado.leerEntero();
	}
	
	public int getPrecio()
	{
		System.out.println( "Introduce el precio por unidad." );
		return teclado.leerEntero();
	}
	
	public void mostrarProductos( List< ProductoPOJO > productos )
	{
		System.out.println( "Productos: \n" );
		Iterator< ProductoPOJO > iterator = productos.iterator();
		
		while( iterator.hasNext() )
		{
			ProductoPOJO temp = iterator.next();
			System.out.println( "Nombre: " + temp.nombre );
			System.out.println( "Clave: " + temp.clave );
			System.out.println( "Descripcion: " + temp.descripcion );
			System.out.println( "Unidad: " + temp.unidad );
			System.out.println( "Cantidad disponible: " + temp.cantidad );
			System.out.println( "Precio por unidad: " + temp.precio + "\n" );
		}
	}
	
	public void mostrarProducto( ProductoPOJO producto )
	{
		System.out.println( "Producto: \n" );
		System.out.println( "Nombre: " + producto.nombre );
		System.out.println( "Clave: " + producto.clave );
		System.out.println( "Descripcion: " + producto.descripcion );
		System.out.println( "Unidad: " + producto.unidad );
		System.out.println( "Cantidad disponible: " + producto.cantidad );
		System.out.println( "Precio por unidad: " + producto.precio + "\n" );
	}
	
	public void mostrarCarritoVentas( RegistroVentasDAO_Imp registro )
	{
		System.out.println( "Productos en Carrito de Ventas: \n" );
		List< VentaPOJO > ventas = registro.readAll();
		Iterator< VentaPOJO > iterator = ventas.iterator();
		
		while( iterator.hasNext() )
		{
			VentaPOJO temp = iterator.next();
			System.out.println( "Nombre: " + temp.nombreProducto );
			System.out.println( "Clave: " + temp.claveProducto );
			System.out.println( "Cantidad: " + temp.cantidad );
			System.out.println( "Precio Unitario: " + temp.precio + "\n" );
		}
		
		System.out.println( "Precio total: " +  registro.getTotal( ventas ) );
		System.out.println( "Precio total IVA: " +  registro.getTotalIVA( ventas ) );
	}
}

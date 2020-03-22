package modelo;

public class VentaPOJO {
	public String nombreProducto, claveProducto;
	public int cantidad, precio;
	
	public VentaPOJO()
	{
		nombreProducto = "";
		claveProducto = "";
		cantidad = 0;
		precio = 0;
	}
	public VentaPOJO( String Nombre_in, String Clave_in, int Cantidad_in, int Precio_in )
	{
		nombreProducto = Nombre_in;
		claveProducto = Clave_in;
		cantidad = Cantidad_in;
		precio = Precio_in;
	}
	public VentaPOJO( VentaPOJO venta )
	{
		nombreProducto = venta.nombreProducto;
		claveProducto = venta.claveProducto;
		cantidad = venta.cantidad;
		precio = venta.precio;
	}
}

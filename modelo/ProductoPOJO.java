package modelo;

public class ProductoPOJO {
	public String nombre, clave, descripcion, unidad;
	public int cantidad, precio;
	
	public ProductoPOJO()
	{
		nombre = "";
		clave = "";
		descripcion = "";
		unidad = "";
		cantidad = 0;
		precio = 0;
	}
	
	public ProductoPOJO( String nombre_in, String clave_in, String Descrip_in, String unidad_in, int cantidad_in, int precio_in )
	{
		nombre = nombre_in;
		clave = clave_in;
		descripcion = Descrip_in;
		unidad = unidad_in;
		cantidad = cantidad_in;
		precio = precio_in;
	}
	
	public ProductoPOJO( ProductoPOJO producto )
	{
		nombre = producto.nombre;
		clave = producto.clave;
		descripcion = producto.descripcion;
		unidad = producto.unidad;
		cantidad = producto.cantidad;
		precio = producto.precio;
	}
}

package modelo;

import java.util.List;

public interface Producto_DAO {
	public boolean create( ProductoPOJO producto );
	public List< ProductoPOJO > readAll();
	public ProductoPOJO readProducto( String Clave );
	public boolean update( String Nombre, String Clave, String Descripcion, String Unidad, int Cantidad, int Precio );
	public boolean delete( String Clave );
	public boolean incrementaInventario( String Clave, int cantidadIncremento);
}

package modelo;

import java.util.List;

public interface RegistroVentasDAO {
	public void agregarProducto( ProductoPOJO Producto, int cantidad );
	public void eliminarProducto( String clave );
	public List< VentaPOJO > readAll();
	public VentaPOJO readVenta( String clave );
	public void realizarVenta();
	public void cancelarVenta();
	public int getTotal( List< VentaPOJO > ventas );
	public int getTotalIVA( List< VentaPOJO > ventas );
}

package modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;

public class Producto_DAO_Imp implements Producto_DAO {
	
	public boolean create( ProductoPOJO producto )
	{
		boolean fueCreado = false;
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			Statement stmt = conex.con.createStatement();
			String qry = " INSERT INTO Productos( Nombre, Clave, Descripcion, Unidad, Cantidad, precio ) VALUES ( '"
						 + producto.nombre + "', '" + producto.clave + "', '" + producto.descripcion + "', '" + producto.unidad + "', '"
						 + producto.cantidad + "', '" + producto.precio + "' );";
			stmt.executeUpdate( qry );
			fueCreado = true;
		}
		catch( Exception e )
		{
			System.out.println( "Error al crear elemento." );
			e.printStackTrace();
		}
		
		conex.stopConection();
		return fueCreado;
	}
	
	public List< ProductoPOJO > readAll()
	{
		List< ProductoPOJO > productos = new ArrayList< ProductoPOJO >();
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			Statement stmt = conex.con.createStatement();
			ResultSet rs = stmt.executeQuery( "Select * from Productos" );
			
			while( rs.next() )
			{
				productos.add( new ProductoPOJO( rs.getString( 1 ), rs.getString( 2 ), rs.getString( 3 ), rs.getString( 4 ),
							                     rs.getInt( 5 ), rs.getInt( 6 ) ) );
			}
			
			rs.close();
			stmt.close();
		}
		catch( Exception e )
		{
			System.out.println( "No se pudo mostrar los productos." );
			e.printStackTrace();
		}
		
		conex.stopConection();
		return productos;
	}
	
	public ProductoPOJO readProducto( String Clave )
	{
		ProductoPOJO temp = new ProductoPOJO();
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			Statement stmt = conex.con.createStatement();
			ResultSet rs = stmt.executeQuery( "Select * from Productos WHERE Clave = '" + Clave + "';" );
			
			if( rs.next() )
			{
				temp = new ProductoPOJO( rs.getString( 1 ), rs.getString( 2 ), rs.getString( 3 ), rs.getString( 4 ), rs.getInt( 5 ), rs.getInt( 6 ) );
			}
			
			rs.close();
			stmt.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		conex.stopConection();
		return temp;
	}
	
	public boolean delete( String Clave )
	{
		boolean borrado = false;
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			Statement stmt = conex.con.createStatement();
			String query = " Delete from Productos Where Clave = " + Clave + ";";
			
			stmt.executeUpdate( query );
			System.out.println( "Producto eliminado." );
			borrado = true;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		conex.stopConection();
		return borrado;
	}
	
	public boolean update( String Nombre, String Clave, String Descripcion, String Unidad, int Cantidad, int Precio )
	{
		boolean cambiosHechos = false;
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			Statement stmt = conex.con.createStatement();
			String query = "UPDATE Productos SET Nombre = '" + Nombre + "', Descripcion = '" + Descripcion + "', Unidad = '"
							+ Unidad + "', Cantidad = '" + Cantidad + "', Precio = '" + Precio + "' WHERE Clave = '" 
							+ Clave + "';";
			
			stmt.executeUpdate(query);
			System.out.println( "El producto ha sido modificado." );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		conex.stopConection();
		return cambiosHechos;
	}
	
	public boolean incrementaInventario( String Clave, int cantidadIncremento )
	{
		boolean incrementoExitoso = false;
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			Statement stmt = conex.con.createStatement();
			String query = "UPDATE Productos SET Cantidad = Cantidad + " + cantidadIncremento + " WHERE Clave = '" 
							+ Clave + "';";
			
			stmt.executeUpdate(query);
			System.out.println( "El producto ha sido modificado." );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		conex.stopConection();
		return incrementoExitoso;
	}
}

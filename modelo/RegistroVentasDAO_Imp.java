package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class RegistroVentasDAO_Imp implements RegistroVentasDAO{
	private java.sql.Date fecha;
	private int folio;
	
	public RegistroVentasDAO_Imp()
	{
		Conexion conex = new Conexion();
		conex.startConection();
		
		java.util.Date fechaSimple = new java.util.Date();
		fecha = new java.sql.Date( fechaSimple.getTime() );
		
		try
		{
			Statement stmt = conex.con.createStatement();
			PreparedStatement ps = conex.con.prepareStatement( "INSERT INTO RegistroVentas( Fecha ) VALUES( ? );" );
			ps.setDate( 1, fecha );
			ps.executeUpdate();
			
			String qry = "SELECT * FROM RegistroVentas ORDER BY Folio DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery( qry );
			rs.next();
			folio = rs.getInt(1);
			
			ps.close();
			rs.close();
			stmt.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	public void agregarProducto( ProductoPOJO producto, int cantidad )
	{
		if( productoEstaDisponible( producto, cantidad ) )
		{
			Conexion conex = new Conexion();
			conex.startConection();
		
			try
			{
				Statement stmt = conex.con.createStatement();
				String qry = "INSERT INTO Ventas( Folio, Nombre, Clave, Cantidad, Precio ) VALUES ( " + folio + ",'" + producto.nombre 
							  + "', '" + producto.clave + "', " + cantidad + ", " + producto.precio + ");";
				stmt.executeUpdate( qry );
				stmt.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		
			conex.stopConection();
		}
		else
		{
			System.out.println( "No hay suficiente cantidad para cumplir tu demanda." );
		}
	}
	
	public void eliminarProducto( String clave )
	{
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			PreparedStatement ps = conex.con.prepareStatement( "DELETE FROM Ventas WHERE Folio = ? AND Clave = ?" );
			ps.setInt( 1, folio);
			ps.setString( 2, clave );
			ps.executeUpdate();
			ps.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		conex.stopConection();
	}
	
	public List< VentaPOJO > readAll()
	{
		List< VentaPOJO > ventas = new ArrayList< VentaPOJO >();
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			Statement stmt = conex.con.createStatement();
			ResultSet rs = stmt.executeQuery( "Select * from Ventas" );
			
			while( rs.next() )
			{
				ventas.add( new VentaPOJO( rs.getString( 2 ), rs.getString( 3 ), rs.getInt( 4 ), rs.getInt( 5 ) ) );
			}
			
			rs.close();
			stmt.close();
		}
		catch( Exception e )
		{
			System.out.println( "No se pudo conseguir los articulos de venta." );
			e.printStackTrace();
		}
		
		conex.stopConection();
		return ventas;
	}
	
	public VentaPOJO readVenta( String clave )
	{
		Conexion conex = new Conexion();
		conex.startConection();
		VentaPOJO  temp = new VentaPOJO();
		
		try
		{
			Statement stmt = conex.con.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Ventas WHERE Clave = " + clave + ";" );
			
			if( rs.next() )
			{
				temp =  new VentaPOJO( rs.getString( 2 ), rs.getString( 3 ), rs.getInt( 4 ), rs.getInt( 5 ) );
			}
			
			rs.close();
			stmt.close();
		}
		catch( Exception e )
		{
			System.out.println( "No se pudo conseguir los articulos de venta." );
			e.printStackTrace();
		}
		
		conex.stopConection();
		return temp;
	}
	
	public void realizarVenta()
	{
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			List< VentaPOJO > ventas = readAll();
			Iterator< VentaPOJO > iterator = ventas.iterator();
			PreparedStatement ps = conex.con.prepareStatement( "UPDATE Productos SET Cantidad = Cantidad - ? WHERE Clave = ?" );
			
			while( iterator.hasNext() )
			{
				VentaPOJO temp = iterator.next();
				ps.setInt( 1, temp.cantidad );
				ps.setString( 2, temp.claveProducto );
				ps.executeUpdate();
			}
			
			ps.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		conex.stopConection();
	}
	
	public int getTotal( List< VentaPOJO > ventas )
	{
		int total = 0;
		Iterator< VentaPOJO > iterator = ventas.iterator();
		while( iterator.hasNext() )
		{
			VentaPOJO temp = iterator.next();
			total += ( temp.cantidad * temp.precio );
		}
		
		return total;
	}
	
	public int getTotalIVA( List< VentaPOJO > ventas )
	{
		int totalIVA = getTotal( ventas );
		totalIVA *= 1.3;
		return totalIVA;
	}
	
	public void cancelarVenta()
	{
		Conexion conex = new Conexion();
		conex.startConection();
		
		try
		{
			PreparedStatement ps = conex.con.prepareStatement("DELETE FROM RegistroVentas WHERE Folio = ?");
			ps.setInt( 1, folio );
			ps.executeUpdate();
			
			ps = conex.con.prepareStatement( "DELETE FROM Ventas WHERE Folio = ?" );
			ps.setInt( 1, folio );
			ps.executeUpdate();
			
			ps.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		conex.stopConection();
	}
	
	public boolean productoEstaDisponible( ProductoPOJO producto, int cantidad )
	{
		boolean estaDisponible = false;
		if( producto.cantidad >= cantidad )
		{
				estaDisponible = true;
		}
		
		return estaDisponible;
	}
}

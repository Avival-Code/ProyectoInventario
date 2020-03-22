package vista;

import java.util.Scanner;

public class Teclado {
	private Scanner sc = new Scanner( System.in );
	
	public int leerEntero()
	{
		int entero = 0;
		try
		{
			entero = sc.nextInt();
			sc.nextLine();
		}
		catch( Exception e )
		{
			System.out.println( "Error al leer el dato. Tipo: Entero" );
			e.printStackTrace();
		}
		return entero;
	}
	
	public String leerCadena()
	{
		String cad = "";
		try
		{
			cad = sc.nextLine();
		}
		catch( Exception e )
		{
			System.out.println( "Error al leer el dato. Tipo: Cadena" );
			e.printStackTrace();
		}
		return cad;
	}
	
	public char leerChar()
	{
		char c = ' ';
		try
		{
			c = sc.nextLine().charAt( 0 );
		}
		catch( Exception e )
		{
			System.out.println( "Error al leer el dato. Tipo: Char" );
			e.printStackTrace();
		}
		return c;
	}
}

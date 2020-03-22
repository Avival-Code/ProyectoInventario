/******************************************************/
/* Nombre de Tarea: Creacion Javadoc                  */
/* Numero de version: 1.0                             */
/* Nombre: Christian Avila Valdes                     */
/* Descripcion: Establece una conexion con la Base    */
/* 			de Datos que se va a utilizar. Como solo  */
/* 			utilizamos una Base de Datos, la          */
/* 			informacion para conectarse es estatica   */
/* 			y no toma parametros.                     */
/*                                                    */
/* Fecha: 12 - marzo - 2020                           */
/******************************************************/

/*******************************************************/
/* Instrucciones de Reutilizacion                      */
/*                                                     */
/* 	Se debera agregar un constructos con parametros    */
/* 	para poder cambiar los valores de la Base de       */
/* 	Datos para poder reutilizar este componente de     */
/* 	manera dinamica.                                   */
/*******************************************************/

package modelo;

import java.sql.*;

public class Conexion {
	public Connection con = null;
	public String driver = "com.mysql.jdbc.Driver";
	public String database = "ejemplocrud";
	public String hostname = "localhost";
	public String port = "3306";
	public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
	public String username = "root";
	public String password = "Pollito12Con23Papas4512345";
	
	/**
	 * Esta funcion se encarga de iniciar la conexion.
	 */
	public void startConection()
	{
		try
		{
			con = DriverManager.getConnection( 
					"jdbc:mysql://localhost:3306/Inventario", username, password);
			//System.out.println( "Conexion establecida." );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Esta funcion se encarga de detener la conexion.
	 */
	public void stopConection()
	{
		try
		{
			con.close();
			//System.out.println( "Conexion terminada." );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}

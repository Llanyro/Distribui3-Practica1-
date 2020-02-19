package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BaseDeDatos {
	//Clase madre de la que heredan todas las clases que se creen de conexion con la base de datos
	//Defines
	protected static final String driver = "com.mysql.jdbc.Driver";
	protected String port = "3306";
	protected String database = "";
	protected String hostname = "";
	protected String url = "";
	protected String username = "";
	protected String password = "";
	
	//Variables
    protected Connection conexion = null;
    protected boolean flagConexionBBDD = false;
    protected Statement statement = null;
    protected ReentrantLock lock = null;
    //Getters
    public boolean getFlagConexionBBDD () {
    	return (this.flagConexionBBDD);
    }
    
    //Funciones privadas
    protected void conectarMySQL() throws SQLException {
    	if (!this.flagConexionBBDD) {
	        try {
	            Class.forName(driver);
	            this.conexion = DriverManager.getConnection(this.url, this.username, this.password);
	            this.statement = conexion.createStatement();
	            this.flagConexionBBDD = true;
	            lock = new ReentrantLock();
	        } catch (ClassNotFoundException | SQLException e) {
	        	this.flagConexionBBDD = false;
	            e.printStackTrace();
	        }
    	}
    }
    
    //Funciones publicas
    public List<List<String>> ejecutarConsulta(String consulta, List<String> listaParametros) throws SQLException  {
    	this.lock.lock();
		//try { Thread.sleep(1 * 500); }
		//catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
    	//Devuelve una lista de listas de Strings correspondiente a la consulta hecha
    	//Recibe la consulta y los parametros en forma de lista
    	PreparedStatement sentencia = conexion.prepareStatement(consulta);
    	for (int i = 0; i < listaParametros.size(); i++) 
    		sentencia.setString(i + 1, listaParametros.get(i));
    	
    	
    	ResultSet resultadoConsulta = sentencia.executeQuery();
    	List<List<String>> resultados = new ArrayList<List <String>>();

    	ResultSetMetaData rsMetaData = resultadoConsulta.getMetaData();
        	
        int numberOfColumns = rsMetaData.getColumnCount();
            
        List <String> resultado = new ArrayList<String>();
        for (int i = 1; i < numberOfColumns+1; i++) {
        	resultado.add(rsMetaData.getColumnName(i));
        }
        resultados.add(resultado);
            
        while (resultadoConsulta.next())
        {
        	boolean flag = true;
        	resultado = new ArrayList<String>();
        	for (int i = 1; flag; i++) {
    	    	try {
    	    		resultado.add(resultadoConsulta.getString(i));
    			} catch (SQLException e) {
    				resultados.add(resultado);
    				flag = false;
    			}
        	}
        }
    	this.lock.unlock();
    	return resultados;
	} 
    public boolean cerrarConexion() {
    	//SOLO USAR AL FINAL DEL PROGRAMA
    	boolean flag;
    	try {
    		flagConexionBBDD = false;
			conexion.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
    	
    	return flag;
    }
	
}

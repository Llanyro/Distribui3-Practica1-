package sql;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ControladorBaseDatos extends BaseDeDatos{
	//Singleton 
	private static ControladorBaseDatos instancia = null;
	public static ControladorBaseDatos getInstancia() throws SQLException {
		if (instancia==null)
			instancia = new ControladorBaseDatos();
		return instancia;
	}
	private ControladorBaseDatos() throws SQLException {
		this.conexion = null;
		this.flagConexionBBDD = false;
		this.database = "tienda";
		this.hostname = "192.168.1.33";
		this.username = "monty";
		this.password = "some_pass";
		this.url = "jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database + "?useSSL=false";
		this.conectarMySQL();
	}
		
    //Funciones publicas
    public int actualizarFila(String update, List<String> listaParametros) {
    	this.lock.lock();
    	//Devuelve un entero con el numero de filas que han sido insertadas o eliminadas
    	//En caso de error, devuelve -1
    	int retorno;
    	
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(update);
    		for (int i = 0; i < listaParametros.size(); i++) {
        		sentencia.setString(i + 1, listaParametros.get(i));
        	}
    		retorno = sentencia.executeUpdate();
		} catch (SQLException e) {
			retorno = -1;
			e.printStackTrace();
		}
    	this.lock.unlock();
    	return retorno;
    }   
}

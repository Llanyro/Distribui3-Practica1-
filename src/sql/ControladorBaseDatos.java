package sql;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		this.hostname = "192.168.0.101";
		this.username = "monty";
		this.password = "some_pass";
		this.url = "jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database + "?useSSL=false";
		this.conectarMySQL();
	}
		
    //Funciones publicas
    public int actualizarFila(String update, List<String> listaParametros) {
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
    	
    	return retorno;
    }
    public int siguienteId(String tabla) {
    	//Recibe una tabla de la base de datos y un tipo de id (mascota o propietario)
    	//Y devuelve el proximo id disponible
    	int siguienteId = -1;
    	String consulta = "";
    	List<List<String>> nombre;
    	
    	switch(tabla)
    	{
		case "mascota":
			consulta = "select id_mascota from mascota " + ";"; 
			break;
		case "propietario":
			consulta = "select id from propietario " + ";"; 
			break;
    	}
    	
    	try {
    		List<String> listaParametros = new ArrayList<String>();
			nombre = this.ejecutarConsulta(consulta, listaParametros);

			if (nombre.size() == 1) siguienteId = 1;
			else {
				siguienteId = Integer.parseInt(nombre.get(nombre.size()-1).get(0));
				siguienteId++;
			}
		} catch (SQLException e) {
			siguienteId = -1;
			e.printStackTrace();
		}
    	
    	
        return siguienteId;
    }
   
}

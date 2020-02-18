package objetos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.ControladorBaseDatos;

public class GestorObjetos {
	//Singleton
	private static GestorObjetos instancia = null;
	public static GestorObjetos getInstancia() {
		if (instancia==null)
			instancia = new GestorObjetos();
		return instancia;
	}
	private GestorObjetos(){}
	
	
	//Funciones privadas
	private boolean guardarCantidad (int id, int cantidad) {
		//Funcion que recibe el id y la nueva cantidad a guardar y la modifica de la base de datos
		boolean dev = false;
		String update = "update productos set cantidad= ? where id_productos= ? ;";
		List<String> listaParametros = new ArrayList<String>();
		listaParametros.add(String.valueOf(cantidad));
		listaParametros.add(String.valueOf(id));
		
		try {
			int actualizado = ControladorBaseDatos.getInstancia().actualizarFila(update, listaParametros);
			if (actualizado > 0) dev = true;
			
		} catch (SQLException e) {
			dev = false;
			e.printStackTrace();
		}
		
		return dev;
	}
	
	
	//Funciones publicas
	public int agregarObjeto(Objetos objeto) {
		//Funcion que recibe un objeto y los agrega a la Base de datos
		int dev = -1;
		String consulta = "insert into productos values ( ? , ? , ? , ? ) ;";
		List<String> listaParametros = new ArrayList<String>();
		listaParametros.add(String.valueOf(objeto.getId()));
		listaParametros.add(objeto.getNombre());
		listaParametros.add(objeto.getDescripcion());
		listaParametros.add(String.valueOf(objeto.getCantidad()));
		
		try {
			dev = ControladorBaseDatos.getInstancia().actualizarFila(consulta, listaParametros);
		} catch (SQLException e) {
			dev = -1;
			e.printStackTrace();
		}
		return dev;
	}
	
	public int eliminarObjeto(int id) {
		//Funcion que recibe un id y eliminar el producto correspondiente de la base de datos
		int dev = -1;
		String consulta = "delete from productos where id_productos= ? ;";
		List<String> listaParametros = new ArrayList<String>();
		listaParametros.add(String.valueOf(id));
		
		try {
			dev = ControladorBaseDatos.getInstancia().actualizarFila(consulta, listaParametros);
		} catch (SQLException e) {
			dev = -1;
			e.printStackTrace();
		}
		
		return dev;
	}
	
	public List<Objetos> getObjetos() {
		//Funcion que devuelve al usuario los objetos que hay disponibles para comprar (cantidad mayor a 0)
		String consulta = "select * from productos where cantidad > 0;";
		List<String> listaParametros = new ArrayList<String>();
		List<Objetos> listaObjetos = new ArrayList<Objetos>();
		
		try {
			List<List<String>> listaConsulta = ControladorBaseDatos.getInstancia().ejecutarConsulta(consulta, listaParametros);
			for (int i = 1; i < listaConsulta.size(); i++) {
				Objetos objeto = new Objetos();
				for (int j = 0; j < listaConsulta.get(i).size(); j++) {
					switch(listaConsulta.get(0).get(j)) {
					  case "id_productos":
						  objeto.setId(Integer.parseInt(listaConsulta.get(i).get(j)));
					      break;
					  case "nombre":
						  objeto.setNombre(listaConsulta.get(i).get(j));
						  break;
					  case "descripcion":
						   	objeto.setDescripcion(listaConsulta.get(i).get(j));
						    break;
					  case "cantidad":
						  	objeto.setCantidad(Integer.parseInt(listaConsulta.get(i).get(j)));
						    break;
					  default:
					    System.out.println("meh");
					}
				}
				
				listaObjetos.add(objeto);
			}
		} catch (SQLException e) {
			listaObjetos = new ArrayList<Objetos>();
			e.printStackTrace();
		}
		
		return listaObjetos;
	}

	public List<Integer> getIDObjetos() {
		//Funcion que devuelve al camion los id de los productos en la base de datos
		String consulta = "select id_productos from productos;";
		List<String> listaParametros = new ArrayList<String>();
		List<Integer> listaInt = new ArrayList<Integer>();
		
		try {
			List<List<String>> listaConsulta = ControladorBaseDatos.getInstancia().ejecutarConsulta(consulta, listaParametros);
			
			for (int i = 1; i < listaConsulta.size(); i++) {
				listaInt.add(Integer.parseInt(listaConsulta.get(i).get(0)));
			}
			
		} catch (SQLException e) {
			listaInt = new ArrayList<Integer>();
			e.printStackTrace();
		}
		
		return listaInt;
	}
	
	public int alterarCantidadObjetos(int id, int cantidad) {
		int dev = 0;
		boolean error = false;
		String consulta = "select cantidad from productos where id_productos= ? ;";
		List<String> listaParametros = new ArrayList<String>();
		listaParametros.add(String.valueOf(id));
		
		try {
			List<List<String>> listaConsulta = ControladorBaseDatos.getInstancia().ejecutarConsulta(consulta, listaParametros);
			 if(listaConsulta.size() > 1) {
				 if (cantidad >= 0) {
					 error = this.guardarCantidad(id, Integer.parseInt(listaConsulta.get(1).get(0)) + cantidad);
					 dev = cantidad;
				 }
				 else {
					 int comprobacion = Integer.parseInt(listaConsulta.get(1).get(0));
					 if (cantidad + comprobacion <= 0) {
						 error = this.guardarCantidad(id, 0);
						 dev = cantidad + Integer.parseInt(listaConsulta.get(1).get(0));
					 }
					 else {
						 error = this.guardarCantidad(id, Integer.parseInt(listaConsulta.get(1).get(0)) + cantidad);
						 dev = cantidad;
					 } 
				 }
			 }
			 
			 if (!error) dev = 0;
	
		} catch (SQLException e) {
			dev = 0;
			e.printStackTrace();
		}
		
		return dev;
	}
}

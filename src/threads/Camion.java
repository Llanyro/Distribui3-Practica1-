package threads;

import java.util.Collections;
import java.util.List;

import objetos.GestorObjetos;
import objetos.Objetos;

public class Camion extends Usuario {
	private List<Integer> listaObjetosAlmacen = null;
	
	protected Camion(boolean mostrarEnPantalla) {
		super("Camion", mostrarEnPantalla);
	}
	protected int ordenarYDevoverNuevoId() {
		Collections.sort(this.listaObjetosAlmacen);
		int idAnterior = 1;
		if(this.listaObjetosAlmacen.size() != 0)
			idAnterior = this.listaObjetosAlmacen.get(this.listaObjetosAlmacen.size()-1)+1;
		return idAnterior;
	}
	private String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	@Override
	public void run() {
		boolean continuar = true;
		this.listaObjetosAlmacen = GestorObjetos.getInstancia().getIDObjetos();
		
		do {
			try { Thread.sleep(1 * 500); }
			catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
			int opcion = (int)(Math.random()*100);
			
			if(opcion == 0)
				continuar = false;
			else if(opcion > 95 && opcion < 100)
			{
				Objetos nuevo = new Objetos();
				nuevo.setCantidad((int)(Math.random()*Usuario.NUM));
				nuevo.setId(this.ordenarYDevoverNuevoId());
				nuevo.setNombre(this.getAlphaNumericString(10));
				nuevo.setDescripcion("descripcion de " + nuevo.getNombre());
				int resultado = GestorObjetos.getInstancia().agregarObjeto(nuevo);
				this.listaObjetosAlmacen = GestorObjetos.getInstancia().getIDObjetos();
				if(this.mostrarEnPantalla)
				{
					if(resultado == 1)
						System.out.println("Camion ha traido y añadido el objeto " + nuevo.toString() +
								" a la tienda.");
					else
						System.out.println("Se ha intentado traer el objeto " + nuevo.toString() + 
								" pero no se ha podido dejar en la tienda.");
				}
			}
			else if(opcion > 93 && opcion < 95)
			{
				if(this.listaObjetosAlmacen.size() > 0)
				{
					int pos = (int)(Math.random()*this.listaObjetosAlmacen.size());
					int id = this.listaObjetosAlmacen.get(pos);
					int resultado = GestorObjetos.getInstancia().eliminarObjeto(id);
					this.listaObjetosAlmacen = GestorObjetos.getInstancia().getIDObjetos();
					if(this.mostrarEnPantalla)
					{
						if(resultado == 1)
							System.out.println("La tienda ha dejado de trabajar con el objeto con id " + 
									id +
									" y el camion se ha llevado lo que quedaba.");
						else
							System.out.println("Se ha intentado dejar de trabajar con el id " + 
									id + 
									" pero no se ha podido hacer nada.");
					}	
				}
			}
			else if(opcion > 0 && opcion < 93)
			{
				if(this.listaObjetosAlmacen.size() > 0)
				{
					int pos = (int)(Math.random()*this.listaObjetosAlmacen.size());
					int id = this.listaObjetosAlmacen.get(pos);
					int cantidad = 0;
					while(cantidad == 0)
						cantidad = (int)(Math.random()*Usuario.NUM);
					int objetosAgregados = GestorObjetos.getInstancia().alterarCantidadObjetos(id, cantidad);
					// Añadir cantidad a un item de la lista
					if(this.mostrarEnPantalla)
					{
						if(objetosAgregados == 0)
							System.out.println("Se ha intentado añadir una cantidad " + cantidad+
									" de objetos de id " + id + 
									" pero no estaba guardado en el inventario.");
						else
							System.out.println("Se ha añadido " + cantidad + " de cantidad del objeto de id "
									+ id + " al almacen.");
					}	
				}
			}
		}
		while(continuar);
		System.out.print(this.nombre + " ha salido de la partida...");
	}
}

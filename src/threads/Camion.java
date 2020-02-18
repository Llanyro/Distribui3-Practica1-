package threads;

import java.util.Collections;
import java.util.List;
import objetos.Objetos;

public class Camion  extends Usuario {
	private List<Integer> listaObjetosAlmacen = null;
	
	protected Camion(boolean mostrarEnPantalla) {
		super("Camion", mostrarEnPantalla);
	}
	protected int ordenarYDevoverNuevoId() {
		boolean continuar = true;
		Collections.sort(this.listaObjetosAlmacen);
		int idAnterior = 0;
		for(int i = 0; i < this.listaObjetosAlmacen.size() && continuar; i++) {
			if(this.listaObjetosAlmacen.get(i) - idAnterior > 1)
			{
				continuar = false;
				idAnterior++;
			}
			else
				idAnterior = this.listaObjetosAlmacen.get(i);
		}
		if(!continuar)
			idAnterior++;
		return idAnterior;
	}
	
	@Override
	public void run() {
		boolean continuar = true;
		//Obtiene la lista de la tienda
		do {
			int opcion = (int)Math.random();
			
			if(opcion == 0)
				continuar = false;
			else if(opcion > 90 && opcion < 100)
			{
				Objetos nuevo = new Objetos();
				nuevo.setCantidad((int)(Math.random()*Usuario.NUM));
				//Add el nuevo item a la tienda
			}
			else if(opcion > 0 && opcion < 10)
			{
				int pos = (int)(Math.random()*this.listaObjetosAlmacen.size());
				this.listaObjetosAlmacen.get(pos); // <--- Eliminamos este item
			} 
			else if(opcion > 0 && opcion < 10)
			{
				int pos = (int)(Math.random()*this.listaObjetosAlmacen.size());
				int cant = 0;
				while(cant == 0)
					cant = (int)(Math.random()*Usuario.NUM);
				// Añadir cantidad a un item de la lista
			}
		}
		while(continuar);
		System.out.print("Camion ha salido de la partida...");
	}
}

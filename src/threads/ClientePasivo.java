package threads;

import java.util.List;

import objetos.GestorObjetos;
import objetos.Objetos;

public class ClientePasivo extends Usuario {
	private List<Objetos> listaObjetosAlmacen = null;
	protected ClientePasivo(boolean mostrarEnPantalla) {
		super("Cliente random", mostrarEnPantalla);
	}
	private Objetos getObjetoTienda(int id) {
		Objetos resultado = null;
		for(int i = 0; i < this.listaObjetosAlmacen.size() && resultado == null; i++)
				if(this.listaObjetosAlmacen.get(i).getId() == id)
					resultado = this.listaObjetosAlmacen.get(i);
		return resultado;
	}
	private int estaEnLaLista(int id) {
		int resultado = -1;
		for(int i = 0; i < this.listaObjetos.size() && resultado == -1; i++)
				if(this.listaObjetos.get(i).getId() == id)
					resultado = i;
		return resultado;
	}
	private void addCantidadAlObjeto(int id, int cantidad) {
		for(int i = 0; i < this.listaObjetos.size(); i++) {
			if(this.listaObjetos.get(i).getId() == id)
				this.listaObjetos.get(i).addCantidad(cantidad);
		}
	}
	@Override
	public void run() {
		boolean continuar = true;

		do {
			try { Thread.sleep(1 * 500); }
			catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
			int opcion = (int)(Math.random()*100);
			
			if(opcion == 0)
				continuar = false;
			else if(opcion > 0 && opcion < 80)
			{
				this.listaObjetosAlmacen = GestorObjetos.getInstancia().getObjetos();
				if(this.listaObjetosAlmacen.size() > 0) {
					int pos = (int)(Math.random()*this.listaObjetosAlmacen.size());
					int id = this.listaObjetosAlmacen.get(pos).getId();
					int cantidad = (int)(Math.random()*this.listaObjetosAlmacen.get(pos).getCantidad());
					int objetosObtenidos = 0;
					if(cantidad > 0)
					{
						objetosObtenidos = -GestorObjetos.getInstancia().alterarCantidadObjetos(id, -cantidad);
						if(objetosObtenidos > 0)
						{
							int id_lista = this.estaEnLaLista(id);
							if(id_lista == -1)
							{
								Objetos obj = new Objetos(this.getObjetoTienda(id));
								obj.setCantidad(objetosObtenidos);
								this.listaObjetos.add(obj);
							}
							else
							{
								this.addCantidadAlObjeto(id, objetosObtenidos);
							}
							if(this.mostrarEnPantalla)
								System.out.println(this.nombre + " ha comprado " + objetosObtenidos + " de objeto de id " + id);
						}
						else if(this.mostrarEnPantalla)
							System.out.println(this.nombre + " ha sido demasiado lento y no ha podido comprar lo que queria.");
					}
				}
			}
			else if(opcion > 80 && opcion < 100) {
				if(this.listaObjetos.size() > 0) {
					int pos = (int)(Math.random()*this.listaObjetos.size());
					Objetos obj = this.listaObjetos.get(pos);
					int id = obj.getId();
					int cantidad = (int)(Math.random()*this.listaObjetos.get(pos).getCantidad());
					if(cantidad > 0)
					{
						if(GestorObjetos.getInstancia().alterarCantidadObjetos(obj.getId(), cantidad) == cantidad)
						{
							if(cantidad == obj.getCantidad())
								this.listaObjetos.remove(pos);
							else
								this.listaObjetos.get(pos).addCantidad(-cantidad);
							
							if(this.mostrarEnPantalla)
								System.out.println(this.nombre + " ha devuelto " + cantidad + " de un objeto con el id "+ id + " a la tienda");
						}
						else if(this.mostrarEnPantalla)
							System.err.println(this.nombre + " ha intentado devolver un objeto que ya no se controla en la tienda.");
					}
				}
			}
		}
		while(continuar);
		System.out.print(this.nombre + " ha salido de la partida...");
	}
}

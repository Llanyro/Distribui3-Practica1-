package threads;

import java.util.Scanner;

import objetos.GestorObjetos;
import objetos.Objetos;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
	private Scanner entradaScanner = null;
	private List<Objetos> listaObjetosAlmacen = null;
	
	protected Cliente(String nombre) {
		super(nombre, false);
		this.entradaScanner = new Scanner(System.in);
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
	private void imprimirOpciones() {
		System.out.print("\n");
		System.out.println("Tienda/Ver Tienda: Actualiza la lista de la tienda y la muestra.");
		System.out.println("Carrito/Inventario: Muestra los objetos de tu carrito.");
		System.out.println("Comprar: Abre la tienda para compar algo.");
		System.out.println("Devolver: Abre la tienda para compar algo.");
		
		System.out.println("Help/Ayuda: Panel de controles.");
		System.out.println("Salir/Exit: Salir de la tienda.");
	}
	private void imprimirListaObjetosCliente() {
		if(this.listaObjetos.size() == 0)
		{
			System.out.println("Vaya, parece que tu carrito esta vacio.");
			System.out.println("Prueba a compar algo en la tienda!");
		}
		else
		{
			for(int i = 0; i < this.listaObjetos.size(); i++) {
				System.out.println(this.listaObjetos.get(i).toString());
			}
		}
	}
	private void imprimirListaObjetosAlmacen() {
		if(this.listaObjetosAlmacen.size() == 0)
		{
			System.out.println("Lo sentimos actualmente no hay objetos disponibles en el almacen.");
			System.out.println("Vuelva mas tarde");
		}
		else
		{
			for(int i = 0; i < this.listaObjetosAlmacen.size(); i++)
				System.out.println(this.listaObjetosAlmacen.get(i).toString());
		}
	}
	private void funcionTienda() {
		System.out.println("¿Que objeto de la lista quieres comprar? Escribe el ID(-1 para salir):");
		int id = (int) this.entradaScanner.nextInt();
		this.entradaScanner.nextLine();
		if(id > 0)
		{
			System.out.println("Introduce la cantidad del objeto a comprar(0 para salir): ");
			int cantidad = (int) this.entradaScanner.nextInt();
			this.entradaScanner.nextLine();
			if(cantidad > 0)
			{
				//Solicitud DDB
				int objetosObtenidos = -GestorObjetos.getInstancia().alterarCantidadObjetos(id, -cantidad);
				if(objetosObtenidos > 0)
				{
					int id_lista = this.estaEnLaLista(id);
					if(id_lista == -1)
					{
						Objetos obj = new Objetos(this.getObjetoTienda(id));
						obj.setCantidad(objetosObtenidos);
						this.listaObjetos.add(obj);
						if(objetosObtenidos < cantidad)
							System.out.print("Parece que no habia suficientes objetos disponibles.\n"
									+ "Aun asi, hemos podido añadirte " + cantidad + " de" + obj.getNombre()
									+ " solicitado.");
						else
							System.out.println("Se ha añadido el objeto " + obj.getNombre() + " con cantidad " +
								objetosObtenidos + " a la lista.");
					}
					else
					{
						this.addCantidadAlObjeto(id, objetosObtenidos);
						if(objetosObtenidos < cantidad)
							System.out.print("Parece que no habia suficientes objetos disponibles.\n"
									+ "Aun asi, hemos podido añadirte " + cantidad + " del objeto solicitado.");
						else
							System.out.println("Se ha añadido " + objetosObtenidos + " de cantidad al inventario.");
					}
				}
				else
					System.out.println("Demasiado lento. Ya no esta este objeto en stock.\n"+
				"Puede que mas tarde tengamos alguna unidad disponible.");
			}
			else
				System.out.println("Ok, Cancelando...");
		}
		else
			System.out.println("Ok, Cancelando...");
	}
	private void funcionDevolver() {
		System.out.println("¿Que objeto de la lista quieres devolver? Escribe el ID(-1 para salir):");
		int id = (int) this.entradaScanner.nextInt();
		this.entradaScanner.nextLine();
		if(id > 0) {
			int pos = this.estaEnLaLista(id);
			if(pos != -1) {
				Objetos obj = this.listaObjetos.get(pos);
				System.out.println("Ok.\nActualmente tienes " + 
						obj.getCantidad() + " del objeto " + 
						obj.getNombre());
				System.out.println("¿Cuantos quieres devolver? (0 para salir): ");
				int cantidad = (int) this.entradaScanner.nextInt();
				this.entradaScanner.nextLine();
				if(cantidad > 0)
				{
					if(cantidad > obj.getCantidad())
						System.out.println("Oye, que no tienes tanto de este objeto ehhh.\nPor supuesto voy a cancelarte la accion.");
					else
					{
						if(GestorObjetos.getInstancia().alterarCantidadObjetos(id, cantidad) == cantidad)
						{
							if(cantidad == obj.getCantidad())
								this.listaObjetos.remove(pos);
							else
								this.listaObjetos.get(pos).addCantidad(-cantidad);
							System.out.println("Se ha devuelto " + cantidad + " del objeto solicitado.");
						}
						else
							System.err.println("La tienda ya no trabaja con este objeto por lo que no se puede devolver.");
					}
				}
				else
					System.out.println("Ok, Cancelando...");
			}
			else
				System.out.println("No existe ningun objeto con el id " + id);
		}
		else
			System.out.println("Ok, Cancelando...");
	}
	@Override
	public void run() {
		String respuesta = null;
		boolean continuar = true;
		
		System.out.println("Introduce tu nombre: ");
		this.nombre = this.entradaScanner.nextLine();
		
		this.imprimirOpciones();
		do
		{
			System.out.print("\nIntroduce la accion a realizar: ");
			respuesta = this.entradaScanner.nextLine();
			if(respuesta.equalsIgnoreCase("help") || respuesta.equalsIgnoreCase("ayuda"))
				this.imprimirOpciones();
			else if(respuesta.equalsIgnoreCase("tienda") || respuesta.equalsIgnoreCase("Ver tienda"))
			{
				System.out.println("Actualizando la lista...");
				listaObjetosAlmacen = GestorObjetos.getInstancia().getObjetos();
				this.imprimirListaObjetosAlmacen();
			}
			else if(respuesta.equalsIgnoreCase("carrito") || respuesta.equalsIgnoreCase("inventario")|| respuesta.equalsIgnoreCase("lista"))
				this.imprimirListaObjetosCliente();
			else if(respuesta.equalsIgnoreCase("Comprar"))
				this.funcionTienda();
			else if(respuesta.equalsIgnoreCase("Devolver"))
				this.funcionDevolver();			
			
			
			else if(respuesta.equalsIgnoreCase("salir") || respuesta.equalsIgnoreCase("exit"))
				continuar = false;
			else if(respuesta.equalsIgnoreCase("tuputamadre.jpg"))
				System.out.println("La tuya mas!");
			else
			{
				System.out.println("Oh no!, parece que te has equivocado al hacer la solicitud.");
				System.out.println("Si necesitas ayuda prueba a escribir: 'HELP'");
			}
		}
		while(continuar);		
		System.out.println("Usuario " + this.nombre + ".");
		System.out.println("Gracias por comprar en AWAZON SL.\nEsperamos que vuelva pronto");
	}
}

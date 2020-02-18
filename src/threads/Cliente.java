package threads;

import java.util.Scanner;

public class Cliente extends Usuario {
	Scanner entradaScanner = null;
	protected Cliente(String nombre) {
		super(nombre);
		this.entradaScanner = new Scanner(System.in);
	}
	
	public void ImprimirOpciones() {
		System.out.print("\n");
		System.out.println("Help/Ayuda: Panel de controles.");
		System.out.println("Salir/Exit: Salir de la tienda.");
	}
	
	
	@Override
	public void run() {
		String respuesta = null;
		boolean continuar = true;
		System.out.println("Introduce tu nombre: ");
		this.nombre = this.entradaScanner.nextLine();
		
		
		this.ImprimirOpciones();
		do
		{
			System.out.print("Introduce la accion a realizar: ");
			respuesta = this.entradaScanner.nextLine();
			if(respuesta.equalsIgnoreCase("help") || respuesta.equalsIgnoreCase("ayuda"))
				this.ImprimirOpciones();
			else if(respuesta.equalsIgnoreCase("salir") || respuesta.equalsIgnoreCase("exit"))
				continuar = false;
			else
			{
				
			}
		}
		while(continuar);		
		System.out.println("Usuario " + this.nombre + ".");
		System.out.println("Gracias por comprar en AWAZON SL.\nEsperamos que vuelva pronto");
	}
}

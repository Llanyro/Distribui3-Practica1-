package threads;

public class ClientePasivo extends Usuario {
	protected ClientePasivo(boolean mostrarEnPantalla) {
		super("Cliente random", mostrarEnPantalla);
	}
	@Override
	public void run() {
		System.out.println(this.nombre + " ha comenzado a testear");
		try{ Thread.sleep(10 * 1000); }
		catch(InterruptedException ie){ ie.printStackTrace(); }  
		System.out.println(this.nombre + " ha parado de testear");
	}
}

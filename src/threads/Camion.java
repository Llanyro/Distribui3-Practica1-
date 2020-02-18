package threads;

public class Camion  extends Usuario {
	protected Camion() {
		super("Camion");
	}
	@Override
	public void run() {
		System.out.println(this.nombre + " ha comenzado a testear");
		try{ Thread.sleep(2 * 1000); }
		catch(InterruptedException ie){ ie.printStackTrace(); }  
		System.out.println(this.nombre + " ha parado de testear");
	}
}

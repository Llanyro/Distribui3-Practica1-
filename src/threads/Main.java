package threads;

public class Main {

	public static void main(String[] args) {
		Usuario user = new Cliente("User1");
		Usuario cli = new ClientePasivo(true);
		Usuario cam = new Camion(true);
		
		user.start();
		cli.start();
		cam.start();
	}
}

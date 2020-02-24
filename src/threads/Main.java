package threads;

public class Main {

	public static void main(String[] args) {
		Usuario user = new Cliente("User1");
		Usuario cli = new ClientePasivo(false);
		Usuario cam = new Camion(false);
		
		user.start();
		cli.start();
		cam.start();
	}
}

package threads;

public class Main {

	public static void main(String[] args) {
		Usuario user = new Cliente("User1");
		//Usuario cli = new ClientePasivo();
		//Usuario cam = new Camion();
		
		user.start();
		//cli.start();
		//cam.start();
	}
}

package objetos;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*GestorObjetos.getInstancia().eliminarObjeto(2);
		
		Objetos objeto = new Objetos();
		objeto.setNombre("nos22");
		objeto.setId(2);
		objeto.setDescripcion("descripcion2");
		objeto.setCantidad(45);
		GestorObjetos.getInstancia().agregarObjeto(objeto);*/
		
		System.out.println(GestorObjetos.getInstancia().alterarCantidadObjetos(2, -123));

		
		/*List<Objetos> lista = GestorObjetos.getInstancia().getObjetos();
		
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getId());
			System.out.println(lista.get(i).getNombre());
			System.out.println(lista.get(i).getDescripcion());
			System.out.println(lista.get(i).getCantidad());
			System.out.println();
		}*/
		
		
		

	}

}

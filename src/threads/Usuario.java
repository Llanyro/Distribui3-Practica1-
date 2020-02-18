package threads;

import java.util.List;

import objetos.Objetos;

public abstract class Usuario extends Thread {
	protected String nombre = null;
	protected List<Objetos> listaObjetos = null;
	public String getNombre() {
		return this.nombre;
	}
	protected Usuario(String nombre) {
		this.nombre = nombre;
	}
}

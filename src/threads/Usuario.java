package threads;

import java.util.ArrayList;
import java.util.List;

import objetos.Objetos;

public abstract class Usuario extends Thread {
	protected String nombre = null;
	protected List<Objetos> listaObjetos = null;
	public static final int NUM = 4;
	protected boolean mostrarEnPantalla = false;
	public String getNombre() {
		return this.nombre;
	}
	protected Usuario(String nombre, boolean mostrarEnPantalla) {
		this.nombre = nombre;
		this.mostrarEnPantalla = mostrarEnPantalla;
		this.listaObjetos = new ArrayList<Objetos>();
	}
}

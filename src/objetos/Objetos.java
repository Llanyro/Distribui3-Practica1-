package objetos;

public class Objetos {
	private int id = -1;
	private String nombre = null;
	private String descripcion = null;
	private int cantidad = -1;
	
	public Objetos(Objetos objetoTienda) {
		this.nombre = objetoTienda.nombre;
		this.descripcion = objetoTienda.descripcion;
		this.id = objetoTienda.id;
		this.cantidad = objetoTienda.cantidad;
	}
	public Objetos() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void addCantidad(int cantidad) {
		this.cantidad += cantidad;
	}
	public String toString() {	
		return ("Id: " + this.id + "\n" +
	"Nombre: " + this.nombre + "\n"+
				"Cantidad: " + this.cantidad + "\n"+
	"Descripcion: " + this.descripcion);
	}
}

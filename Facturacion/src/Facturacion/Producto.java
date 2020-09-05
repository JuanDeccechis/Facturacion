package Facturacion;

public class Producto {

	private int id;
	private String nombre;
	private float valor;
	
	public Producto(int id, String nombre, float valor) {
		super();
		this.id= id;
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public Producto() {
		super();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", valor=" + valor + "]";
	}

	
}

package eventos.modelo.javabean;

import java.io.Serializable;

public class Tipo  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idTipo;
	private String nombre;
	
	public Tipo(int idTipo, String nombre) {
		super();
		this.idTipo = idTipo;
		this.nombre = nombre;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Tipo [idTipo=" + idTipo + ", nombre=" + nombre + "]";
	}
}
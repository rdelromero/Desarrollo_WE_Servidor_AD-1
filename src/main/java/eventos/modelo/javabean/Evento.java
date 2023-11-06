package eventos.modelo.javabean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Evento implements Serializable{

	private static final long serialVersionUID = 1L;
	//Atributos de instancia
	private int idEvento;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private int duracion;
	private String direccion;
	private String estado;
	private char destacado;
	private int aforo;
	private int asistencia;
	private double precio;
	private Tipo tipo;
	
	//Métodos constructores
	public Evento() {
		super();
	}
	
	public Evento(int idEvento, String nombre, String descripcion, Date fecha, int duracion, String direccion,
			String estado, char destacado, int aforo, int asistencia, double precio, Tipo tipo) {
		super();
		this.idEvento = idEvento;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.duracion = duracion;
		this.direccion = direccion;
		this.estado = estado;
		this.destacado = destacado;
		this.aforo = aforo;
		this.asistencia = asistencia;
		this.precio = precio;
		this.tipo = tipo;
	}

	//Métodos getter y setter
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public char getDestacado() {
		return destacado;
	}
	public void setDestacado(char destacado) {
		this.destacado = destacado;
	}
	public int getAforo() {
		return aforo;
	}
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	public int getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(int asistencia) {
		this.asistencia = asistencia;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return idEvento == other.idEvento;
	}
}

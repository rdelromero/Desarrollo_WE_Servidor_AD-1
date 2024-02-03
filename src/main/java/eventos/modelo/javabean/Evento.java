package eventos.modelo.javabean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Método constructor con todos los argumentos
@AllArgsConstructor
//Método constructor sin argumentos
@NoArgsConstructor
//getter y setter, toString
@Data
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idEvento;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private int duracion;
	private String direccion;
	private String estado;
	private char destacado;
	private int aforoMaximo;
	private int minimoAsistencia;
	private double precio;
	private Tipo tipo;
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
	
	//Dos eventos son iguales si tienen el mismo idEvento.
	@Override
	public int hashCode() {
		return Objects.hash(idEvento);
	}
}


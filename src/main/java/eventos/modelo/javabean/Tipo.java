package eventos.modelo.javabean;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Método constructor con todos los argumentos
@AllArgsConstructor
//Método constructor sin argumentos
@NoArgsConstructor
//getter y setter, hashCode, toString
@Data
public class Tipo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idTipo;
	private String nombre;
	private String descripcion;
}

package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.javabean.Tipo;

public interface TipoDao {
	public abstract Tipo devolverTipoPorId(int idTipo);
	public abstract List<Tipo> devolverTodosTipos();
}
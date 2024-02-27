package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.javabean.Evento;

public interface EventoDao {
	public abstract Evento devolverEventoPorId(int idEvento);
	public abstract List<Evento> devolverTodosEventos();
	public abstract int añadirEvento(Evento evento);
	public abstract int actualizarEvento(Evento cliente);
	public abstract int borrarEventoPorId(int idEvento);
	public abstract int cancelarEventoPorId(int idEvento);	
}

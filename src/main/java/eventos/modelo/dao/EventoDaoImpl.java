package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import eventos.modelo.javabean.Evento;

@Qualifier("evento")
@Repository
public class EventoDaoImpl implements EventoDao {

	private List<Evento> lista;
	private TipoDao tipoEvento;
	private static int idAuto;
	
	static {
		idAuto=0;
	}
	public  EventoDaoImpl() {
		lista = new ArrayList<>();
		tipoEvento = new TipoDaoImpl();
		cargarLista();
	}
	
	private void cargarLista() {
		lista.add(new Evento(001, "Concierto de La Risión", "IES Príncipe Felipe", new Date(), 180, "c. Finisterre, 60, Madrid", "ACTIVO", 'N', 200, 120, 199.99, tipoEvento.findById(1)));
		lista.add(new Evento(002, "Concierto de El Barrio", "Finca de Sergio Ramos", new Date(), 180, "Finca La Alegría, Bollullos de la Mitación", "ACTIVO", 'S', 1000, 300, 15000, tipoEvento.findById(1)));
		lista.add(new Evento(003, "Despedida de Ainhoa", "Descripción", new Date(), 180, "Madrid", "ACTIVO", 'N', 100, 60, 199.99, tipoEvento.findById(2)));
		lista.add(new Evento(004, "Cumpleaños de Marcelo", "Descripción", new Date(), 180, "Madrid", "ACTIVO", 'S', 100, 80, 18000, tipoEvento.findById(3)));
		lista.add(new Evento(005, "Boda de Marcos Llorente", "Se casa con infuencer", new Date(), 180, "Sa Fortalesa", "ACTIVO", 'S', 1000, 600, 30000, tipoEvento.findById(4)));
		lista.add(new Evento(006, "Boda de Nerea Camacho", "Se casa con jugador de balonmano", new Date(), 180, "cortijo La Tortuga Mora, Almería", "ACTIVO", 'S', 1000, 700, 15000, tipoEvento.findById(4)));
		idAuto = 6;
	}
	
	@Override
	public int insert(Evento evento) {
		System.out.println("insertando nuevo evento");
		if (!lista.contains(evento)) {
			evento.setIdEvento(++idAuto);
			lista.add(evento);
			
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(int idEvento) {
		Evento even = findById(idEvento);
		if (even == null) 
			return 0;
		
		return lista.remove(even) ? 1 : 0;
	}

	@Override
	public int updateOne(Evento evento) {
		int pos = lista.indexOf(evento);
		System.out.println("actualizando posicion: " + pos);
		if (pos == -1)
			return 0;
		lista.set(pos, evento);
		return 1;
	}

	@Override
	public Evento findById(int idEvento) {
		for (int i = 0; i < lista.size();i++) {
			if (lista.get(i).getIdEvento() == idEvento)
				return lista.get(i);
		}
		return null;
	}

	@Override
	public List<Evento> findAll() {
		// TODO Auto-generated method stub
		return lista;
	}

	 public int cancelarEvento(int idEvento) {
	        Evento evento = findById(idEvento);
	        if (evento != null) {
	            evento.setEstado("CANCELADO");
	            // Actuación del estado  del evento en la base de datos
	            updateOne(evento);

	            // Comprobar el estado por consola
	            System.out.println("Evento " + idEvento + " ha sido cancelado. Estado: " + evento.getEstado());
	        }

	        return idEvento;
	    }
}

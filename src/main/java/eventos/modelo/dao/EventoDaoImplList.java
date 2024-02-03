package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import eventos.modelo.javabean.Evento;

@Repository
public class EventoDaoImplList implements EventoDao {
	//Atributos de instancia
	private List<Evento> listaEventos;
	private TipoDaoImplList tipoDaoImplList;
	//Atributos de clase;
	private static int idAuto;
	static {
		idAuto=0;
	}
	
	//Método constructor
	public EventoDaoImplList() {
		listaEventos = new ArrayList<>();
		tipoDaoImplList = new TipoDaoImplList();
		cargarLista();
	}
	
	private void cargarLista() {
		listaEventos.add(new Evento(001, "Concierto de La Risión", "IES Príncipe Felipe", new Date(), 180, "c. Finisterre, 60, Madrid", "ACTIVO", 'N', 200, 120, 199.99, tipoDaoImplList.devolverTipoPorId(1)));
		listaEventos.add(new Evento(002, "Concierto de El Barrio", "Finca de Sergio Ramos", new Date(), 180, "Finca La Alegría, Bollullos de la Mitación", "ACTIVO", 'S', 1000, 300, 15000, tipoDaoImplList.devolverTipoPorId(1)));
		listaEventos.add(new Evento(003, "Despedida de Ainhoa", "Descripción", new Date(), 180, "Madrid", "ACTIVO", 'N', 100, 60, 199.99, tipoDaoImplList.devolverTipoPorId(2)));
		listaEventos.add(new Evento(004, "Despedida de Marcelo", "jugador del Real Madrid", java.sql.Date.valueOf("2022-07-10"), 180, "restaurante Zuma, Madrid", "ACTIVO", 'S', 100, 80, 18000, tipoDaoImplList.devolverTipoPorId(2)));
		listaEventos.add(new Evento(005, "Cumpleaños Neymar", "jugador del PSG", java.sql.Date.valueOf("2023-02-04"), 200, "París", "ACTIVO", 'S', 100, 80, 18000, tipoDaoImplList.devolverTipoPorId(3)));
		listaEventos.add(new Evento(006, "Boda de Nerea Camacho", "Se casa con jugador de balonmano", java.sql.Date.valueOf("2023-08-26"), 180, "cortijo La Tortuga Mora, Almería", "ACTIVO", 'S', 1000, 700, 15000, tipoDaoImplList.devolverTipoPorId(4)));
		listaEventos.add(new Evento(007, "Boda de Marcos Llorente", "Se casa con infuencer", java.sql.Date.valueOf("2023-08-30"), 180, "Sa Fortalesa, La Pobla, Mallorca", "ACTIVO", 'S', 1000, 600, 30000, tipoDaoImplList.devolverTipoPorId(4)));
		idAuto = 7;
	}
	
	@Override
	public String toString() {
		return "EventoDaoImplList [listaEventos=" + listaEventos + ", tipoDaoImplList=" + tipoDaoImplList + "]";
	}
	
	@Override
	public Evento devolverEventoPorId(int idEvento) {
		for (int i = 0; i < listaEventos.size();i++) {
			if (listaEventos.get(i).getIdEvento() == idEvento)
				return listaEventos.get(i);
		}
		return null;
	}
	
	@Override
	public List<Evento> devolverTodosEventos() {
		return listaEventos;
	}
	@Override
	public int crearEvento(Evento evento) {
		if (!listaEventos.contains(evento)) {
			evento.setIdEvento(++idAuto);
			listaEventos.add(evento);
			return 1;
		}
		return 0;
	}
	
	@Override
	public int actualizarEvento(Evento evento) {
		int pos=listaEventos.indexOf(evento);
		if (pos == -1)
			return 0;
		listaEventos.set(pos, evento);
        return 1;
	}
	
	@Override
	public int borrarEventoPorId(int idEvento) {
		Evento evento = devolverEventoPorId(idEvento);
		if (evento == null) 
			return 0;
		return listaEventos.remove(evento) ? 1 : 0;
	}
	
	@Override
	public int cancelarEventoPorId(int idEvento) {
		Evento evento = devolverEventoPorId(idEvento);
		if (evento != null) {
			evento.setEstado("CANCELADO");
		    return 1;
		}
		return 0;
	}
	
}

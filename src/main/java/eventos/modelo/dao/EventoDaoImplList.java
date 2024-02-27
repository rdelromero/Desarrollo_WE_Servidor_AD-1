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
		listaEventos.add(new Evento(1, "Concierto de El Barrio", "Gira Atemporal", java.sql.Date.valueOf("2023-11-04"), 120, "Palau San Jordi, Barcelona", "ACTIVO", 'S', 16000, 10000, 59.00, tipoDaoImplList.devolverTipoPorId(1)));
		listaEventos.add(new Evento(2, "Concierto de Melendi", "Gira 20 años sin noticias", java.sql.Date.valueOf("2023-11-10"), 100, "Pabellón Vizcaya Arena, Bilbao", "ACTIVO", 'S', 200, 120, 59.00, tipoDaoImplList.devolverTipoPorId(1)));
		listaEventos.add(new Evento(3, "Concierto de Toundra", "Banda española de rock instrumental", java.sql.Date.valueOf("2023-11-11"), 120, "Sala Paral·lel 62, Barcelona", "CANCELADO", 'N', 1450, 500, 25.00, tipoDaoImplList.devolverTipoPorId(1)));
		listaEventos.add(new Evento(4, "Despedida de Marcelo", "jugador del Real Madrid", java.sql.Date.valueOf("2022-07-10"), 180, "restaurante Zuma, Madrid", "ACTIVO", 'S', 300, 250, 299.00, tipoDaoImplList.devolverTipoPorId(2)));
		listaEventos.add(new Evento(5, "Despedida de Jonatan Viera", "jugador de UD Las Palmas", java.sql.Date.valueOf("2023-12-19"), 180, "Las Palmas de Gran Canaria", "ACTIVO", 'N', 100, 60, 35.00, tipoDaoImplList.devolverTipoPorId(2)));
		listaEventos.add(new Evento(6, "Cumpleaños Neymar", "jugador del PSG", java.sql.Date.valueOf("2023-02-04"), 200, "París", "ACTIVO", 'S', 400, 300, 1100, tipoDaoImplList.devolverTipoPorId(3)));
		listaEventos.add(new Evento(7, "Cumpleaños Alejandro Sanz", "jugador del PSG", java.sql.Date.valueOf("2023-12-19"), 200, "Teatro Barceló, Madrid", "ACTIVO", 'S', 700, 500, 99.90, tipoDaoImplList.devolverTipoPorId(3)));
		listaEventos.add(new Evento(8, "Boda de Nerea Camacho", "Se casa con jugador de balonmano", java.sql.Date.valueOf("2023-08-26"), 180, "cortijo La Tortuga Mora, Almería", "ACTIVO", 'S', 1000, 700, 290.00, tipoDaoImplList.devolverTipoPorId(4)));
		listaEventos.add(new Evento(9, "Boda de Marcos Llorente", "Se casa con infuencer", java.sql.Date.valueOf("2023-08-30"), 180, "Sa Fortalesa, La Pobla, Mallorca", "ACTIVO", 'S', 1000, 600, 780.00, tipoDaoImplList.devolverTipoPorId(4)));
		listaEventos.add(new Evento(10, "Boda de Juan Ortega", "Se casa con médico Carmen Otte", java.sql.Date.valueOf("2023-08-30"), 180, "Jerez", "CANCELADO", 'S', 500, 400, 599.90, tipoDaoImplList.devolverTipoPorId(4)));
		idAuto = 10;
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
	public int añadirEvento(Evento evento) {
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

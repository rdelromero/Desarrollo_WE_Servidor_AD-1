package eventos.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.javabean.Evento;
import eventos.modelo.javabean.Tipo;

@Controller
public class EventoControlador {
	/*El Autowired busca entre los paquetes y que tenga @Repository una clase
	que implemente el interface*/
	/*Crea un solo objeto de clase EventoDao, es singletone*/
	
	/*Si desde otra clase alguien crea el mismo objeto de esa clase, no crea
	 * otro objeto distinto, sino que apunta al mismo
	 */
	//También podríamos poner EventoDaoImplList.
	/*El autowired equivale a     
	    public EventoControlador() {
            super();
            eventodao = new EventoDaoImplList();
        }
    }*/

	@Autowired
	private EventoDao eventodao;
	@Autowired
	private TipoDao tipodao;
		
	@GetMapping("/eventos")
	public String eventos (Model modelo) {
		modelo.addAttribute("listaTodosEventos", eventodao.devolverTodosEventos());
		return "home_eventos";
	}
	
	@GetMapping("/eventos/crearevento")
	public String evento1 (Model modelo) {
		return "home_eventos_crearEvento";
	}
	
	//El valor que hay entre llaves, "id" debe ser el mismo en getmapping y en PathVariable
	@GetMapping("/eventos/leer/{id}")
	public String leerEvento(@PathVariable("id") int idEvento, Model modelo) {
		Evento evento = eventodao.devolverEventoPorId(idEvento);
		if (evento != null) {
			modelo.addAttribute("evento", evento);
			return "home_eventos_leerEvento";
		}
		else
			modelo.addAttribute("mensaje", "No existe un evento con id="+idEvento+".");
		//En caso de que no exista ese cliente, sale la página de inicio
		return "forward:/eventos";
	}
	
	@PostMapping("/eventos/crear")
	public String crearYAñadirEvento(Evento evento, RedirectAttributes redAtt) {
		evento.setEstado("ACTIVO");
		evento.setTipo(tipodao.devolverTipoPorId(evento.getTipo().getIdTipo()));
		if (eventodao.añadirEvento(evento)==1) {
			redAtt.addFlashAttribute("mensaje", "Creación de evento realizado");
		}
		else
			redAtt.addFlashAttribute("mensaje", "Alta NOOO realizada");
		//Como es Post, no pongo forward sino redirect para volver al home
		return "redirect:/eventos";
	}
	
	@GetMapping("/eventos/actualizar/{id}")
	public String actualizarEvento (@PathVariable("id") int idEvento, Model modelo) {
		Evento evento = eventodao.devolverEventoPorId(idEvento);
		if (evento != null) {
			modelo.addAttribute("evento", evento);
			return "home_eventos_actualizarEvento";
		}
		else
			modelo.addAttribute("mensaje", "Este evento no existe");
		//En caso de que no exista ese cliente, sale la página de inicio
		return "forward:/eventos";
	}
	
	@PostMapping("/eventos/actualizar/{id}")
	public String postActualizarEvento(Evento evento, @PathVariable("id") int idEvento, RedirectAttributes redAtt) {
		evento.setEstado("ACTIVO");
		System.out.println(evento);
		evento.setIdEvento(idEvento);
		evento.setTipo(tipodao.devolverTipoPorId(evento.getTipo().getIdTipo()));
		System.out.println(evento);
		eventodao.actualizarEvento(evento);
		if (eventodao.actualizarEvento(evento)==1) {
			redAtt.addFlashAttribute("mensaje", "Modificación realizada");
		}
		else 
			redAtt.addFlashAttribute("mensaje", "Modificacion NOOO realizada");
		return "redirect:/eventos";
	}
	
	//El valor que hay entre llaves, "id" debe ser el mismo en el getmapping y en PathVariable
	@GetMapping("/eventos/borrar/{id}")
	public String borrarEvento(@PathVariable("id") int idEvento, Model modelo) {
		//El if a la vez está ejecutando cdao.borrarClientePorId(idCliente)...
		if (eventodao.borrarEventoPorId(idEvento)==1)
			modelo.addAttribute("mensaje", "Cliente eliminado correctamente");
		else
			modelo.addAttribute("mensaje", "Evento NOO se ha podido borrar");
		//Volver a la página de inicio.
		return "forward:/eventos";
	}
	
	//El valor que hay entre llaves, "id" debe ser el mismo en el getmapping y en PathVariable
	@GetMapping("/eventos/cancelar/{id}")
	public String cancelarEvento(@PathVariable("id") int idEvento, Model modelo) {
		//El if a la vez está ejecutando cdao.borrarClientePorId(idCliente)...
		if (eventodao.cancelarEventoPorId(idEvento)==1)
			modelo.addAttribute("mensaje", "Evento cancelado correctamente");
		else
			modelo.addAttribute("mensaje", "Cliente NOO se ha podido eliminar");
		//Volver a la página de inicio.
		return "forward:/eventos";
	}
	
	//Método necesario para poder coger el input type="date" de home_eventos_actualizarEventos
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
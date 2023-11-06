package eventos.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.javabean.Evento;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	@Qualifier("evento")
	@Autowired
	private EventoDao edao;

	@GetMapping("/editar/{id}")
	public String editarEvento(@PathVariable("id") int idEvento, Model model) {
		
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "formEditar";
		}
		else {
			model.addAttribute("mensaje", "Este cliente no existe");
			return "forward:/";
		}
		
	}
	
	//Edición de los los atributos de un evento
	@PostMapping("/editar/{id}")
	public String procEditarEvento(Evento evento, @PathVariable("id") int idEvento, RedirectAttributes ratt) {
		evento.setIdEvento(idEvento);
		if (edao.updateOne(evento) == 1)
			ratt.addFlashAttribute("mensaje", "Modificación realizada");
		else
			ratt.addFlashAttribute("mensaje", "Modificacion NOOO  realizada");
		return "redirect:/";
	}

	//Dar de alta a nuevo evento
	@PostMapping("/alta")
	public String procFormAlta(Evento evento, RedirectAttributes ratt) {
		evento.setFecha(new Date());
		if (edao.insert(evento) == 1) {
			ratt.addFlashAttribute("mensaje", "Alta de evento realizada");
			System.out.println(evento);
		}else
			ratt.addFlashAttribute("mensaje", "Alta de evento NOOO realizada");
		
		return "redirect:/";
	}
	
	@GetMapping("/alta")
	public String mostrarFormAlta() {
		return "formAlta";
	}
	
    //Método al que se le proporcina idEvento y devuelve los detalles de ese evento

	@GetMapping("/detalle/{id}")
	public String verEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "verDetalle";
		}
		else {
			model.addAttribute("mensaje", "Este evento no existe");
			return "forward:/";
		}
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") int idEvento, Model model) {
		if (edao.delete(idEvento) == 1)
			model.addAttribute("mensaje", "Evento eliminado correctamente");
		else
			model.addAttribute("mensaje", "Evento NOO se ha podido eliminar");
		
		return "forward:/";
	}
	/**
	 * Método para cancelar o cambiar el estado de un vento a cancelado para ello necesitamos el metodo correspondiente del daoImpl y el id del que tenemos que actualizar
	 * @param id
	 * @return una vez cancelado lo podremos ver por consola o en la pagina principal si le damos a detalle
	 */
	 @GetMapping("/cancelar/{id}")
	    public String cancelarEvento(@PathVariable int id) {
	        edao.cancelarEvento(id);
	      //Comprobaciónpor consolo de que el estado a pasado a cancelado
	        Evento evento = edao.findById(id);
	        System.out.println("Evento " + id + " ha sido cancelado. Estado: " + evento.getEstado());
	        return "redirect:/";
	    }

	@InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}

package eventos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eventos.modelo.dao.EventoDao;

@Controller
public class HomeControlador {
	/*El Autowired busca entre los paquetes y que tenga @Repository una clase
	que implemente el interface*/
	/*Crea un solo objeto de clase ClienteDao, es singletone*/
	
	/*Si desde otra clase alguien crea el mismo objeto de esa clase, no crea
	 * otro objeto distinto, sino que apunta al mismo
	 */
	//También podríamos poner ClienteDaoImplList.
	/*El autowired equivale a     
	    public ClienteController() {
            super();
            cdao = new ClienteDaoImplList();
        }
    }*/

	@GetMapping("/")
	public String mostrarHome (Model modelo) {
		modelo.addAttribute("mensaje", "Esto ha sido escrito en 'HomeControlador.java'.");
		return "home";
	}
	
}

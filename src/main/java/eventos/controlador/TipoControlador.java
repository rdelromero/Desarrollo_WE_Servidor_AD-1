package eventos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;

@Controller
public class TipoControlador {
	/*El Autowired busca entre los paquetes y que tenga @Repository una clase
	que implemente el interface*/
	/*Crea un solo objeto de clase TipoDao, es singletone*/
	
	/*Si desde otra clase alguien crea el mismo objeto de esa clase, no crea
	 * otro objeto distinto, sino que apunta al mismo
	 */
	//También podríamos poner TipoDaoImplList.
	/*El autowired equivale a     
	    public TipoControlador() {
            super();
            tipodao = new TipoDaoImplList();
        }
    }*/

	//También valdría poner TiposDaoImplList
	@Autowired
	private TipoDao tipodao;
	
	@GetMapping("/tipos")
	public String tipos (Model modelo) {
		modelo.addAttribute("listaTodosTipos", tipodao.devolverTodosTipos());
		return "home_tipos";
	}
	
	
}
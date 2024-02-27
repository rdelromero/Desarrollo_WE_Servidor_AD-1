package eventos.pruebas;
import eventos.modelo.dao.EventoDaoImplList;
import eventos.modelo.dao.TipoDaoImplList;

public class PruebaTipo {

	public static void main(String[] args) {
		TipoDaoImplList tipoDaoImplList1 = new TipoDaoImplList();
		System.out.println(tipoDaoImplList1);
		
		EventoDaoImplList eventoDaoImplList1 = new EventoDaoImplList();
		System.out.println(eventoDaoImplList1);
	}

}

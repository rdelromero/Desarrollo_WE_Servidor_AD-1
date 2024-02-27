package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import eventos.modelo.javabean.Tipo;

@Repository
public class TipoDaoImplList implements TipoDao{
	//Atributos de instancia
	private List<Tipo> listaTipos;
	//Atributos de clase
	private static int idAuto;

	static {
		idAuto=0;
	}
	//Método constructor
	public TipoDaoImplList() {
		listaTipos = new ArrayList<>();
		cargarLista();
	}
	
	private void cargarLista() {
		listaTipos.add(new Tipo(1, "Concierto", "descripción1"));
		listaTipos.add(new Tipo(2, "Despedida", "descripción2"));
		listaTipos.add(new Tipo(3, "Cumpleaños", "descripción3"));
		listaTipos.add(new Tipo(4, "Boda", "descripción4"));
		idAuto = 4;
	}

	@Override
	public String toString() {
		return "TipoDaoImplList [listaTipos=" + listaTipos + "]";
	}

	@Override
	public Tipo devolverTipoPorId(int idTipo) {
		for (int i = 0; i < listaTipos.size();i++) {
			if (listaTipos.get(i).getIdTipo() == idTipo)
				return listaTipos.get(i);
		}
		return null;
	}

	@Override
	public List<Tipo> devolverTodosTipos() {
		return listaTipos;
	}
}






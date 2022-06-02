package project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import interfacecrud.InterfaceCrud;
import project.bean.geral.BeanManagedViewAbstract;
import project.geral.controller.EstadoController;
import project.model.classes.Estado;

@Controller
@Scope(value = "session")
@ManagedBean(name = "estadoBeanView")
public class EstadoBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;

	@Autowired
	private EstadoController estadoController;
	
	public List<SelectItem> getEstados() throws Exception {
		return estadoController.getListEstado();
	}

	@Override
	protected Class<Estado> getClassImplement() {
		// TODO Auto-generated method stub
		return Estado.class;
	}

	@Override
	protected InterfaceCrud<Estado> getController() {
		// TODO Auto-generated method stub
		return estadoController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

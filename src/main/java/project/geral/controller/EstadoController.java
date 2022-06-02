package project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import implementacao.crud.ImplementacaoCrud;
import interfacecrud.InterfaceCrud;
import project.model.classes.Estado;

@Controller
public class EstadoController extends ImplementacaoCrud<Estado> implements InterfaceCrud<Estado>{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * retorna uma lista com os estados para inserção no component combobox
	 */
	public List<SelectItem> getListEstado() throws Exception {
			
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Estado> estados = super.findListByQueryDinamica(" from Estado");
		
		for(Estado estado : estados) {
			list.add(new SelectItem(estado, estado.getEst_nome()));
		}
		
		return list;
	}
	

}

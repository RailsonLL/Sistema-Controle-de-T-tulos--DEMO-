package project.geral.controller;

import org.springframework.stereotype.Controller;

import implementacao.crud.ImplementacaoCrud;
import interfacecrud.InterfaceCrud;
import project.model.classes.Mensagem;

@Controller
public class MensagemController extends ImplementacaoCrud<Mensagem> implements InterfaceCrud<Mensagem>{

	private static final long serialVersionUID = 1L;

	@Override
	public Mensagem merge(Mensagem obj) throws Exception {
		// TODO Auto-generated method stub
		return super.merge(obj);
	}
	
}

package project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import implementacao.crud.ImplementacaoCrud;
import interfacecrud.InterfaceCrud;
import project.model.classes.Cidade;
import repository.interfaces.RepositoryCidade;
import srv.interfaces.SrvCidade;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements InterfaceCrud<Cidade>{

	private static final long serialVersionUID = 1L;

	@Resource
	private SrvCidade srvCidade;
	@Resource
	private RepositoryCidade repositoryCidade;
	
}

package project.geral.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import implementacao.crud.ImplementacaoCrud;
import interfacecrud.InterfaceCrud;
import project.model.classes.Entidade;
import srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SrvEntidade srvEntidade;

	public Entidade findUserLogado(String userLogado) throws Exception{
		return super.findInuqueByProperty(Entidade.class, userLogado, "ent_login", " and entity.ent_inativo is false");
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}
	
	public void updateUltimoAcessoUser(String name) {
		//srvEntidade = new SrvEntidadeImpl();
		srvEntidade.updateUltimoAcessoUser(name);
	}

	public List<Entidade> pesquisarPorNome(String nome) throws Exception {
		return (List<Entidade>) getSession().createQuery("select e from Entidade e where e.ent_nomefantasia like '%"+nome+"%'").list();
	}

	public boolean existeCpf(String cpf) throws Exception {
		return super.findListByQueryDinamica(" from Entidade where cpf = '"+cpf+"'").size() > 0;
	}

}

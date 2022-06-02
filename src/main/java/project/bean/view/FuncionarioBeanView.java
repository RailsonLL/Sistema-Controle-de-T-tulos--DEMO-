package project.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import interfacecrud.InterfaceCrud;
import project.bean.geral.BeanManagedViewAbstract;
import project.carregamento.lazy.CarregamentoLazyListForObject;
import project.geral.controller.EntidadeController;
import project.model.classes.Cidade;
import project.model.classes.Entidade;
import project.util.all.Menssagens;

@Controller
@Scope("session")
@ManagedBean(name = "funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;
	
	private String urlFind = "/cadastro/find_funcionario.jsf?faces-redirect=true";
	private String url = "/cadastro/cad_funcionario.jsf?faces-redirect=true";

	private Entidade objetoSelecionado = new Entidade();
	@Autowired
	private ContextoBean contextoBean;
	@Autowired
	private EntidadeController entidadeController;
	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	
	
	@Override
	protected Class<?> getClassImplement() {
		// TODO Auto-generated method stub
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		// TODO Auto-generated method stub
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return " and entity.tipoEntidade = 'FUNCIONARIO' ";
	}
	
	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}
	
	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}
	
	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}
	
	@Override
	public void consultarEntidades() throws Exception {
		objetoSelecionado = new Entidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		
		return urlFind;
	}
	
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnt_codigo() != null && objetoSelecionado.getEnt_codigo() > 0) {
			entidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
	}
	
	@Override
	public String novo() throws Exception {	
		objetoSelecionado = new Entidade();
		list.clean();
		return url;
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		if (!objetoSelecionado.getAcessos().contains("USER")) {
			objetoSelecionado.getAcessos().add("USER");
		}
		
		if (entidadeController.existeCpf(objetoSelecionado.getCpf())) {
			Menssagens.msgSeverityInfo("Este CPF já foi registrado!");
		} else {
			objetoSelecionado =	entidadeController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
				
	}
	
	@Override
	public void saveEdit() throws Exception {
		objetoSelecionado =	entidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Entidade();
		Menssagens.msgSeverityInfo("Atualizado com sucesso!");
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();
		return url;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_funcionario");
		super.setNomeRelatorioSaida("report_funcionario");
		super.setListaDataBeanCollectionReport(entidadeController.findList(Entidade.class));
		return super.getArquivoReport();
	}

}

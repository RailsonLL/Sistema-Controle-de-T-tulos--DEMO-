package project.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import interfacecrud.InterfaceCrud;
import project.bean.geral.BeanManagedViewAbstract;
import project.carregamento.lazy.CarregamentoLazyListForObject;
import project.geral.controller.EntidadeController;
import project.geral.controller.TituloController;
import project.model.classes.Entidade;
import project.model.classes.Titulo;
import project.util.all.Menssagens;

@Controller
@Scope("session")
@ManagedBean(name = "funcionarioBeanView")
public class TituloBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;
	
	private String urlFind = "/cadastro/find_titulo.jsf?faces-redirect=true";
	private String url = "/cadastro/cad_titulo.jsf?faces-redirect=true";

	private Titulo objetoSelecionado = new Titulo();
	@Autowired
	private ContextoBean contextoBean;
	@Autowired
	private TituloController tituloController;
	@Autowired
	private EntidadeController entidadeController;

	private CarregamentoLazyListForObject<Titulo> list = new CarregamentoLazyListForObject<Titulo>();
	
	
	@PostConstruct
	public void init() throws Exception {
		objetoSelecionado.setEnt_codigoabertura(contextoBean.getEntidadeLogada());
	}
	
	@Override
	protected Class<Titulo> getClassImplement() {
		// TODO Auto-generated method stub
		return Titulo.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		// TODO Auto-generated method stub
		return tituloController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}
	
	public Titulo getObjetoSelecionado() {
		return objetoSelecionado;
	}
	
	public void setObjetoSelecionado(Titulo objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	public void setList(CarregamentoLazyListForObject<Titulo> list) {
		this.list = list;
	}
	
	public CarregamentoLazyListForObject<Titulo> getList() {
		return list;
	}
	
	@Override
	public void consultarEntidades() throws Exception {
		objetoSelecionado = new Titulo();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		novo();
		return urlFind;
	}
	
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getTit_codigo() != null && objetoSelecionado.getTit_codigo() > 0) {
			tituloController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Titulo();
			sucesso();
		}
	}
	
	@Override
	public String novo() throws Exception {	
		objetoSelecionado = new Titulo();
		list.clean();
		init();
		return url;
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		objetoSelecionado =	tituloController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Titulo();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		objetoSelecionado =	tituloController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Titulo();
		Menssagens.msgSeverityInfo("Atualizado com sucesso!");
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();
		return url;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_titulo");
		super.setNomeRelatorioSaida("report_titulo");
		super.setListaDataBeanCollectionReport(tituloController.findList(Titulo.class));
		return super.getArquivoReport();
	}
	
	public List<Entidade> pesquisarPagador(String nome) throws Exception{
		return entidadeController.pesquisarPorNome(nome);
	}

}




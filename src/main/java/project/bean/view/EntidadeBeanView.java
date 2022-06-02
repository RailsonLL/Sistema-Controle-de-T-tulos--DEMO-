package project.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import interfacecrud.InterfaceCrud;
import project.bean.geral.BeanManagedViewAbstract;
import project.bean.geral.EntidadeAtualizaSenhaBean;
import project.geral.controller.EntidadeController;
import project.model.classes.Entidade;

@Controller
@Scope(value="session")
@ManagedBean(name="entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;
	
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();

	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}
	
	public Date getUltimoAcesso() throws Exception{
		
		Entidade entidade = (Entidade) contextoBean.getEntidadeLogada();
		System.out.println(entidade.getEnt_ultimoacesso().toString());
		//return entidade.getEnt_ultimoacesso().toString();
		return entidade.getEnt_ultimoacesso();
	}

	@Override
	protected Class<Entidade> getClassImplement() {
		// TODO Auto-generated method stub
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		// TODO Auto-generated method stub
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEntidadeAtualizaSenhaBean(EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}
	
	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}
	
	public void updateSenha() throws Exception {
		Entidade entidadeLogada = (Entidade) contextoBean.getEntidadeLogada();
		if (!entidadeAtualizaSenhaBean.getSenhaAtual().equals(entidadeLogada.getEnt_senha())) {
			addMsg("A senha não é válida");
			return;
		} else if (entidadeAtualizaSenhaBean.getSenhaAtual().equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
			addMsg("A nova senha precisa ser diferente a atual. Informe uma nova senha.");
			return;
		} else if (!entidadeAtualizaSenhaBean.getNovaSenha().equals(entidadeAtualizaSenhaBean.getConfirmaSenha())) {
			addMsg("Confirmação de senha incorreta! Repita a senha informada."); return;
		} else {
			entidadeLogada.setEnt_senha(entidadeAtualizaSenhaBean.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController.findByPorId(Entidade.class, entidadeLogada.getEnt_codigo());
			if (entidadeLogada.getEnt_senha().equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
				sucesso();
			} else {
				addMsg("Não foi possível atualizar a senha");
				error();
			}
		}
	}
	
	/*public Date getUltimoAcesso() throws Exception{

		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}*/
	
}

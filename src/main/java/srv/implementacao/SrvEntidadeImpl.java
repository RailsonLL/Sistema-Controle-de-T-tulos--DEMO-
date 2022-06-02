package srv.implementacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.interfaces.RepositoryEntidade;
import srv.interfaces.SrvEntidade;

@Service
public class SrvEntidadeImpl implements SrvEntidade{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryEntidade repositoryEntidade;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		// TODO Auto-generated method stub
		return repositoryEntidade.getUltimoAcessoEntidadeLogada(name);
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		// TODO Auto-generated method stub
		repositoryEntidade.updateUltimoAcessoUser(login);
	}

	@Override
	public boolean existeUsuario(String ent_login) {
		// TODO Auto-generated method stub
		return repositoryEntidade.existeUsuario(ent_login);
	}

}

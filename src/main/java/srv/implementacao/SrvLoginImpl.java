package srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;

import repository.interfaces.RepositoryLogin;
import srv.interfaces.SrvLogin;

public class SrvLoginImpl implements SrvLogin {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RepositoryLogin repositorioLogin;
	
	@Override
	public boolean autentico(String login, String senha) throws Exception {
		// TODO Auto-generated method stub
		return repositorioLogin.autentico(login, senha);
	}

}

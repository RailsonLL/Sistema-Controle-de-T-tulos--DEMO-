package srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import repository.interfaces.RepositoryCidade;
import srv.interfaces.SrvCidade;

@Service
public class SrvCidadeImpl implements SrvCidade {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryCidade repositoryCidade;
}

package dao.implementacao;

import org.springframework.stereotype.Repository;

import implementacao.crud.ImplementacaoCrud;
import project.model.classes.Cidade;
import repository.interfaces.RepositoryCidade;

@Repository
public class DaoCidade extends ImplementacaoCrud<Cidade> implements RepositoryCidade {


	private static final long serialVersionUID = 1L;

}

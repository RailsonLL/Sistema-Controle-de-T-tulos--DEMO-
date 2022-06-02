package dao.implementacao;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import implementacao.crud.ImplementacaoCrud;
import repository.interfaces.RepositoryLogin;

public class DaoLogin extends ImplementacaoCrud<Object> implements RepositoryLogin {

	/**
	 * verifica se existe o usuário
	 */
	@Override
	public boolean autentico(String login, String senha) throws Exception {

		String sql = "select count(1) as autentica from entidade where ent_login = ? and ent_senha = ? ";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[]{login, senha});
		
		return sqlRowSet.next() ? sqlRowSet.getInt("autentica") > 0 : false;
	}

}

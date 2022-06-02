package interfacecrud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {

	//salva os dados
	void save(T obj) throws Exception;
	
	void persist(T obj) throws Exception;
	
	//salva ou atualiza
	void saveOrUpdate(T obj) throws Exception;
	
	void update(T obj) throws Exception;

	void delete(T obj) throws Exception;
	
	//salva ou atualiza e retorna o objeto em estado persistente
	T merge(T obj) throws Exception;
	
	//carrega a lista de dados de determinada classe
	List<T> findList(Class<T> obj) throws Exception;
	
	//carrega e retorna dados do objeto por id
	T findByPorId(Class<T> entidade, Long id) throws Exception;
	
	//retorna lista de objeto conforme HQL passado por parametro
	List<T> findListByQueryDinamica(String query) throws Exception;
	
	//executa update com o HQL
	void executeUpdateQueryDinamica(String query) throws Exception;
	
	//executa update com o SQL
	void executeUpdateSQLDinamica(String sql) throws Exception;
	
	//limpa a sessão do Hibernate
	void clearSession() throws Exception;
	
	//remove um objeto da sessão do Hibernate
	void evict(Object obj) throws Exception;
	
	Session getSession() throws Exception;
	
	//retorna lista de objetos conforme o sql passado por parametro
	List<?> getListSQLDinamica(String sql) throws Exception;
	
	//JDBC do Spring
	JdbcTemplate getJdbcTemplate();
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleInsert();
	
	Long totalRegistro(String table) throws Exception;
	
	Query obterQuery(String query) throws Exception;
	
	//carregamento dinamico com JSF e Primefaces
	List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;
		
	
}

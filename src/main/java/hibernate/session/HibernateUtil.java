package hibernate.session;

import java.io.Serializable;
import java.sql.Connection;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import implementacao.crud.ConexaoUtil;

//Responsável por estabelecer a conexão com o hibernate
@ApplicationScoped
public class HibernateUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	//Responsável por ler o arquivo de configuração hibernate.cfg.xml
	private static SessionFactory buildSessionFactory() {
		
		
		try {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}

			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory");
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//retorna a sessão do SessionFactory
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	//abre uma nova sessão do SessionFactory
	public static Session openSession() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		
		return sessionFactory.openSession();
	}
	
	//obtém o conection do provedor de conexões configurado
	public static Connection getConnectionProvider() throws Exception{
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
	}
	
	//Connection no InitialContext java:/comp/env/jdbc/datasource
	public static Connection getConnection() throws Exception{
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		return ds.getConnection();
	}
	
	//retorna Datasource JNDI Tomcat
	public DataSource getDataSourceJndi() throws NamingException{
		InitialContext context = new InitialContext();
		return (DataSource) context.lookup(ConexaoUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE);
	}
	
	
	
	
}

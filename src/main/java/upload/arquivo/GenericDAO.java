package upload.arquivo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.c3p0.DriverManagerDataSource;

@Repository
@Transactional
//@EnableTransactionManagement
abstract class GenericDAO<T> {
	
	@Autowired
	@PersistenceContext
	protected EntityManager em;
	
	@Autowired
	protected DriverManagerDataSource dataSource;
	
	@Autowired
	protected LocalContainerEntityManagerFactoryBean myEmf;
	
	@Autowired
	protected JpaTransactionManager transactionManger;

}

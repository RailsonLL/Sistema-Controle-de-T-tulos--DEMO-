package upload.arquivo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("uploadArquivoAula")
@Transactional
public class UploadArquivoAulaDaoImpl extends GenericDAO<ArquivoUploadAula> implements UploadArquivoAulaInterface{

	@Override
	public void salvar(ArquivoUploadAula arquivoUploadAula) {
		super.em.persist(arquivoUploadAula);
		
	}

}

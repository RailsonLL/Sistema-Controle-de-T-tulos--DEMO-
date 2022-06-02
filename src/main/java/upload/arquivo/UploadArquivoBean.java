package upload.arquivo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

@RequestScoped
@ManagedBean(name = "uploadArquivoBean")
public class UploadArquivoBean {

	
	private ArquivoUploadAula arquivoUploadAula = new ArquivoUploadAula();
	
	@ManagedProperty(name = "uploadArquivoAula", value = "#{uploadArquivoAula}")
	private UploadArquivoAulaInterface uploadArquivoAula;
	
	private Part arquivo;

	public ArquivoUploadAula getArquivoUploadAula() {
		return arquivoUploadAula;
	}

	public void setArquivoUploadAula(ArquivoUploadAula arquivoUploadAula) {
		this.arquivoUploadAula = arquivoUploadAula;
	}

	public UploadArquivoAulaInterface getUploadArquivoAula() {
		return uploadArquivoAula;
	}

	public void setUploadArquivoAula(UploadArquivoAulaInterface uploadArquivoAula) {
		this.uploadArquivoAula = uploadArquivoAula;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}
		
	public void upload() {
		
	}
	
	
}

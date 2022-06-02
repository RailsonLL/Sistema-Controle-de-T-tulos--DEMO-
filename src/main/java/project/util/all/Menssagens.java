package project.util.all;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Menssagens extends FacesContext implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Menssagens() {
		// TODO Auto-generated constructor stub
	}
	
	public static void sucesso() {
		msgSeverityInfo(Constante.OPERACAO_REALIZADA_COM_SUCESSO);
	}
	
	public static void erroNaOperacao() {
		msgSeverityError(Constante.ERRO_NA_OPERACAO);
	}
	
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	private static boolean facesContextValido() {
		return getFacesContext() != null;
	}
	
	public static void msgSeverityWarn(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage(msg, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}
	}
	
	public static void msgSeverityError(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage(msg, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}
	
	public static void msgSeverityFatal(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage(msg, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		}
	}
	
	public static void msgSeverityInfo(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage(msg, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
	}
	
	public static void msg(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage(msg, new FacesMessage(msg));
		}
	}
	
	public static void responseOperation(StatusPersistencia statusPer) {
		if (statusPer != null && statusPer.equals(StatusPersistencia.SUCESSO)) {
			sucesso();
		} else if (statusPer != null && statusPer.equals(StatusPersistencia.OBJETO_REFERENCIADO)) {
			msgSeverityFatal(StatusPersistencia.OBJETO_REFERENCIADO.toString());
		} else {
			erroNaOperacao();
		}
	}

}

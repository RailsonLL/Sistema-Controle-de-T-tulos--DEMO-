package report.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import project.util.all.BeanViewAbstract;

@Component
public class BeanReportView extends BeanViewAbstract {

	private static final long serialVersionUID = 1L;

	protected StreamedContent arquivoReport;
	protected int tipoRelatorio;
	protected List<?> listaDataBeanCollectionReport;
	protected HashMap<Object, Object> parametrosRelatorio;
	protected String nomeRelatorioJasper = "default";
	protected String nomeRelatorioSaida = "default";
	
	@Resource
	private ReportUtil reportUtil;
	
	public BeanReportView () {
		parametrosRelatorio = new HashMap<Object, Object>();
		listaDataBeanCollectionReport = new ArrayList();
	}
	
	public ReportUtil getReportUtil() {
		return reportUtil;
	}
	
	public void setReportUtil(ReportUtil reportUtil) {
		this.reportUtil = reportUtil;
	}
	
	public StreamedContent getArquivoReport() throws Exception {
		return getReportUtil().geraRelatorio(getListaDataBeanCollectionReport(), getParametrosRelatorio(), getNomeRelatorioJasper(), getNomeRelatorioSaida(), getTipoRelatorio());
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public List<?> getListaDataBeanCollectionReport() {
		return listaDataBeanCollectionReport;
	}

	public void setListaDataBeanCollectionReport(List<?> listaDataBeanCollectionReport) {
		this.listaDataBeanCollectionReport = listaDataBeanCollectionReport;
	}

	public HashMap<Object, Object> getParametrosRelatorio() {
		return parametrosRelatorio;
	}

	public void setParametrosRelatorio(HashMap<Object, Object> parametrosRelatorio) {
		this.parametrosRelatorio = parametrosRelatorio;
	}

	public String getNomeRelatorioJasper() {
		return nomeRelatorioJasper;
	}

	public void setNomeRelatorioJasper(String nomeRelatorioJasper) {
		if (nomeRelatorioJasper == null || nomeRelatorioJasper.isEmpty()) {
			nomeRelatorioJasper = "default";
		}
		this.nomeRelatorioJasper = nomeRelatorioJasper;
	}

	public String getNomeRelatorioSaida() {
		return nomeRelatorioSaida;
	}

	public void setNomeRelatorioSaida(String nomeRelatorioSaida) {
		if (nomeRelatorioSaida == null || nomeRelatorioSaida.isEmpty()) {
			nomeRelatorioSaida = "default";
		}
		this.nomeRelatorioSaida = nomeRelatorioSaida;
	}
	
	
	
	
}

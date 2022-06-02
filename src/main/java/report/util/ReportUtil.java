package report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String UNDERLINE = "_";
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static final String EXTENSION_ODS = "ods";
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_HTML = "html";
	private static final String EXTENSION_PDF= "pdf";
	
	private String SEPARATOR = File.separator;
	private static final int RELATORIO_PDF = 1;
	private static final int RELATORIO_EXCEL = 2;
	private static final int RELATORIO_HTML = 3;
	private static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
	private static final String PONTO = ".";
	private StreamedContent arquivoRetorno = null;
	private String caminhoArquivoRelatorio = null;
	private JRExporter tipoArquivoExportado = null;
	private String extensaoAquivoExportado = "";
	private String caminhoSubReport_dir = "";
	private File arquivoGerado = null;
	
	public StreamedContent geraRelatorio(List<?> listDataBeanCollectionReport, HashMap parametrosRelatorio, String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRealtorio) throws Exception {
		
		/*
		 * cria lista de collectionDataSource de beans que carregam os dados para o relatório
		 */
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanCollectionReport);
		
		/*
		 * fornece o caminho físico até a pasta que contém os relatórios compilados .jasper
		 */
		FacesContext context = FacesContext.getCurrentInstance();
		context.responseComplete();
		ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
		
		String caminhoRelatorio = scontext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio+SEPARATOR+nomeRelatorioJasper+PONTO+"jasper");
		
		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}
		
		/*caminho para imagens*/
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		//caminho completo até o relatório compilado indicado
		String caminhoAquivoJasper = caminhoRelatorio+SEPARATOR+nomeRelatorioJasper+PONTO+"jasper";
		
		//faz o carregamento do relatório indicado
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoAquivoJasper);
		
		//seta parametro para sub relatórios
		caminhoSubReport_dir = caminhoRelatorio+SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_dir);
		
		//carreo arquivo compilado para a memória
		JasperPrint impJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		switch (tipoRealtorio) {
		case RELATORIO_HTML:
			tipoArquivoExportado = new JRHtmlExporter();
			extensaoAquivoExportado = EXTENSION_HTML;
			break;
		case RELATORIO_EXCEL:
			tipoArquivoExportado = new JRXlsExporter();
			extensaoAquivoExportado = EXTENSION_XLS;
			break;
		case RELATORIO_PLANILHA_OPEN_OFFICE:
			tipoArquivoExportado = new JROdtExporter();
			extensaoAquivoExportado = EXTENSION_ODS;
			break;
		default:
			tipoArquivoExportado = new JRPdfExporter();
			extensaoAquivoExportado = EXTENSION_PDF;
			break;
		}
		
		nomeRelatorioSaida = UNDERLINE+DateUtils.getDateAtualReportName();
		
		//caminho do arquivo exportado
		caminhoArquivoRelatorio = caminhoRelatorio+SEPARATOR+nomeRelatorioSaida+PONTO+extensaoAquivoExportado;
		
		//cria um novo arquivo exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		//preparar a impressão
		tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impJasper);
		
		//nome do arquivo físico a ser impresso/exportado
		tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		//executa a exportação
		tipoArquivoExportado.exportReport();
		
		//remove o arquivo do servidor após relaizado o download
		arquivoGerado.deleteOnExit();
		
		//cria o inputstream para ser usado pelo PrimeFaces
		InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
		
		//faz o retorno para aplicação
		arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/"+extensaoAquivoExportado, nomeRelatorioSaida+PONTO+extensaoAquivoExportado);
		
		return arquivoRetorno;
		
	}
	
}

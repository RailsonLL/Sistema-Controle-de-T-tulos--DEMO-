package project.geral.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import implementacao.crud.ImplementacaoCrud;
import interfacecrud.InterfaceCrud;
import project.model.classes.Titulo;

@Controller
public class TituloController extends ImplementacaoCrud<Titulo> implements InterfaceCrud<Titulo>{

	private static final long serialVersionUID = 1L;

	@RequestMapping("**/gerarGraficoInicial")
	public @ResponseBody String gerarGraficoInicial(@RequestParam(value = "dias") int dias) {
		
		List<Map<String, Object>> titulosUltimoDias = getTituloUltimosDias(dias);
		
		int totalLinhas = titulosUltimoDias.size() + 1;
		
		boolean semDados = false;
		if (totalLinhas <= 1) {
			semDados = true;
		}
		
		String[] dados = new String[totalLinhas];
		int cont = 0;
		
		if (semDados) {
			dados[cont ++] = "[\""+"Sem dados"+"\","+"\""+ 0 +"\","+"\""+ 0 +"\"]";
		} else {
			dados[cont] = "[\""+"Tipo"+"\","+"\""+"Quantidade"+"\","+"\""+"Media"+"\"]";
			cont++;
			
			//PERCARRE OS DADOS DO MAP E ADICIONA EM UM ARRAY DE STRING 
			for(Map<String, Object> objeto : titulosUltimoDias) {
				dados[cont] = "[\""+objeto.get("situacao")+"\","+"\""+objeto.get("quantidade")+"\","+"\""+objeto.get("media_valor")+"\"]";
				cont++;
			}
		}
		
		//RETORNA O ARRAY DE STRING PARA SER LIDO NO JAVASCRIPT QUE GERA OS GRAFICOS
		return Arrays.toString(dados);
	}

	/**
	 * 
	 * RETORNA UM MAP COM OS DADOS CARREGADOS NO SELECT
	 */
	private List<Map<String, Object>> getTituloUltimosDias(int dias) {
		StringBuilder sql = new StringBuilder();

		sql.append("(SELECT COUNT(1) AS QUANTIDADE, TIT_ORIGEM AS SITUACAO, TRUNC(AVG(COALESCE(TIT_VALOR, 0.00)), 2) AS MEDIA_VALOR ");
		sql.append("FROM TITULO ");
		sql.append("WHERE CAST (TIT_DATAHORA AS DATE) >= (CURRENT_DATE - "+dias+") AND CAST(TIT_DATAHORA AS DATE) <= CURRENT_DATE ");
		sql.append("GROUP BY TIT_ORIGEM ");
		sql.append("UNION ");
		sql.append("SELECT COUNT(1) AS QUANTIDADE, ");
		sql.append("CASE WHEN TIT_BAIXADO THEN 'BAIXADO' ELSE 'EM ABERTO' END AS SITUACAO, ");
		sql.append("TRUNC(AVG(COALESCE(TIT_VALOR, 0.00)), 2) AS MEDIA_VALOR ");
		sql.append("FROM TITULO ");
		sql.append("WHERE CAST(TIT_DATAHORA AS DATE) >= (CURRENT_DATE - "+dias+") AND CAST(TIT_DATAHORA AS DATE) <= CURRENT_DATE ");
		sql.append("GROUP BY TIT_BAIXADO ");
		sql.append("UNION ");
		sql.append("SELECT COUNT(1) AS QUANTIDADE, TIT_TIPO AS SITUACAO, TRUNC(AVG(COALESCE(TIT_VALOR, 0.00)), 2) AS MEDIA_VALOR ");
		sql.append("FROM TITULO ");
		sql.append("WHERE CAST (TIT_DATAHORA AS DATE) >= (CURRENT_DATE - "+dias+") AND CAST(TIT_DATAHORA AS DATE) <= CURRENT_DATE ");
		sql.append("GROUP BY TIT_TIPO) ORDER BY QUANTIDADE, MEDIA_VALOR ");
		return super.getSimpleJdbcTemplate().queryForList(sql.toString());
	}
	
}

package project.enums;

public enum CondicaoPesquisa {
	
	CONTEM("Contém"),
	INICIA("Inicia com"),
	TERMINA_COM("Tremina com"),
	IGUAL_A("Igual");
	
	private String condicao;
	
	private CondicaoPesquisa(String condicao) {
		this.condicao = condicao;
	}
	
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	
	public String getCondicao() {
		return condicao;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.condicao;
	}

}

package project.util.all;

public enum StatusPersistencia {

	ERRO("Erro"),
	SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Esse objeto não pode ser apagado por possuir referências ao mesmo");
	
	private String name;
	
	private StatusPersistencia(String s) {
		// TODO Auto-generated constructor stub
		this.name = s;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}

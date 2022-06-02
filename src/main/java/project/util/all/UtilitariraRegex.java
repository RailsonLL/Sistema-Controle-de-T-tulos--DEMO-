package project.util.all;

public class UtilitariraRegex {
	
	/**
	 * Retira acentos da string passada no parametro
	 * 
	 */
	public String retiraAcentos(String string) {
		String aux = new String(string);
		aux = aux.replaceAll("[èëÈéêÉÊË]", "e");
		aux = aux.replaceAll("[ùüÙúûÚÛÜ]", "u");
		aux = aux.replaceAll("[ìïÌíîÍÎÏ]", "i");
		aux = aux.replaceAll("[àäÀáâÁÂÄ]", "a");
		aux = aux.replaceAll("[òöÒóôÓÔÖ]", "o");
		return aux;
	}

}

package br.pucminas.api.constants;

public final class Mensagem {
	
	private static final String CT_ADM = "Contate o reponsável técnico.";
	
	public static class ERRO {
		public static String SALVAR = "Erro ao salvar os dados." + CT_ADM;
		public static String APAGAR = "Erro ao apagar o(s) registro(s)" + CT_ADM;
		public static String CAMPO_OBRIGATORIO = "Campo obrigatório não informado: ";
	}
	
	
}

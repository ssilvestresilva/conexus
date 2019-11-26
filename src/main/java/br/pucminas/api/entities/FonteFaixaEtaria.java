package br.pucminas.api.entities;

public class FonteFaixaEtaria {

	private Long idade;
	private Long quantidade;

	public FonteFaixaEtaria(Long idade, Long quantidade) {
		this.idade = idade;
		this.quantidade = quantidade;
	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
}

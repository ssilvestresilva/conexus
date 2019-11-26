package br.pucminas.api.entities;

public class FonteAprovacoes {

	private Long qtdAprovados;
	private Long qtdReprovados;

	public FonteAprovacoes(Long qtdAprovados, Long qtdReprovados) {
		this.qtdAprovados = qtdAprovados;
		this.qtdReprovados = qtdReprovados;
	}

	public Long getQtdAprovados() {
		return qtdAprovados;
	}

	public void setQtdAprovados(Long qtdAprovados) {
		this.qtdAprovados = qtdAprovados;
	}

	public Long getQtdReprovados() {
		return qtdReprovados;
	}

	public void setQtdReprovados(Long qtdReprovados) {
		this.qtdReprovados = qtdReprovados;
	}

}

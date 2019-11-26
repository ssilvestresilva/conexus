package br.pucminas.api.dtos;

import java.util.Optional;
import javax.validation.constraints.NotNull;

public class CursoDto {

	private Optional<Long> id = Optional.empty();
	private String descricao;
	private String segmento;
	private String periodo;
	private Boolean ativo;

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@NotNull( message = "Campo obrigatório não informado: Descrição" )
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotNull( message = "Campo obrigatório não informado: Segmento" )
	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	@NotNull( message = "Campo obrigatório não informado: Período" )
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@NotNull( message = "Campo obrigatório não informado: Status" )
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo != null ? ativo : true;
	}

	@Override
	public String toString() {
		return "CursoDto [id=" + id + ", descricao=" + descricao + ", segmento=" + segmento + ", periodo=" + periodo
				+ ", ativo=" + ativo + "]";
	}

}

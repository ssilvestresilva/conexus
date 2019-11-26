package br.pucminas.api.dtos;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class ResultadoDto {

	private String aluno;
	private String curso;
	private String instituicao;
	private String periodo;
	private BigDecimal resultado;
	private Date lastDataBase;
	private Timestamp dtaCriacao;

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}

	public Date getLastDataBase() {
		return lastDataBase;
	}

	public void setLastDataBase(Date lastDataBase) {
		this.lastDataBase = lastDataBase;
	}

	public Timestamp getDtaCriacao() {
		return dtaCriacao;
	}

	public void setDtaCriacao(Timestamp dtaCriacao) {
		this.dtaCriacao = dtaCriacao;
	}

}

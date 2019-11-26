package br.pucminas.api.dtos;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import br.pucminas.api.entities.Curso;
import br.pucminas.api.utils.Utils;

public class AlunoDto {

	private Optional<Long> id = Optional.empty();
	private String nome;
	private String cpf;
	private String dtaNascimento;
	private String endereco;
	private Boolean ativo;
	private Set<Curso> cursos = new HashSet<>();

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@NotNull( message = "Campo obrigatório não informado: Status" )
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull( message = "Campo obrigatório não informado: Status" )
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotNull( message = "Campo obrigatório não informado: Status" )
	public String getDtaNascimento() {
		return dtaNascimento;
	}

	public void setDtaNascimento(String dtaNascimento) {
		this.dtaNascimento = dtaNascimento;
	}

	@NotNull( message = "Campo obrigatório não informado: Status" )
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@NotNull( message = "Campo obrigatório não informado: Status" )
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@NotNull( message = "Campo obrigatório não informado: Curso" )
	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

		@AssertTrue( message = "Formato de data inválido para: Data de Nascimento" )
	public boolean isDataValida() {
		try {
			Utils.converterDataLocal(this.getDtaNascimento());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "AlunoDto [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dtaNascimento=" + dtaNascimento
				+ ", endereco=" + endereco + "]";
	}

}

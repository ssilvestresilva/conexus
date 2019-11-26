package br.pucminas.api.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.pucminas.api.utils.Utils;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 2988578591926756875L;

	private Long id;
	private String nome;
	private String cpf;
	private Date dtaNascimento;
	private String endereco;
	private Boolean ativo;
	
	//@JsonManagedReference
	private Set<Curso> cursos = new HashSet<>();
	
	@JsonIgnore
	private Set<Resultado> resultados = new HashSet<>();

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(	
		name = "curso_aluno",
		joinColumns = @JoinColumn(name = "id_aluno"),
		inverseJoinColumns = @JoinColumn(name = "id_curso")
	)
	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}
	
	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(Set<Resultado> resultados) {
		this.resultados = resultados;
	}

	@NaturalId
	@Column( name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column( name = "cpf", unique = true, nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column( name = "dta_nascimento", nullable = false)
	public Date getDtaNascimento() {
		return dtaNascimento;
	}

	public void setDtaNascimento(Date dtaNascimento) throws Exception {
		this.dtaNascimento = dtaNascimento;
	}

	@Column( name = "endereco", nullable = false )
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Column( name = "ativo", nullable = false )
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Aluno comId(Long id) {
		this.setId(id);
		return this;
	}
	
	public Aluno comNome(String nome) {
		this.setNome(nome);
		return this;
	}
	
	public Aluno comCpf(String cpf) {
		this.setCpf(cpf);
		return this;
	}
	
	public Aluno comDtaNascimento(String dtaNascimento) throws Exception {
		this.setDtaNascimento(Utils.converterDataLocal(dtaNascimento));
		return this;
	}
	
	public Aluno comEndereco(String endereco) {
		this.setEndereco(endereco);
		return this;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dtaNascimento=" + dtaNascimento
				+ ", endereco=" + endereco + "]";
	}

}

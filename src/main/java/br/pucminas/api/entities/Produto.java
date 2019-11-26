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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.pucminas.api.utils.Utils;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 2988578591926756875L;

	private Long id;
	private String nome;
	private String cpf;
	private Date dtaNascimento;
	private String endereco;
	private Boolean ativo;
//	id serial NOT NULL,
//	cod_produto int4 NOT NULL,
//	descricao varchar(100) NOT NULL,
//	vlr_distribuidor NUMERIC(5,2) NULL DEFAULT 0,
//	vlr_admin NUMERIC(5,2) NULL DEFAULT 0,
//	quantidade int4 NOT NULL,
//	dta_criacao TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
//	dta_atualizacao TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
//	ativo bool NOT NULL DEFAULT false,
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
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	public Produto comId(Long id) {
		this.setId(id);
		return this;
	}
	
	public Produto comNome(String nome) {
		this.setNome(nome);
		return this;
	}
	
	public Produto comCpf(String cpf) {
		this.setCpf(cpf);
		return this;
	}
	
	public Produto comDtaNascimento(String dtaNascimento) throws Exception {
		this.setDtaNascimento(Utils.converterDataLocal(dtaNascimento));
		return this;
	}
	
	public Produto comEndereco(String endereco) {
		this.setEndereco(endereco);
		return this;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dtaNascimento=" + dtaNascimento
				+ ", endereco=" + endereco + "]";
	}

}

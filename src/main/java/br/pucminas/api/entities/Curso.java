package br.pucminas.api.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

	private static final long serialVersionUID = 8913272793070855004L;

	private Long id;
	private String descricao;
	private String segmento;
	private String periodo;
	private Boolean ativo;

	@JsonBackReference
	private Set<Aluno> alunos = new HashSet<>();

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "cursos")
	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Column( name = "descricao", nullable = false )
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column( name = "segmento", nullable = false )
	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	@Column( name = "periodo", nullable = false )
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	@Column( name = "ativo", nullable = false )
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Curso comId(Long id) {
		this.setId(id);
		return this;
	}
	
	public Curso comDescricao(String descricao) {
		this.setDescricao(descricao);
		return this;
	}
	
	public Curso comSegmento(String segmento) {
		this.setSegmento(segmento);
		return this;
	}
	
	public Curso comPeriodo(String periodo) {
		this.setPeriodo(periodo);
		return this;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", descricao=" + descricao + ", segmento=" + segmento + ", periodo=" + periodo
				+ ", status=" + ativo + "]";
	}
	
}

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.pucminas.api.utils.Utils;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 2988578591926756875L;

	private Long id;
	private Integer codProduto;
	private String descricao;
	private Double vlrDistribuidor;
	private Double vlrAdmin;
	private Integer quantidade;
	private Date dtaCriacao;
	private Date dtaAtualizacao;
	private Boolean ativo;
	
	@JsonBackReference
	private Set<Compras> compras = new HashSet<>();
	
	@JsonIgnore
	private Set<ResultadoProduto> resultados = new HashSet<>();

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<ResultadoProduto> getResultados() {
		return resultados;
	}

	public void setResultados(Set<ResultadoProduto> resultados) {
		this.resultados = resultados;
	}

	@NaturalId
	@Column( name = "cod_produto", unique = true, nullable = false)
	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "produtos")
	public Set<Compras> getCompras() {
		return compras;
	}
	
	public void setCompras(Set<Compras> compras) {
		this.compras = compras;
	}

	@Column( name = "descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column( name = "vlr_distribuidor")
	public Double getVlrDistribuidor() {
		return vlrDistribuidor;
	}

	public void setVlrDistribuidor(Double vlrDistribuidor) {
		this.vlrDistribuidor = vlrDistribuidor;
	}

	@Column( name = "vlr_admin")
	public Double getVlrAdmin() {
		return vlrAdmin;
	}

	public void setVlrAdmin(Double vlrAdmin) {
		this.vlrAdmin = vlrAdmin;
	}

	@Column( name = "quantidade", nullable = false)
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Column( name = "dta_criacao", nullable = false)
	public Date getDtaCriacao() {
		return dtaCriacao;
	}

	public void setDtaCriacao(Date dtaCriacao) throws Exception {
		this.dtaCriacao = dtaCriacao;
	}

	@Column( name = "dta_atualizacao", nullable = false)
	public Date getDtaAtualizacao() {
		return dtaAtualizacao;
	}

	public void setDtaAtualizacao(Date dtaAtualizacao) throws Exception {
		this.dtaAtualizacao = dtaAtualizacao;
	}

	@Column( name = "ativo", nullable = false)
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
	
	public Produto comCodProduto(Integer codProduto) {
		this.setCodProduto(codProduto);
		return this;
	}
	
	public Produto comDescricao(String descricao) {
		this.setDescricao(descricao);
		return this;
	}

	public Produto comVlrDistribuidor(Double vlrDistribuidor) {
		this.setVlrDistribuidor(vlrDistribuidor);
		return this;
	}
	
	public Produto comVlrAdmin(Double vlrAdmin) {
		this.setVlrAdmin(vlrAdmin);
		return this;
	}
	
	public Produto comDtaCriacao(String dtaCriacao) throws Exception {
		this.setDtaCriacao(Utils.converterDataLocal(dtaCriacao));
		return this;
	}

	public Produto comDtaAtualizacao(String dtaAtualizacao) throws Exception {
		this.setDtaAtualizacao(Utils.converterDataLocal(dtaAtualizacao));
		return this;
}
	
	public Produto comQuantidade(Integer quantidade) {
		this.setQuantidade(quantidade);
		return this;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", codProduto=" + codProduto + ", descricao=" 
	+ descricao + ", vlrDistribuidor=" + vlrDistribuidor + ", vlrAdmin=" + vlrAdmin +
	", + quantidade=" + quantidade + "]";
	}

}

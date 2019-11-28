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
@Table(name = "compras")
public class Compras implements Serializable {

	private static final long serialVersionUID = 2988578591926756875L;

	private Long id;
	private Integer codCompra;
	private Integer idUsuario;
	private String nome;
	private Integer idProduto;
	private Integer codProduto;
	private Double vlrCompra;
	private Integer quantidade;
	private Date dtaPedido;
	private String formaPagamento;
	private Date dtaPagamento;
	private String statusPagamento;
	private Date dtaEnvio;
	private String statusEnvio;
	
	@JsonIgnore
	private Set<ResultadoCompras> resultados = new HashSet<>();

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<ResultadoCompras> getResultados() {
		return resultados;
	}

	public void setResultados(Set<ResultadoCompras> resultados) {
		this.resultados = resultados;
	}

	@NaturalId
	@Column( name = "cod_compra", nullable = false)
	public Integer getCodCompra() {
		return codCompra;
	}

	public void setCodCompra(Integer codCompra) {
		this.codCompra = codCompra;
	}
	
	@Column( name = "id_usuario", nullable = false)
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column( name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column( name = "id_produto", nullable = false)
	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	@Column( name = "cod_produto", nullable = false)
	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	@Column( name = "vlr_compra")
	public Double getVlrCompra() {
		return vlrCompra;
	}

	public void setVlrCompra(Double vlrCompra) {
		this.vlrCompra = vlrCompra;
	}

	@Column( name = "quantidade", nullable = false)
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Column( name = "dta_pedido", nullable = false)
	public Date getDtaPedido() {
		return dtaPedido;
	}

	public void setDtaPedido(Date dtaPedido) throws Exception {
		this.dtaPedido = dtaPedido;
	}

	@Column( name = "forma_pagamento", nullable = false)
	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	@Column( name = "dta_pagamento", nullable = false)
	public Date getDtaPagamento() {
		return dtaPagamento;
	}

	public void setDtaPagamento(Date dtaPagamento) throws Exception {
		this.dtaPagamento = dtaPagamento;
	}

	@Column( name = "status_pagamento", nullable = false)
	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	
	@Column( name = "dta_envio", nullable = false)
	public Date getDtaEnvio() {
		return dtaEnvio;
	}

	public void setDtaEnvio(Date dtaEnvio) throws Exception {
		this.dtaEnvio = dtaEnvio;
	}
	
	@Column( name = "status_envio", nullable = false)
	public String getStatusEnvio() {
		return statusEnvio;
	}

	public void setStatusEnvio(String statusEnvio) {
		this.statusEnvio = statusEnvio;
	}
	
	public Compras comId(Long id) {
		this.setId(id);
		return this;
	}
	
	public Compras comIdUsuario(Integer idUsuario) {
		this.setIdUsuario(idUsuario);
		return this;
	}
	
	public Compras comNome(String nome) {
		this.setNome(nome);
		return this;
	}
	
	public Compras comIdProduto(Integer codIdProduto) {
		this.setIdProduto(codIdProduto);
		return this;
	}
	
	public Compras comCodCompra(Integer codCompra) {
		this.setCodCompra(codCompra);
		return this;
	}
	
	public Compras comVlrCompra(Double vlrCompra) {
		this.setVlrCompra(vlrCompra);
		return this;
	}
	
	public Compras comQuantidade(Integer quantidade) {
		this.setQuantidade(quantidade);
		return this;
	}
	
	public Compras comDtaPedido(String dtaPedido) throws Exception {
		this.setDtaPedido(Utils.converterDataLocal(dtaPedido));
		return this;
	}
	
	public Compras comFormaPagamento(String formaPagamento) {
		this.setFormaPagamento(formaPagamento);
		return this;
	}
	
	public Compras comDtaPagamento(String dtaPagamento) throws Exception {
		this.setDtaPagamento(Utils.converterDataLocal(dtaPagamento));
		return this;
	}
	
	public Compras comStatusPagamento(String statusPagamento) {
		this.setStatusPagamento(statusPagamento);
		return this;
	}
	
	public Compras comDtaEnvio(String dtaEnvio) throws Exception {
		this.setDtaEnvio(Utils.converterDataLocal(dtaEnvio));
		return this;
	}
	
	public Compras comStatusEnvio(String statusEnvio) {
		this.setStatusEnvio(statusEnvio);
		return this;
	}
	
	@Override
	public String toString() {
		return "Compra [id=" + id + ", codCompra=" + codCompra + ", nome=" 
	+ nome + ", vlrCompra=" + vlrCompra + 
	", + quantidade=" + quantidade + "]";
	}

}

package br.pucminas.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "resultado_produto")
public class ResultadoProduto implements Serializable {

	private static final long serialVersionUID = -713545263599116642L;

	private Long id;
	private Produto produto;
	private BigDecimal resultado;
	
	@JsonProperty("dta_criacao")
	private Timestamp dtaCriacao;
	
	@JsonProperty("last_data_base")
	private Date lastDataBase;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "id_produto")
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Column(name = "resultado", nullable = false)
	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}
	
	@Column(name = "dta_criacao")
	public Timestamp getDtaCriacao() {
		return dtaCriacao;
	}

	public void setDtaCriacao(Timestamp dtaCriacao) {
		this.dtaCriacao = dtaCriacao;
	}
	
	@Column(name = "last_data_base", nullable = false)
	public Date getLastDataBase() {
		return lastDataBase;
	}

	public void setLastDataBase(Date lastDataBase) {
		this.lastDataBase = lastDataBase;
	}

	@PrePersist
    public void prePersist() {
        final Timestamp atual = Timestamp.valueOf(LocalDateTime.now());
        dtaCriacao = atual;
    }

	@Override
	public String toString() {
		return "Resultado [id=" + id + ", produto=" + produto + resultado + "]";
	}

}

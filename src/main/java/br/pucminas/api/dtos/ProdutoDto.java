package br.pucminas.api.dtos;

import java.util.Optional;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import br.pucminas.api.utils.Utils;

public class ProdutoDto {

	private Optional<Long> id = Optional.empty();
	private Integer codProduto;
	private String descricao;
	private Double vlrDistribuidor;
	private Double vlrAdmin;
	private Integer quantidade;
	private String dtaCriacao;
	private String dtaAtualizacao;
	private Boolean ativo;

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@NotNull( message = "Campo obrigatório não informado: Código Produto" )
	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	@NotNull( message = "Campo obrigatório não informado: Descrição" )
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getVlrDistribuidor() {
		return vlrDistribuidor;
	}

	public void setVlrDistribuidor(Double vlrDistribuidor) {
		this.vlrDistribuidor = vlrDistribuidor;
	}
	
	public Double getVlrAdmin() {
		return vlrAdmin;
	}

	public void setVlrAdmin(Double vlrAdmin) {
		this.vlrAdmin = vlrAdmin;
	}

	@NotNull( message = "Campo obrigatório não informado: Quantidade" )
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@NotNull( message = "Campo obrigatório não informado: Data Criação" )
	public String getDtaCriacao() {
		return dtaCriacao;
	}

	public void setDtaCriacao(String dtaCriacao) {
		this.dtaCriacao = dtaCriacao;
	}
	
	@NotNull( message = "Campo obrigatório não informado: Data Atualização" )
	public String getDtaAtualizacao() {
		return dtaAtualizacao;
	}

	public void setDtaAtualizacao(String dtaAtualizacao) {
		this.dtaAtualizacao = dtaAtualizacao;
	}
	
	@NotNull( message = "Campo obrigatório não informado: Status" )
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@AssertTrue( message = "Formato de data inválido para: Data de Criação" )
	public boolean isDataCriacaoValida() {
		try {
			Utils.converterDataLocal(this.getDtaCriacao());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@AssertTrue( message = "Formato de data inválido para: Data de Atualização" )
	public boolean isDataAtualizacaoValida() {
		try {
			Utils.converterDataLocal(this.getDtaAtualizacao());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", codProduto=" + codProduto + ", descricao=" 
				+ descricao + ", vlrDistribuidor=" + vlrDistribuidor + ", vlrAdmin=" + vlrAdmin +
				", + quantidade=" + quantidade + "]";
	}

}

package br.pucminas.api.services;

import java.util.List;

import br.pucminas.api.entities.Produto;

public interface ProdutoService {

	List<Produto> listar();
	Produto findByCodProduto(Integer codProduto);
	
	Produto inserir(Produto prod);
	Produto atualizar(Produto prod);
}

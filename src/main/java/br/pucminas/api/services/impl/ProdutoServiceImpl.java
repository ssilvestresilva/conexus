package br.pucminas.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.api.entities.Produto;
import br.pucminas.api.repositories.ProdutoRepository;
import br.pucminas.api.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto inserir(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto atualizar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto findByCodProduto(Integer codProduto) {
		return produtoRepository.findByCodProduto(codProduto);
	}
}

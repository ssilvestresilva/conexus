package br.pucminas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.api.entities.Produto;

@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByCodProduto(Integer codProduto);
	
}


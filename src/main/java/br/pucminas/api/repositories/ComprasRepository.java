package br.pucminas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.api.entities.Compras;

@Transactional(readOnly = true)
public interface ComprasRepository extends JpaRepository<Compras, Long> {

	Compras findByCodCompra(Integer codCompra);
	
}


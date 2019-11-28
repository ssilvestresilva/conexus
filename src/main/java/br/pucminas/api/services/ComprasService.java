package br.pucminas.api.services;

import java.util.List;

import br.pucminas.api.entities.Compras;

public interface ComprasService {

	List<Compras> listar();
	Compras findByCodCompra(Integer codCompra);
}

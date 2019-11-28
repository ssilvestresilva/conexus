package br.pucminas.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.api.entities.Compras;
import br.pucminas.api.repositories.ComprasRepository;
import br.pucminas.api.services.ComprasService;

@Service
public class ComprasServiceImpl implements ComprasService {

	@Autowired
	private ComprasRepository comprasRepository;

	@Override
	public List<Compras> listar() {
		return comprasRepository.findAll();
	}

	@Override
	public Compras findByCodCompra(Integer codCompra) {
		return comprasRepository.findByCodCompra(codCompra);
	}
}

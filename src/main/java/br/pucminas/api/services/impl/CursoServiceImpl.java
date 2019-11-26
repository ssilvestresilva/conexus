package br.pucminas.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.api.entities.Curso;
import br.pucminas.api.repositories.CursoRepository;
import br.pucminas.api.services.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public List<Curso> listar() {
		return cursoRepository.findAll();
	}
	
	@Override
	public Curso inserir(Curso curso) {
		return cursoRepository.save(curso);
	}
	
	@Override
	public Curso atualizar(Curso curso) {
		return cursoRepository.save(curso);
	}

}

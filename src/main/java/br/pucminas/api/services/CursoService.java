package br.pucminas.api.services;

import java.util.List;

import br.pucminas.api.entities.Curso;

public interface CursoService {

	List<Curso> listar();
	
	Curso inserir(Curso curso);
	Curso atualizar(Curso curso);
	
}

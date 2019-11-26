package br.pucminas.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.api.entities.Aluno;
import br.pucminas.api.repositories.AlunoRepository;
import br.pucminas.api.services.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	@Override
	public Aluno inserir(Aluno curso) {
		return alunoRepository.save(curso);
	}

	@Override
	public Aluno atualizar(Aluno curso) {
		return alunoRepository.save(curso);
	}

	@Override
	public Aluno findByCpf(String cpf) {
		return alunoRepository.findByCpf(cpf);
	}

}

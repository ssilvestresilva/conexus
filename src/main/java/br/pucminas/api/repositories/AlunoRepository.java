package br.pucminas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.api.entities.Aluno;

@Transactional(readOnly = true)
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	Aluno findByCpf(String cpf);

}

package br.pucminas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.api.entities.Curso;

@Transactional(readOnly = true)
public interface CursoRepository extends JpaRepository<Curso, Long>{

}

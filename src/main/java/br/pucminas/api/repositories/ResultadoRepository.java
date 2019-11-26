package br.pucminas.api.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.api.entities.FonteAprovacoes;
import br.pucminas.api.entities.Resultado;

@Transactional(readOnly = true)
public interface ResultadoRepository extends CrudRepository<Resultado, Long>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Resultado WHERE last_data_base = ?1")
	void removeByLastDataBase(Date lastDataBase);
	
	@Query("select new br.pucminas.api.entities.FonteAprovacoes(\r\n" + 
			"		sum(case when resultado >= 65 then 1 else 0 end) as qtdAprovados,\r\n" + 
			"		sum(case when resultado < 65 then 1 else 0 end) as qtdReprovados)\r\n" + 
			"from Resultado")
	FonteAprovacoes fonteAprovacoes();
	
	@Query(value = "select (CURRENT_DATE - aluno.dta_nascimento) / 365 as idade,\r\n" + 
			"		count(1) as quantidade\r\n" + 
			"from aluno\r\n" + 
			"inner join curso_aluno ca on ca.id_aluno = aluno.id\r\n" + 
			"inner join curso on curso.id = ca.id_curso\r\n" + 
			"group by 1 order by 1", nativeQuery = true)
	List<Object[]> fonteFaixaEtaria();
	
	@Query(value = "select avg(resultado) as media\r\n" + 
			"	from resultado\r\n" + 
			"	group by id_aluno", nativeQuery = true)
	List<Object> fonteMediaNotas();
	
	@Query(value = "select curso.segmento, count(1)\r\n" + 
			"from aluno\r\n" + 
			"inner join curso_aluno ca on ca.id_aluno = aluno.id\r\n" + 
			"inner join curso on curso.id = ca.id_curso\r\n" + 
			"group by curso.segmento", nativeQuery = true)
	List<Map<String, Object>> fontePredominancia();
	
	Page<Resultado> findAll(Pageable pageable);
	
}

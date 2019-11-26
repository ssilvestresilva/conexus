package br.pucminas.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.api.constants.Mensagem;
import br.pucminas.api.constants.Validador;
import br.pucminas.api.dtos.CursoDto;
import br.pucminas.api.entities.Curso;
import br.pucminas.api.repositories.CursoRepository;
import br.pucminas.api.response.Response;
import br.pucminas.api.services.CursoService;
import br.pucminas.api.utils.Utils;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = "*")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@PostMapping
	public ResponseEntity<Response<Curso>> inserir(@RequestBody CursoDto cursoDto) {
		Response<Curso> response = new Response<Curso>();
		List<String> erros = Validador.validate(cursoDto);
		
		if (!erros.isEmpty()) {
			response.setErro(erros.get(0));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {

			Curso curso = this.fromDto(cursoDto);
			response.setData(cursoRepository.save(curso));

		} catch (Exception e) {
			response.setErro(Mensagem.ERRO.SALVAR);
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	private ResponseEntity<Response<Curso>> atualizar(@RequestBody CursoDto cursoDto) {
		Response<Curso> response = new Response<Curso>();
		List<String> erros = Validador.validate(cursoDto);
		
		if (!erros.isEmpty()) {
			response.setErro(erros.get(0));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {

			Curso curso = this.fromDto(cursoDto);
			response.setData(cursoService.atualizar(curso));

		} catch (Exception e) {
			response.setErro(Mensagem.ERRO.SALVAR);
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping
	public Page<Curso> listar(@RequestParam(required = false) Long id,
							  @RequestParam(required = false) String descricao,
							  @RequestParam(required = false) String segmento,
							  @RequestParam(required = false) String periodo,
							  Pageable pageable) {
		
		Curso curso = new Curso()
				.comId(id)
				.comDescricao(descricao)
				.comSegmento(segmento)
				.comPeriodo(periodo);

		return cursoRepository.findAll(Example.of(curso, Utils.MATCHER_CONTAINING), pageable);
	}
	
	@GetMapping("/todos")
	public List<Curso> listarTodos() {
		return cursoRepository.findAll();
	}
	
	private Curso fromDto(CursoDto cursoDto) {
		Curso curso = new Curso();
		cursoDto.getId().ifPresent(id -> curso.setId(id));
		curso.setDescricao(cursoDto.getDescricao());
		curso.setSegmento(cursoDto.getSegmento());
		curso.setPeriodo(cursoDto.getPeriodo());
		curso.setAtivo(cursoDto.getAtivo());
		
		return curso;
	}
}
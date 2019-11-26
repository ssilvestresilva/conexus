package br.pucminas.api.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.api.constants.Mensagem;
import br.pucminas.api.constants.Validador;
import br.pucminas.api.dtos.AlunoDto;
import br.pucminas.api.entities.Aluno;
import br.pucminas.api.entities.Curso;
import br.pucminas.api.repositories.AlunoRepository;
import br.pucminas.api.repositories.CursoRepository;
import br.pucminas.api.response.Response;
import br.pucminas.api.services.AlunoService;
import br.pucminas.api.utils.Utils;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public Page<Aluno> listar(@RequestParam(required = false) Long id,
							  @RequestParam(required = false) String nome,
							  @RequestParam(required = false) String cpf,
							  @RequestParam(required = false) String dtaNascimento,
							  @RequestParam(required = false) String endereco,
							  Pageable pageable) throws Exception {
		
		Aluno aluno = new Aluno()
				.comId(id)
				.comNome(nome)
				.comCpf(cpf)
				.comDtaNascimento(dtaNascimento)
				.comEndereco(endereco);

		return alunoRepository.findAll(Example.of(aluno, Utils.MATCHER_CONTAINING), pageable);
	}
	
	@PostMapping
	public ResponseEntity<Response<Aluno>> inserir(@RequestBody AlunoDto alunoDto) {
		Response<Aluno> response = new Response<Aluno>();
		List<String> erros = Validador.validate(alunoDto);
		
		if (!erros.isEmpty()) {
			response.setErro(erros.get(0));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			Aluno aluno = this.fromDto(alunoDto);
			carregarCursos(aluno, alunoDto);
			response.setData(alunoRepository.save(aluno));
		} catch (Exception e) {
			response.setErro(Mensagem.ERRO.SALVAR);
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	private ResponseEntity<Response<Aluno>> atualizar(@RequestBody AlunoDto alunoDto) {
		Response<Aluno> response = new Response<Aluno>();
		List<String> erros = Validador.validate(alunoDto);
		
		if (!erros.isEmpty()) {
			response.setErro(erros.get(0));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			Aluno aluno = this.fromDto(alunoDto);
			carregarCursos(aluno, alunoDto);
			response.setData(alunoService.atualizar(aluno));
		} catch (Exception e) {
			response.setErro(Mensagem.ERRO.SALVAR);
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	private Aluno fromDto(AlunoDto alunoDto) throws Exception {
		Aluno aluno = new Aluno();
		alunoDto.getId().ifPresent(id -> aluno.setId(id));
		aluno.setNome(alunoDto.getNome());
		aluno.setCpf(alunoDto.getCpf());
		aluno.setDtaNascimento(Utils.converterDataLocal(alunoDto.getDtaNascimento()));
		aluno.setEndereco(alunoDto.getEndereco());
		aluno.setAtivo(alunoDto.getAtivo());
		
		return aluno;
	}
	
	private void carregarCursos(Aluno aluno, AlunoDto alunoDto) throws Exception {
		List<Long> idsCursos = StreamUtils.createStreamFromIterator(alunoDto.getCursos().iterator())
				.filter(Objects::nonNull)
				.map(Curso::getId)
				.collect(Collectors.toList());
		
		List<Curso> cursos = cursoRepository.findAllById(idsCursos);
		if (!cursos.isEmpty()) {
			aluno.setCursos(new HashSet<Curso>(cursos));
		} else {
			throw new Exception("Curso não encontrado na base de dados.");
		}
	}
}

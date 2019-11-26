package br.pucminas.api.controllers;

import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.pucminas.api.dtos.ResultadoDto;
import br.pucminas.api.entities.Aluno;
import br.pucminas.api.entities.HistoricoIntegracao;
import br.pucminas.api.entities.Integracao;
import br.pucminas.api.entities.Resultado;
import br.pucminas.api.repositories.ResultadoRepository;
import br.pucminas.api.services.AlunoService;
import br.pucminas.api.services.ResultadoService;

@RestController
@RequestMapping("/api/integracao")
public class IntegracaoController {

	private static final URI URL = URI.create("https://api.myjson.com/bins/1d572o");

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ResultadoService resultadoService;
	
	@Autowired
	private ResultadoRepository resultadoRepository;
	
	@GetMapping
	public Page<ResultadoDto> listar(Pageable pageable) throws Exception {
		return resultadoRepository.findAll(pageable).map(this::fromResultado);
	}
	
	@PostMapping
	public ResponseEntity<Object> enviarDadosCensoEscolar() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@PutMapping
	public void receberDadosCensoEscolar() {
		HistoricoIntegracao historicoIntegracao = carregarIntegracao();
		final Date lastDataBase = historicoIntegracao.getLastDataBase();

		limparIntegracoesMesmaData(lastDataBase);

		List<Resultado> resultados = historicoIntegracao.getIntegracoes()
				.stream()
				.map(integracao -> construirResultado(integracao, lastDataBase))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

		if (!resultados.isEmpty()) {
			resultadoService.inserirLote(resultados);
		}
	}
	
	private ResultadoDto fromResultado(Resultado resultado) {
		ResultadoDto resultadoDto = new ResultadoDto();
		resultadoDto.setAluno(resultado.getAluno().getNome());
		resultadoDto.setCurso(resultado.getCurso());
		resultadoDto.setInstituicao(resultado.getInstituicao());
		resultadoDto.setPeriodo(resultado.getPeriodo());
		resultadoDto.setResultado(resultado.getResultado());
		resultadoDto.setLastDataBase(resultado.getLastDataBase());
		resultadoDto.setDtaCriacao(resultado.getDtaCriacao());
		
		return resultadoDto;
	}

	private void limparIntegracoesMesmaData(Date lastDataBase) {
		resultadoService.deleteByLastDataBase(lastDataBase);
	}

	private Resultado construirResultado(Integracao integracao, Date lastDataBase) {
		Aluno aluno = alunoService.findByCpf(integracao.getCpf());

		if (aluno == null) {
			return null;
		}

		Resultado resultado = new Resultado();
		resultado.setAluno(aluno);
		resultado.setCurso(integracao.getCurso());
		resultado.setInstituicao(integracao.getInstituicao());
		resultado.setPeriodo(integracao.getPeriodo());
		resultado.setResultado(integracao.getResultado());
		resultado.setLastDataBase(lastDataBase);

		return resultado;
	}

	private HistoricoIntegracao carregarIntegracao() {
		RestTemplate restTemplate = new RestTemplate();
		HistoricoIntegracao historicoIntegracao = restTemplate.getForObject(URL, HistoricoIntegracao.class);
		return historicoIntegracao;
	}

}

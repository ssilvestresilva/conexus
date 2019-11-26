package br.pucminas.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.api.entities.FonteAprovacoes;
import br.pucminas.api.services.ResultadoService;

@RestController
@RequestMapping("/api/resultado")
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;

	@GetMapping("/aprovacoes")
	public FonteAprovacoes fonteAprovacoes() {
		return resultadoService.fonteAprovacoes();
	}

	@GetMapping("/faixa-etaria")
	public List<Object[]> fonteFaixaEtaria() {
		return resultadoService.fonteFaixaEtaria();
	}
	
	@GetMapping("/media-notas")
	public List<Object> fonteMediaNotas() {
		return resultadoService.fonteMediaNotas();
	}
	
	@GetMapping("/predominancia")
	public List<Map<String, Object>> fontePredominancia() {
		return resultadoService.fontePredominancia();
	}

}

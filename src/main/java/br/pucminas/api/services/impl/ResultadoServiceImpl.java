package br.pucminas.api.services.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.api.entities.FonteAprovacoes;
import br.pucminas.api.entities.Resultado;
import br.pucminas.api.repositories.ResultadoRepository;
import br.pucminas.api.services.ResultadoService;

@Service
public class ResultadoServiceImpl implements ResultadoService {

	@Autowired
	private ResultadoRepository resultadoRepository;

	@Override
	public void deleteByLastDataBase(Date lastDataBase) {
		resultadoRepository.removeByLastDataBase(lastDataBase);
	}

	@Override
	public void inserir(Resultado resultado) {
		resultadoRepository.save(resultado);
	}

	@Override
	public void inserirLote(List<Resultado> resultados) {
		resultadoRepository.saveAll(resultados);
	}

	@Override
	public FonteAprovacoes fonteAprovacoes() {
		return resultadoRepository.fonteAprovacoes();
	}

	@Override
	public List<Object[]> fonteFaixaEtaria() {
		return resultadoRepository.fonteFaixaEtaria();
	}

	@Override
	public List<Object> fonteMediaNotas() {
		return resultadoRepository.fonteMediaNotas();
	}

	@Override
	public List<Map<String, Object>> fontePredominancia() {
		return resultadoRepository.fontePredominancia();
	}

}

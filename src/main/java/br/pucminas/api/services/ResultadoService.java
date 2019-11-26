package br.pucminas.api.services;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import br.pucminas.api.entities.FonteAprovacoes;
import br.pucminas.api.entities.Resultado;

public interface ResultadoService {

	void inserir(Resultado resultado);
	void inserirLote(List<Resultado> resultados);
	void deleteByLastDataBase(Date lastDataBase);
	FonteAprovacoes fonteAprovacoes();
	List<Object[]> fonteFaixaEtaria();
	List<Object> fonteMediaNotas();
	List<Map<String, Object>> fontePredominancia();
}

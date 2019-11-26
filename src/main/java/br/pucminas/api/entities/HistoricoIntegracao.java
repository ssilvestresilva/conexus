package br.pucminas.api.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricoIntegracao {

	@JsonProperty("last_data_base")
	private Date lastDataBase;

	@JsonProperty("notas")
	private List<Integracao> integracoes;

	public Date getLastDataBase() {
		return lastDataBase;
	}

	public void setLastDataBase(Date lastDataBase) {
		this.lastDataBase = lastDataBase;
	}

	public List<Integracao> getIntegracoes() {
		return integracoes;
	}

	public void setIntegracoes(List<Integracao> integracoes) {
		this.integracoes = integracoes;
	}

	@Override
	public String toString() {
		return "HistoricoIntegracao [lastDataBase=" + lastDataBase + ", integracoes=" + integracoes + "]";
	}
}

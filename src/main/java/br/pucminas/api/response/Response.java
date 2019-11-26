package br.pucminas.api.response;

public class Response<T> {

	private T data;
	private String erro;

	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErro() {
		return this.erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}

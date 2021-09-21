package br.com.github.authentice.domain.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Erro {

	private Integer status;
	private LocalDateTime dataHora;
	private String mensagem;
	private String mensagemErro;
	private List<Field> listaFields;
	
	public Erro() {
		super();
	}
	
	public Erro(Integer status, LocalDateTime dataHora, String mensagem, List<Field> listaFields) {
		super();
		this.status = status;
		this.dataHora = dataHora;
		this.mensagem = mensagem;
		this.listaFields = listaFields;
	}
	
	public Erro(Integer status, LocalDateTime dataHora, String mensagem,String mensagemErro, List<Field> listaFields) {
		super();
		this.status = status;
		this.dataHora = dataHora;
		this.mensagem = mensagem;
		this.listaFields = listaFields;
		this.mensagemErro = mensagemErro;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Field> getListaFields() {
		return listaFields;
	}

	public void setListaFields(List<Field> listaFields) {
		this.listaFields = listaFields;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

}

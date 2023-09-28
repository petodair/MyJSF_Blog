package br.com.bloco.model;

import java.io.Serializable;
import java.util.Objects;

public class Post implements Serializable, Base{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int idConta;
	private String assunto;
	private String conteudo;
	
	public Post() {
		super();
	}

	public Post(int id, int idConta, String assunto, String conteudo) {
		super();
		this.id = id;
		this.idConta = idConta;
		this.assunto = assunto;
		this.conteudo = conteudo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}

}

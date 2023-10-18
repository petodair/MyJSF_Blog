package br.com.bloco.model;

import java.io.Serializable;
import java.util.Objects;

public class Solicitacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int idConta;
	private int idRemetente;
	
	public Solicitacao() {
		
	}
	
	public Solicitacao(int id, int idConta, int idRemetente) {
		super();
		this.id = id;
		this.idConta = idConta;
		this.idRemetente = idRemetente;
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
	
	public int getIdRemetente() {
		return idRemetente;
	}
	
	public void setIdRemetente(int idRemetente) {
		this.idRemetente = idRemetente;
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
		Solicitacao other = (Solicitacao) obj;
		return id == other.id;
	}	

}

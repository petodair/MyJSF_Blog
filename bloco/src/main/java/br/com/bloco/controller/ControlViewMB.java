package br.com.bloco.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("viewMB")
@ViewScoped
public class ControlViewMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private boolean editarAssunto = false;
	
	private boolean editarConteudo = false;

	public boolean isEditarAssunto() {
		return editarAssunto;
	}

	public void setEditarAssunto(boolean editarAssunto) {
		this.editarAssunto = editarAssunto;
	}

	public boolean isEditarConteudo() {
		return editarConteudo;
	}

	public void setEditarConteudo(boolean editarConteudo) {
		this.editarConteudo = editarConteudo;
	}
	
	

}

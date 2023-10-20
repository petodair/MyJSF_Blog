package br.com.bloco.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bloco.model.Conta;
import br.com.bloco.service.LoginService;

@Named
@SessionScoped
public class LoginMB implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String senha;
	
	private Conta conta;
	@Inject
	private LoginService service;
	
	public String logar() {
		
		conta = new Conta();
		
		conta.setUser(user);
		conta.setSenha(senha);
		
		if(service.checaLogin(conta) == true) {
			return "/home.xhtml";
		}
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro","Usuario ou senha incorretos"));
		return null;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml";
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
}

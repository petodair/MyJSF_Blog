package br.com.bloco.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bloco.model.Conta;
import br.com.bloco.model.Post;
import br.com.bloco.service.ContaService;
import br.com.bloco.service.PostService;

@Named("bean")
@SessionScoped
public class ControlMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Post post;

	@Inject
	private Conta conta;

	@Inject
	private LoginMB login;

	@Inject
	private PostService service;

	@Inject
	private ContaService contaService;

	private String valor;

	@PostConstruct
	public void init() {

		valor = post.getAssunto();

	}

	public void pagPost(int id) {
		post = new Post();
		post = service.selecionaPost(id);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaPost.xhtml");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void pagPostSub(int id) {
		post = new Post();
		post = service.selecionaPost(id);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../paginaPost.xhtml");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void editPost(int id) {
		post = new Post();
		post = service.selecionaPost(id);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../restricted/editarPost.xhtml");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void pagConta(int id) {
		conta = new Conta();
		conta = contaService.selecionaConta(id);
		try {
			if (login.getConta() != null) {
				if (login.getConta().getId() == id) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("restricted/conta.xhtml");
				} else {
					FacesContext.getCurrentInstance().getExternalContext().redirect("perfil.xhtml");
				}
				} else {
					FacesContext.getCurrentInstance().getExternalContext().redirect("perfil.xhtml");
				}			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}

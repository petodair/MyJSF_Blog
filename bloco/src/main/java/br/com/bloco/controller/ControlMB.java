package br.com.bloco.controller;


import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bloco.model.Post;
import br.com.bloco.service.PostService;

@Named("bean")
@SessionScoped
public class ControlMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Post post;
	
	@Inject
	private PostService service;
	
	public void pagPost(int id) {
		post = new Post();
		post = service.selecionaPost(id);
	    try {
	    	FacesContext.getCurrentInstance().getExternalContext().redirect("paginaPost.xhtml");
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
	
	

}

package br.com.bloco.controller;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bloco.model.Post;
import br.com.bloco.service.PostService;

@Named
@ViewScoped
public class PostMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Post post;
	
	@Inject
	private PostService service;
	
	private List<Post> posts;
	
	@PostConstruct
	public void carregar() {
		posts = service.todosOsPosts();
	}
	
	public void adicionar(int id) {
		try {
			post.setId(id);
			service.salvar(post);
			FacesContext context = FacesContext.getCurrentInstance();
		    context.getExternalContext().redirect("../home.xhtml");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void remover(Post p) {
		
		try {
			service.remover(p);
			carregar();
		} catch (Exception e) {
			
		}
		
	}
	
	public void pagPost(int id) {
		post = service.selecionaPost(post);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaPost.xhtml?param=#{p.id}");
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

	public List<Post> getPosts() {
		return posts;
	}

}
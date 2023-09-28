package br.com.bloco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.bloco.dao.DAOPost;
import br.com.bloco.model.Post;

public class PostService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOPost dao;
	
	public void salvar(Post p) {
		dao.inserirPost(p);
	}
	
	public void remover(Post p) {
		dao.deletaPost(p);
	}
	
	public Post selecionaPost(Post p) {
		dao.selecionaPost(p);
		return p;
	}
	
	public List<Post> todosOsPosts(){
		return dao.listarPosts();
	}


}

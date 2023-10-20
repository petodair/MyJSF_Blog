package br.com.bloco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.bloco.dao.DAOComentario;
import br.com.bloco.model.Comentario;

public class ComentarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOComentario dao;
	
	public void salvar(Comentario c) {
		dao.inserirComentario(c);
	}
	
	public void remover(int id) {
		dao.deletaComentario(id);
	}
	
	public List<Comentario> comentariosDoPost(int id) {
		return dao.listarComentarios(id);
	}

}

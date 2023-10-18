package br.com.bloco.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.bloco.dao.DAOCurtida;

public class LikeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCurtida dao;
	
	public void curtir(boolean resposta,boolean like,int idComent,int idAutor,int idPost) {
		dao.curtir(resposta, like, idComent, idAutor, idPost);
	}
	
	public void descurtir(boolean resposta,boolean like,int idComent,int idAutor,int idPost) {
		dao.descurtir(resposta, like, idComent, idAutor, idPost);
	}
	
	public boolean retornaLike(int idComent,int idAutor, boolean resposta) {
		return dao.retornaLike(idComent, idAutor, false);
	}
	
	public boolean likeOuDeslike(int idComent,int idAutor,boolean curtida) {
		return dao.curtido(idComent, idAutor, curtida);
	}
	
	

}

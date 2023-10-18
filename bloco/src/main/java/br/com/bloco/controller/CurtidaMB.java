package br.com.bloco.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bloco.service.LikeService;

@Named
@ViewScoped
public class CurtidaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LikeService service;

	@Inject
	private LoginMB loginMB;

	

	public void curtir(int idComent, int idPost) {

		try {

			if (loginMB.getConta() == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} else {
				boolean curtida = true;
				int idAutor = loginMB.getConta().getId();
				boolean resposta = service.likeOuDeslike(idComent, idAutor, curtida);
				curtida = service.retornaLike(idComent, idAutor, resposta);			
				service.curtir(resposta, curtida, idComent, idAutor, idPost);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void descurtir(int idComent, int idPost) {

		try {

			if (loginMB.getConta() == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} else {
				boolean curtida = true;
				int idAutor = loginMB.getConta().getId();
				boolean resposta = service.likeOuDeslike(idComent, idAutor, curtida);
				curtida = service.retornaLike(idComent, idAutor, resposta);
				service.descurtir(resposta, curtida, idComent, idAutor, idPost);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

package br.com.bloco.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bloco.model.Comentario;
import br.com.bloco.service.ComentarioService;
import javax.annotation.PostConstruct;

@Named
@ViewScoped
public class ComentarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Comentario comentario;

	@Inject
	private ComentarioService service;
	
	@Inject
	private LoginMB loginMB;

	private List<Comentario> comentarios;
	
	public List<Comentario> carregarComentarios(int id) {
		comentarios = service.comentariosDoPost(id);
		return comentarios;
	}

	public void adicionar(int id) {
		try {		
			if(loginMB.getConta() == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
			} else {
			comentario.setAutor(loginMB.getConta().getUser());
			comentario.setIdConta(loginMB.getConta().getId());
			comentario.setId(id);
			service.salvar(comentario);
			comentario = new Comentario();
			carregarComentarios(id);
			}		
		} catch (Exception e) {
			System.out.println("erro ao adicionar: " + e);
		}
	}
	
	public void teste() {
		System.out.println("funfoou");
	}

	public void remover(int id) {
		try {
			service.remover(comentario);
			carregarComentarios(id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public ComentarioService getService() {
		return service;
	}

	public void setService(ComentarioService service) {
		this.service = service;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

}

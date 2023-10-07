package br.com.bloco.model;

import java.io.Serializable;

public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String autor;
	private String comentario;
	private int likes;
	private int deslikes;
	private int idConta;
	private int idPost;
	
	public Comentario() {
		super();
	}

	public Comentario(int id, String autor, String comentario, int likes, int deslikes, int idConta, int idPost) {
		super();
		this.id = id;
		this.autor = autor;
		this.comentario = comentario;
		this.likes = likes;
		this.deslikes = deslikes;
		this.idConta = idConta;
		this.idPost = idPost;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDeslikes() {
		return deslikes;
	}

	public void setDeslikes(int deslikes) {
		this.deslikes = deslikes;
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

}

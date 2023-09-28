package br.com.bloco.controller;


import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("bean")
@ViewScoped
public class ControlMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void pagPost() {
		FacesContext context = FacesContext.getCurrentInstance();
	    try {
			context.getExternalContext().redirect("postar_bloco.xhtml");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

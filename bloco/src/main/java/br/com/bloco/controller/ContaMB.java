package br.com.bloco.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bloco.model.Conta;
import br.com.bloco.service.ContaService;

@Named
@ViewScoped
public class ContaMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Conta conta;
	
	@Inject
	private ContaService service;
	
	private List<Conta> contas;
	
	@PostConstruct
	public void carregar() {
		contas = service.todasAsContas();
	}
	
	public void adicionar() {
		try {
			service.salvar(conta);
			conta = new Conta();
			carregar();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void remover() {		
		try {
			service.remover(conta);
			carregar();
		} catch (Exception e) {
			
		}
		
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getContas() {
		return contas;
	}
	

}

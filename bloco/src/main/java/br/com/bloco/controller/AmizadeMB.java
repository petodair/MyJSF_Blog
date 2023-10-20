package br.com.bloco.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bloco.model.Conta;
import br.com.bloco.model.Solicitacao;
import br.com.bloco.service.AmizadeService;
import br.com.bloco.service.ContaService;

@Named
@ViewScoped
public class AmizadeMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Conta remetente;
	
	@Inject
	private AmizadeService service;
	
	@Inject
	private ContaService contaService;
	
	public void enviarSolicitacao(int idconta, int idrementente) {
		service.salvar(idconta, idrementente);
	}
	
	public void recusarSolicitacao(int id) {
		service.remover(id);
	}
	
	public void recusarSolicitacao(int idconta,int idremetente) {
		service.remover(idconta, idremetente);
	}
	
	public List<Solicitacao> listarSolicitacoes(int idconta) {
		return service.listarSolicitacoes(idconta);
	}
	
	public String nomeRemetente(int id) {
		remetente = contaService.selecionaConta(id);
		return remetente.getUser();
	}
	
	public boolean checaSolicitacao(int idconta, int idremetente) {
		return service.checaSolicitacao(idconta, idremetente);
	}

}

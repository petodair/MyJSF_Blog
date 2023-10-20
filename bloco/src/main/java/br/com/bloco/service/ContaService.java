package br.com.bloco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.bloco.dao.DAOConta;
import br.com.bloco.model.Conta;

public class ContaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOConta dao;
	
	public void salvar(Conta c) {
		dao.inserirConta(c);
	}
	
	public void remover(Conta c) {
		dao.deletaConta(c);
	}
	
	public Conta selecionaConta(int id) {
		return dao.selecionaConta(id);
	}
	
	public int checaCredenciais(String user, String email) {
		return dao.checaConta(user, email);
	}
	
	public List<Conta> todasAsContas(){
		return dao.listarContas();
	}
	
	public List<Conta> pesquisarConta(String nome){
		return dao.pesquisarContas(nome);
	}

}

package br.com.bloco.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.bloco.dao.DAOConta;
import br.com.bloco.model.Conta;

public class LoginService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOConta dao;
	
	
	public boolean checaLogin(Conta conta) {
		return dao.checaConta(conta);
	}

}

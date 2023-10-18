package br.com.bloco.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.bloco.model.Solicitacao;

public class DAOAmizade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1/dbbloco?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "892240";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
	}
	
	public void adicionarSolicitacao(int idconta, int idremetente) {
		String read = "INSERT INTO tbsolicitacao(idconta,idremetente) VALUES(?,?)";
		try {
			
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setInt(1, idconta);
			pst.setInt(2, idremetente);
			
			pst.executeQuery();
		} catch (Exception e) {
			System.out.println("Erro ao adicionar solicitacao " + e);
		}
	}
	
	public ArrayList<Solicitacao> listarSolicitacoes(int id) {
		ArrayList<Solicitacao> solicitacoes = new ArrayList<>();
		String read = "SELECT * FROM tbsolicitacao WHERE idconta = ?";
		try {
			
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int idsolicitacao = rs.getInt(1);
				int idconta = rs.getInt(2);
				int idremetente = rs.getInt(3);
				
				solicitacoes.add(new Solicitacao(idsolicitacao, idconta, idremetente));
			}
			
			con.close();
			
			return solicitacoes;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void excluirSolicitacao(int id) {
		String read = "DELETE FROM tbsolicitacoes WHERE id = ?";
		try {
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setInt(1, id);
			
			pst.executeQuery();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

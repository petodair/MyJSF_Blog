package br.com.bloco.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.exceptions.RSAException;

import br.com.bloco.model.Conta;

public class DAOConta implements Serializable {

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

	public void inserirConta(Conta conta) {

		String novaConta = "INSERT INTO tbcontas(usuario, email, senha, tipo) values (?,?,?,?)";
		try {

			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(novaConta);

			pst.setString(1, conta.getUser());
			pst.setString(2, conta.getEmail());
			pst.setString(3, conta.getSenha());
			pst.setString(4, conta.getTipo());

			pst.executeUpdate();

			con.close();

		} catch (Exception e) {
			System.out.println("Erro ao inserir: " + e);
		}
	}

	public ArrayList<Conta> listarContas() {
		ArrayList<Conta> contas = new ArrayList<>();
		String read = "SELECT idconta, usuario, email, senha, tipo FROM tbcontas";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String usuario = rs.getString(2);
				String email = rs.getString(3);
				String senha = rs.getString(4);
				String tipo = rs.getString(5);

				contas.add(new Conta(id, usuario, email, senha, tipo));
			}
			con.close();
			return contas;
		} catch (Exception e) {
			System.out.println("Erro ao listar: " + e);
			return null;
		}
	}

	public void deletaConta(Conta conta) {

		String deletaConta = "DELETE FROM tbcontas WHERE idconta = ?";

		try {

			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(deletaConta);

			pst.setInt(1, conta.getId());

			pst.executeUpdate();

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean checaConta(Conta conta) {
		
		String read = "SELECT idconta, usuario, email, senha, tipo FROM tbcontas WHERE usuario = ? and senha = ?";
		
		try {
			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setString(1, conta.getUser());
			pst.setString(2, conta.getSenha());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				conta.setId(rs.getInt(1));
				conta.setUser(rs.getString(2));
				conta.setEmail(rs.getString(3));
				conta.setTipo(rs.getString(5));
				
				return true;
			}
			con.close();
		} catch (Exception e) {
			
			System.out.println(e);
			return false;		

		}
		
		return false;
		
	}

}

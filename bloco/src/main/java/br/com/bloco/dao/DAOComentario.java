package br.com.bloco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.bloco.model.Comentario;

public class DAOComentario {
	
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
	
	public void inserirComentario(Comentario comentario) {
		
		String novoComentario = "INSERT INTO tbcomentarios(autor, comentario, likes, deslikes, idpost, idconta)"
				+ "VALUES(?,?,0,0,?,?)";
		
		try {
			
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(novoComentario);
			
			pst.setString(1, comentario.getAutor());
			pst.setString(2, comentario.getComentario());
			pst.setInt(3, comentario.getIdPost());
			pst.setInt(4, comentario.getIdConta());
			
			pst.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Comentario> listarComentarios(int idp) {
		ArrayList<Comentario> comentarios = new ArrayList<>();
		String read = "SELECT id, autor, comentario, likes, deslikes, idPost, idConta FROM tbcomentarios WHERE idPost = ?";		
		try {			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setInt(1, idp);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {

				int id = rs.getInt(1);
				String autor = rs.getString(2);
				String comentario = rs.getString(3);
				int likes = rs.getInt(4);
				int deslikes = rs.getInt(5);
				int idPost = rs.getInt(6);
				int idConta = rs.getInt(7);

				comentarios.add(new Comentario(id, autor, comentario, likes, deslikes, idPost, idConta));
			}
			con.close();
			return comentarios;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void deletaComentario(Comentario comentario) {
		String delete = "DELETE FROM tbcomentarios WHERE id = ?";
		String delete2 = "DELETE FROM registrolikes WHERE idcomentario = ?";
		try {
			
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(delete);
			PreparedStatement pst2 = con.prepareStatement(delete2);

			pst.setInt(1, comentario.getId());
			
		    pst2.setInt(1, comentario.getId());

			pst.executeUpdate();
			
			pst2.executeUpdate();

			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

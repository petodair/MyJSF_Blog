package br.com.bloco.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.bloco.model.Post;

public class DAOPost implements Serializable{
	
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
	
	public void inserirPost(Post post) {

		String novoPost = "INSERT INTO tbposts(assunto, conteudo, idconta) values (?,?,?)";
		try {

			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(novoPost);
						
			pst.setString(1, post.getAssunto());
			pst.setString(2, post.getConteudo());
			pst.setInt(3, post.getId());
			

			pst.executeUpdate();

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<Post> listarPosts() {
		ArrayList<Post> posts = new ArrayList<>();
		String read = "select * from tbposts";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String assunto = rs.getString(2);
				String conteudo = rs.getString(3);
				int idconta = rs.getInt(4);

				posts.add(new Post(id, idconta, assunto, conteudo));
			}
			con.close();
			return posts;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void selecionaPost(Post post) {
		String read2 = "select * from tbposts where id  = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, post.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				post.setId(rs.getInt(1));
				post.setAssunto(rs.getString(2));
				post.setConteudo(rs.getString(3));
				post.setIdConta(rs.getInt(4));
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deletaPost(Post post) {
		
		String deletePost = "DELETE FROM tbposts WHERE id = ?";
	
		try {

			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(deletePost);
			
			pst.setInt(1, post.getId());			

			pst.executeUpdate();

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	
		
	}

}
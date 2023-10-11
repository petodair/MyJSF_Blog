package br.com.bloco.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOCurtida implements Serializable {

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

	public boolean curtido(int idComentario, int idConta, boolean like) {
		String read = "select * from registrolikes WHERE idcomentario = ? AND idconta = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);

			pst.setInt(1, idComentario);
			pst.setInt(2, idConta);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				like = rs.getBoolean(3);

				return true;
			} else {

				return false;
			}

		} catch (Exception e) {
			System.out.println(e + ":falha ao checar curtido");
			return true;
		}

	}

	public boolean retornaLike(int idComentario, int idConta, boolean like) {
		String read = "select * from registrolikes WHERE idcomentario = ? AND idconta = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);

			pst.setInt(1, idComentario);
			pst.setInt(2, idConta);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				like = rs.getBoolean(3);
			}
			con.close();

			return like;

		} catch (Exception e) {
			System.out.println(e + ":falha ao checar curtido");
			return true;
		}

	}

	public void curtir(boolean resposta, boolean like, int idComentario, int idConta, int idPost) {

		String read = null;
		String read2 = null;

		try {
			Connection con = conectar();
			PreparedStatement pst = null;
			PreparedStatement pst2 = null;

			if (resposta == true && like == true) {
				read = "UPDATE tbcomentarios SET likes = likes - 1 WHERE idcomentario = ? ";
				read2 = "DELETE FROM registrolikes WHERE idcomentario = ? AND idconta = ?";
				pst = con.prepareStatement(read);
				pst2 = con.prepareStatement(read2);

				pst.setInt(1, idComentario);
				pst2.setInt(1, idComentario);
				pst2.setInt(2, idConta);

			} else if (resposta == true && like == false) {
				read = "UPDATE tbcomentarios SET deslikes = deslikes - 1, likes = likes + 1 WHERE idcomentario = ?";
				read2 = "UPDATE registrolikes SET curtido = true WHERE idcomentario = ? and idconta = ?";
				pst = con.prepareStatement(read);
				pst2 = con.prepareStatement(read2);

				pst.setInt(1, idComentario);
				pst2.setInt(1, idComentario);
				pst2.setInt(2, idConta);

			} else if (resposta == false) {
				read = "UPDATE tbcomentarios SET likes = likes + 1 WHERE idcomentario = ?";
				read2 = "INSERT INTO registrolikes(idcomentario, idconta, curtido, idpost) VALUES (?,?,?,?)";
				pst = con.prepareStatement(read);
				pst2 = con.prepareStatement(read2);

				pst.setInt(1, idComentario);
				pst2.setInt(1, idComentario);
				pst2.setInt(2, idConta);
				pst2.setBoolean(3, true);
				pst2.setInt(4, idPost);
			}

			pst.executeUpdate();
			pst2.executeUpdate();

			con.close();

		} catch (Exception e) {
			System.out.println(e + ":falha ao curtir");

		}

	}

	public void descurtir(boolean resposta, boolean like, int idComentario, int idConta, int idPost) {

		String read = null;
		String read2 = null;

		try {
			Connection con = conectar();
			PreparedStatement pst = null;
			PreparedStatement pst2 = null;

			if (resposta == true && like == false) {
				read = "UPDATE tbcomentarios SET deslikes = deslikes - 1 WHERE idcomentario = ?";
				read2 = "DELETE FROM registrolikes WHERE idcomentario = ? AND idconta = ?";
				pst = con.prepareStatement(read);
				pst2 = con.prepareStatement(read2);

				pst.setInt(1, idComentario);
				pst2.setInt(1, idComentario);
				pst2.setInt(2, idConta);

			} else if (resposta == true && like == true) {
				read = "UPDATE tbcomentarios SET likes = likes - 1, deslikes = deslikes + 1 WHERE idcomentario = ?";
				read2 = "UPDATE registrolikes SET curtido = false WHERE idcomentario = ? and idconta = ?";
				pst = con.prepareStatement(read);
				pst2 = con.prepareStatement(read2);

				pst.setInt(1, idComentario);
				pst2.setInt(1, idComentario);
				pst2.setInt(2, idConta);

			} else if (resposta == false) {
				read = "UPDATE tbcomentarios  SET deslikes = deslikes + 1 WHERE idcomentario = ?";
				read2 = "INSERT  INTO registrolikes(idcomentario, idconta, curtido, idpost) VALUES (?,?,?,?)";
				pst = con.prepareStatement(read);
				pst2 = con.prepareStatement(read2);

				pst.setInt(1, idComentario);
				pst2.setInt(1, idComentario);
				pst2.setInt(2, idConta);
				pst2.setBoolean(3, false);
				pst2.setInt(4, idPost);
			}

			pst.executeUpdate();
			pst2.executeUpdate();

			con.close();

		} catch (Exception e) {
			System.out.println(e + ":falha ao descurtir");

		}

	}

}

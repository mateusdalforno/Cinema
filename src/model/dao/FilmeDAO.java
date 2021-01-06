package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.Filme;

public class FilmeDAO {

	public void create(Filme f) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con
					.prepareStatement("INSERT INTO FILME (titulo, categoria, sinopse, tempo, imagem3d, dublado) VALUES"
							+ "(?,?,?,?,?,?)");
			stmt.setString(1, f.getTitulo());
			stmt.setString(2, f.getCategoria());
			stmt.setString(3, f.getSinopse());
			stmt.setInt(4, f.getTempo());
			stmt.setBoolean(5, f.isImagem3d());
			stmt.setBoolean(6, f.isDublado());

			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Filme Salvo com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public List<Filme> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Filme> filmes = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT * FROM filme;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Filme f = new Filme();
				f.setIdFilme(rs.getInt("idFilme"));
				f.setTitulo(rs.getString("titulo"));
				f.setTempo(rs.getInt("tempo"));
				f.setSinopse(rs.getString("sinopse"));
				f.setCategoria(rs.getString("categoria"));
				f.setImagem3d(rs.getBoolean("imagem3d"));
				f.setDublado(rs.getBoolean("dublado"));
				filmes.add(f);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar as informações do BD: " + e);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return filmes;
	}

	public Filme read(int idFilme) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Filme f = new Filme();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM filme WHERE idFilme=? LIMIT 1;");
			stmt.setInt(1, idFilme);
			rs = stmt.executeQuery();
			if(rs != null && rs.next()) {
				f.setIdFilme(rs.getInt("idFilme"));
				f.setTitulo(rs.getString("titulo"));
				f.setTempo(rs.getInt("tempo"));
				f.setSinopse(rs.getString("sinopse"));
				f.setCategoria(rs.getString("categoria"));
				f.setImagem3d(rs.getBoolean("imagem3d"));
				f.setDublado(rs.getBoolean("dublado"));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return f;
	}
	
	
	public void update(Filme f) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE filme SET titulo=?, categoria=?, sinopse=?,"
					+ "tempo=?, imagem3d=?, dublado=? WHERE idFilme=?;");
			stmt.setString(1, f.getTitulo());
			stmt.setString(2, f.getCategoria());
			stmt.setString(3, f.getSinopse());
			stmt.setInt(4, f.getTempo());
			stmt.setBoolean(5, f.isImagem3d());
			stmt.setBoolean(6, f.isDublado());
			stmt.setInt(7, f.getIdFilme());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(Filme f) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM filme WHERE idFilme=?");
			stmt.setInt(1, f.getIdFilme());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Filme excluído com sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}
	
}

package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	public List<Corso> getCorsiByPeriodo(Integer periodo){
		String sql = "SELECT * "
				+ "FROM  corso "
				+ "WHERE pd=?";
		List<Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			rs.close();
			st.close();
			conn.close();
			return result;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer periodo){
		String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins = i.codins AND c.pd = ? "
				+ "GROUP BY c.codins, c.nome, c.crediti, c.pd";
		Map<Corso, Integer> result = new HashMap<Corso, Integer>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				Integer n = rs.getInt("tot");
				result.put(c, n);
			}
			rs.close();
			st.close();
			conn.close();
			return result;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}


	public boolean esisteCorso(String codice) {
		String sql = "SELECT * FROM corso WHERE codins = ?";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codice);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				return true;
			} else {
				rs.close();
				st.close();
				conn.close();
				return false;
			}
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}


	public Map<String, Integer> getDivisioneCDS(String codice) {
		String sql = "SELECT s.CDS, COUNT(*) AS tot "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? AND s.CDS<>'' "
				+ "GROUP BY s.CDS";
		Map<String, Integer> mappa = new HashMap<String, Integer>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codice);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				mappa.put(rs.getString("CDS"), rs.getInt("tot"));
			}
			rs.close();
			st.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException();
		}
		return mappa;
	}

}

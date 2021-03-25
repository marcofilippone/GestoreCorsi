package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getStudentiByCodIns(String codins){
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+"FROM studente s, iscrizione i "
				+"WHERE s.matricola=i.matricola AND i.codins=?";
		List<Studente> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				Studente s = new Studente( res.getInt("matricola"), res.getString("cognome"), res.getString("nome"), res.getString("CDS")  );
				result.add(s);
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
		
	}

}

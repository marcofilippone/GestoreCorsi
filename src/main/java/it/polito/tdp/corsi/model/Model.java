package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(Integer pd){
		return corsoDao.getCorsiByPeriodo(pd);
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer pd){
		return corsoDao.getIscrittiByPeriodo(pd);
	}
	
	public List<Studente> getStudentiByCodIns(String codins){
		return studenteDao.getStudentiByCodIns(codins);
	}
	
	public Map<String, Integer> getDivisioneCDS (String codice){
		/*Map<String, Integer> divisione = new HashMap<String, Integer>();
		List<Studente> studenti = this.getStudentiByCodIns(codice);
		for (Studente s : studenti) {
			if(s.getCDS()!=null){
				if(divisione.get(s.getCDS()) == null) {
					divisione.put(s.getCDS(), 1);
				}
				else {
					divisione.put(s.getCDS(), divisione.get(s.getCDS()) +1);
				}
			}
		}*/
		return corsoDao.getDivisioneCDS(codice);
	}

	public boolean esisteCorso(String codice) {
		return corsoDao.esisteCorso(codice);
	}
	
}

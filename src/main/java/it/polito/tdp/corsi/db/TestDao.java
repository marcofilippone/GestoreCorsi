package it.polito.tdp.corsi.db;

import java.util.HashMap;
import java.util.Map;

public class TestDao {

	public static void main(String[] args) {
		CorsoDAO corsoDao = new CorsoDAO();
		Map<String, Integer> m = new HashMap<String, Integer>();
		m = corsoDao.getDivisioneCDS("10BDAPG");
		for(String str : m.keySet()) {
			System.out.println(str + "" + m.get(str));
		}

	}

}

package lonen.test.handel.persistence;

import java.util.List;

public class KlantenService { //duidelijk welke functies er zijn in de daolmpl
	private KlantenDao klantendao = new KlantenPostgresDaolmpl();
	
	public List<Klant> findall(){
		return klantendao.alleKlanten();
	}
	
	public boolean save(Klant klant) {
		return klantendao.save(klant);
	}
	
	public boolean delete(int Klantcode) {
		return klantendao.delete(Klantcode);
	}

	public boolean update(Klant klant) {
		return klantendao.update(klant);
	}
}

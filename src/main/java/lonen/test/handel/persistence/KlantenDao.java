package lonen.test.handel.persistence;

import java.util.List;

public interface KlantenDao {//overzichtelijk welke functies er in de daolmpl zitten
	public List<Klant> alleKlanten();
	
	public boolean save(Klant klant);
	
	public boolean delete(int klantid);

	public boolean update(Klant klant);
}

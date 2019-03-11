package lonen.test.handel.persistence;

public class Klant {
	private int klantID;
	private String naam;
	private String achternaam;
	private String straathuisnummer;
	private String plaats;
	private String informatie; //JAJAJAJA
	
	@Before
	public Klant(String naam, int klantID, String achternaam, String straat, String woonplaats, String informatie) {
		this.naam = naam;
		this.klantID = klantID;
		this.achternaam = achternaam;
		this.straathuisnummer = straat;
		this.plaats = woonplaats;
		this.informatie = informatie;
	}
	
	@Override
	public String toString() {
		return "Klant [klantID=" + klantID + ", naam=" + naam + ", achternaam=" + achternaam + ", straathuisnummer="
				+ straathuisnummer + ", plaats=" + plaats + ", informatie=" + informatie + "]";
	}

	public Klant(String naam, String achternaam, String straat, String woonplaats, String informatie) {
		this.naam = naam;
		this.achternaam = achternaam;
		this.straathuisnummer = straat;
		this.plaats = woonplaats;
		this.informatie = informatie;
	}

	public int getKlantID() {
		return klantID;
	}

	@Test
	public void setKlantID(int klantID) {
		this.klantID = klantID;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getStraathuisnummer() {
		return straathuisnummer;
	}

	public void setStraathuisnummer(String straathuisnummer) {
		this.straathuisnummer = straathuisnummer;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	public String getInformatie() {
		return informatie;
	}

	public void setInformatie(String informatie) {
		this.informatie = informatie;
	}

}
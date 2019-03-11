package lonen.test.handel.persistence;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class KlantenPostgresDaolmpl extends PostgresBaseDao implements KlantenDao{
	
	
	
	@Override
	public List<Klant> alleKlanten() {
		List<Klant> results = new ArrayList<Klant>(); //eindresultaat, lijst met alle klanten
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("Select naam, klantID, achternaam, straat, woonplaats, informatie FROM KLANTEN ORDER BY klantID DESC"); //sql statement waarbij je alle data ophaalt geordent op kleinste productid eerst
			ResultSet dbResultSet = pstmt.executeQuery();
			while (dbResultSet.next()) { //zolang er een nieuwe klant opgehaald is
				String naam = dbResultSet.getString("naam");
				int klantID = dbResultSet.getInt("klantID");
				String achternaam = dbResultSet.getString("achternaam");
				String straat = dbResultSet.getString("straat");
				String woonplaats = dbResultSet.getString("woonplaats");
				String informatie = dbResultSet.getString("informatie");
				

				Klant newKlant = new Klant(naam, klantID, achternaam, straat, woonplaats, informatie);
				results.add(newKlant); //nieuwe klant in de lijst toevoegen
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	@Override
	public boolean save(Klant klant) {
		boolean result = false; //automatisch het resultaat als fout
		try (Connection con = super.getConnection()) {
			System.out.println("READYING QUERY");
			String query = "INSERT INTO klanten(naam, achternaam, straat, woonplaats, informatie)values(?,?,?,?,?)"; //sql statement om nieuwe klanten toe te voegen
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, klant.getNaam());
			pstmt.setString(2, klant.getAchternaam());
			pstmt.setString(3, klant.getStraathuisnummer());
			pstmt.setString(4, klant.getPlaats());
			pstmt.setString(5, klant.getInformatie());
			int res = pstmt.executeUpdate();
			if (res == 1) {  // als er een regel toegevoegd is, wordt het resultaat goed
				result = true;

			}} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean delete(int klantid) {
		boolean result = false; //automatisch doogeven dat het foutgaat
		try (Connection con = super.getConnection()) {
			System.out.println("klantenid ontvangen bij deletefunctie: " + klantid);
			String query = "DELETE from klanten WHERE klantid = ?"; //sql statement om te verwijderen uit de database
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, klantid);
			int res = pstmt.executeUpdate();
			System.out.println(res);
			if (res == 1) { //als er een regel verwijdert wordt krijg je geen foutmelding
				result = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}


	@Override
	public boolean update(Klant klant) {
		boolean result = false; //automatisch teruggeven dat het fout is
		try (Connection con = super.getConnection()) {
			System.out.println("ready to update");
			String query = "UPDATE klanten SET naam = ?, achternaam = ?, straat = ?, woonplaats = ?, informatie = ? WHERE klantid = ?"; //sql statement om alle data bij een bepaalt productid aan te passen
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, klant.getNaam());
			pstmt.setString(2, klant.getAchternaam());
			pstmt.setString(3, klant.getStraathuisnummer());
			pstmt.setString(4, klant.getPlaats());
			pstmt.setString(5, klant.getInformatie());
			pstmt.setInt(6, klant.getKlantID());
			System.out.println("ready to execute with code: " + klant.getKlantID());
			int res = pstmt.executeUpdate();
			System.out.println("executed");
			if (res == 1) { //wanneer er een regel aangepast is, wordt het resultaat goed
				result = true;

			}} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}


}

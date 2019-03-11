package lonen.test.handel.persistence;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class UserPostgresDaolmpl extends PostgresBaseDao implements UserDao{
	
	public String findRoleForUser(String username, String password) { //deze functie dient om te kijken welke rol een gebruiker heeft. 
		String role = null;
		String jsonRole = null;
		try(Connection con = super.getConnection()){
			String query = "SELECT role FROM useraccount WHERE username = ? AND password = ?"; // de rol van een user
			PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				ResultSet dbResultSet = pstmt.executeQuery();
				JsonArrayBuilder jab = Json.createArrayBuilder();
				while (dbResultSet.next()) {
					JsonObjectBuilder roleObject = Json.createObjectBuilder();
					role = dbResultSet.getString("role");
					roleObject.add("role", role);
					jab.add(roleObject);	
				}
				JsonArray array = jab.build(); //de hele array teruggeven
				jsonRole =  array.toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("role received from Database: " + role);
		return role;
	}
	
}
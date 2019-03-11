package lonen.test.handel.webservices;

import java.security.Key;
import java.util.Calendar;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lonen.test.handel.persistence.*;

@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();
	
	// RolesAllowed staat voor alle toegestane rollen. Deze rollen zijn de enige die de functies mogen uitvoeren
	// @Pathparam staat voor de parameters die bij het request mee gegeven wordt
	// De service wordt gebruikt om de database connectie te doen
	// FormParam staat voor alle data dat uit een formulier wordt gehaald en meegegeven wordt aan het request
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username,
									@FormParam("password") String password) {
		try {
			UserDao dao = new UserPostgresDaolmpl();
			String role = dao.findRoleForUser(username, password);
			System.out.println("role received from UserPostGres: " + role);
			
			if(role == null) {throw new IllegalArgumentException("no user found!");			}
			
			String token = createToken(username, role);
			SimpleEntry<String, String> JWT = new SimpleEntry<String, String>("JWT", token);
			System.out.println("Building JWT");
			return Response.ok(JWT).build();
			
		}catch(JwtException | IllegalArgumentException e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();}
		}
	
	private String createToken(String username, String role) throws JwtException{
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.MINUTE, 30);
		
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(expiration.getTime())
				.claim("role", role)
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}
}
	
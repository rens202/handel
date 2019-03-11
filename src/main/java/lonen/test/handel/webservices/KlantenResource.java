package lonen.test.handel.webservices;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import lonen.test.handel.persistence.Klant;
import lonen.test.handel.persistence.KlantenService;
import lonen.test.handel.persistence.ServiceProvider;

@Path("/Klanten")
public class KlantenResource {
	private KlantenService service = ServiceProvider.getKlantenService();
	
	// RolesAllowed staat voor alle toegestane rollen. Deze rollen zijn de enige die de functies mogen uitvoeren
	// @Pathparam staat voor de parameters die bij het request mee gegeven wordt
	// De service wordt gebruikt om de database connectie te doen
	
	
	@DELETE
	@Produces("application/json")
	@RolesAllowed({"admin", "medewerker"}) 
	@Path("/{klantid}")
	public Response deleteKlant(@Context SecurityContext sc, //klant verwijderen
			@PathParam("klantid") int klantid) {	
		System.out.println("Reached backend met klantenid: " + klantid);
		if(!service.delete(klantid)) { 
			return Response.status(404).build();
		}
		System.out.println("deleted");
		return Response.ok().build();

		
	}
	
	
	@GET
	@Produces("application/json")
	@RolesAllowed({"user", "admin", "medewerker"})
	public String getKlanten() { //alle klanten
		KlantenService service = ServiceProvider.getKlantenService();
		List<Klant> klanten = service.findall(); 
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Klant o : klanten) {
			JsonObjectBuilder klant = Json.createObjectBuilder();
			klant.add("naam", o.getNaam());
			klant.add("achternaam", o.getAchternaam());
			klant.add("klantID", o.getKlantID());
			klant.add("straat", o.getStraathuisnummer());
			klant.add("woonplaats", o.getPlaats());
			klant.add("informatie", o.getInformatie());

			jab.add(klant);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	
	@POST
	@Produces("application/json")
	@RolesAllowed({"admin", "medewerker"})
	public Response createKlant(@Context SecurityContext sc, //klant creeren
			@FormParam("naam") String naam,
			@FormParam("achternaam") String achternaam,
			@FormParam("woonplaats") String plaats,
			@FormParam("straat") String straat,
			@FormParam("informatie") String informatie) {
		if(naam == null && achternaam == null && plaats == null && straat == null && informatie == null) {
			return Response.status(404).build();
		}else {
		  Klant klant = new Klant(naam, achternaam, plaats, straat, informatie);
		  System.out.println(klant);
		
		  
		  if (!service.save(klant)) {
		    	return Response.status(404).build();
		    } 
			System.out.println("updated");
			return Response.ok().build();
	}}
	
	 @PUT
	  @Produces("application/json")
	  @RolesAllowed({"admin", "medewerker"})
	  @Path("/{naamID}")
	  public Response updateKlant(@Context SecurityContext sc, //klant updaten
			  		@PathParam("naamID") int naamID,
			  		@FormParam("naam") String naam,
					@FormParam("achternaam") String achternaam,
					@FormParam("straat") String straat,
					@FormParam("woonplaats") String woonplaats,
					@FormParam("informatie") String informatie) {

		if(informatie == null) {
			informatie = "nvt";
		}
	    Klant klant = new Klant(naam, naamID, achternaam, straat, woonplaats, informatie);
	    System.out.println(klant);
	    

	    if (!service.update(klant)) {
	    	return Response.status(404).build();
	    }
	    
		System.out.println("updated");
		return Response.ok().build();

	  }


}

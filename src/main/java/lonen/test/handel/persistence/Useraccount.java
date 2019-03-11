package lonen.test.handel.persistence;

import java.util.ArrayList;

public class Useraccount {
	private int userID;
	private String username;
	private String password;
	private String role;
	
	
	public Useraccount(String role, String username, int userID, String password) {
		this.role = role;
		this.username = username;
		this.userID = userID;
		this.password = password;
	}
	
	public Useraccount(String rl, int uid) {
		this.role = rl;
		this.userID = uid;
	}
	
	public Useraccount(String nm, String ps, String rl) {
		this.username = nm;
		this.password = ps;
		this.role = rl;
	}
	
	public Useraccount(int userID) {
		this.userID = userID;
	}


	public int getUserID() {
		return userID;
	}
	


	@Override
	public String toString() {
		return "Useraccount : \n userID=" + userID + ", username=" + username + "role=" + role
				+ "\n, medewerkers=";
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

}

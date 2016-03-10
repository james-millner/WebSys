package com.jm.WebSys.domain;

import com.sun.istack.internal.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * Created by James on 03/03/2016.
 */
public class User {
	
	String id;
	String fname;
	String sname;
	String dob;

	@NotNull
	@Size(min=5, max=20)
	 String username;

	@NotNull
	@Size(min=8, max=16)
	 String password;
	
	public User() {
		//No Arg Constructor
	}
	
	public User(String f, String n,  String d, String u, String p) {
		this.fname = f;
		this.sname = n;
		this.dob = d;
		this.username = u;
		this.password = p;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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
}

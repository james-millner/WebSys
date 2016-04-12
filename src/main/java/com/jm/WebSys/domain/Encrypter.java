package com.jm.WebSys.domain;


/**
 * Created by James on 04/03/2016.
 *
 * Model for Encrypter Object.
 *
 * This class encrypts and decrypts a String.
 */

public class Encrypter {

	String password = null;

    public Encrypter() {

    }

	public Encrypter(String pass) {
		this.password = pass;

	}

    public void setString(String s) {
        this.password = s;
    }

    public String returnString(){
        return this.password;
    }

	public String encrypt() {
        //Encrypt the password by 10 unicode places.
        String passResult = "";
        String result = "";
        int a = this.password.length();
		char ps;
		for(int i = 0; i < a; i++) {
            ps = this.password.charAt(i);
            ps += 15;
            passResult += ps;
		}
        this.password = passResult;

        result = passResult;

        return result;
	}

	public  String decrypt() {
        //Encrypt the password by 10 unicode places.
        String passResult = "";
        String result = "";
        int a = this.password.length();
        char ps;
        for(int i = 0; i < a; i++) {
            ps = this.password.charAt(i);
            ps -= 15;
            passResult += ps;
        }
        this.password = passResult;


        result = passResult;


        return result;
    }



}

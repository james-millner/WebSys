package com.jm.WebSys.domain;

/**
 * Created by James on 21/03/2016.
 */
public class Recipe {

    String rname;
    String rdesc;
    String ringredients;
    int rhours;
    int rmins;
    String additional;
    String creator;

    //No Arg Constructor
    public Recipe() {

    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public String getRingredients() {
        return ringredients;
    }

    public void setRingredients(String ringredients) {
        this.ringredients = ringredients;
    }

    public int getRhours() {
        return rhours;
    }

    public void setRhours(int rhours) {
        this.rhours = rhours;
    }

    public int getRmins() {
        return rmins;
    }

    public void setRmins(int rmins) {
        this.rmins = rmins;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

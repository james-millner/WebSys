package com.jm.WebSys.domain;

/**
 * Created by James on 21/03/2016.
 */
public class Recipe {

    String id;
    String rname;
    String rdesc;
    String ringredients;
    int rhours;
    int rmins;
    String rmethod;
    String creator;
    int views;


    //No Arg Constructor
    public Recipe() {

    }

    //Get and Set Methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRmethod() {
        return rmethod;
    }

    public void setRmethod(String rmethod) {
        this.rmethod = rmethod;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}

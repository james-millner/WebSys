package com.jm.WebSys.domain;

/**
 * Created by James on 05/04/2016.
 *
 * Model for Like Object.
 *
 */
public class Like {

    String id;
    String rid;
    String uid;

    //No Arg Constructor
    public Like() {

    }

    public Like(String i, String r, String u) {
        this.id = i;
        this.rid = r;
        this.uid = u;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

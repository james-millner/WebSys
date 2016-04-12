package com.jm.WebSys.domain;

import java.util.Date;

/**
 * Created by James on 29/03/2016.
 *
 * Model for Comments.
 *
 */
public class Comment {

    String id;
    String rid;
    Date timestamp;
    String author;
    String comment;

    //No Arg Constructor
    public Comment() {

    }

    public Comment(String i, String r, Date t, String a, String c){
        this.id = i;
        this.rid = r;
        this.timestamp = t;
        this.author = a;
        this.comment = c;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

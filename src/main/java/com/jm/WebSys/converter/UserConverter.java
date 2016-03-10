package com.jm.WebSys.converter;

import com.jm.WebSys.domain.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by James on 06/03/2016.
 *
 * Class for converting database objects from MongoDB into Java Objects.
 *
 */
public class UserConverter {

    //Convert a user object to MongoDB Object.
    public static DBObject toDBObject(User u) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append("fname", u.getFname()).append("sname", u.getSname())
                .append("dob", u.getDob())
                .append("username", u.getUsername())
                .append("password", u.getPassword());

        //If the object already has an ID, search via that ID as well.
        if(u.getId() != null) {
            builder = builder.append("_id", new ObjectId(u.getId()));
        }
        return builder.get();
    }

    //Convert a DB object into a Java Object.
    public static User toUser(DBObject obj) {
        User u = new User();
        ObjectId id = (ObjectId) obj.get("_id");
        u.setFname((String) obj.get("fname"));
        u.setSname((String) obj.get("sname"));
        u.setDob((String) obj.get("dob"));
        u.setUsername((String) obj.get("username"));
        u.setPassword((String) obj.get("password"));
        return u;
    }
}

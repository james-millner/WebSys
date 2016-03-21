package com.jm.WebSys.converter;

import com.jm.WebSys.domain.Recipe;
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
public class RecipeConverter {

    //Convert a user object to MongoDB Object.
    public static DBObject toDBObject(Recipe r) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append("rname", r.getRname()).append("rdesc", r.getRdesc())
                .append("ringredients", r.getRingredients())
                .append("rhours", r.getRhours())
                .append("rmins", r.getRmins())
                .append("radditional", r.getAdditional());

        //If the object already has an ID, search via that ID as well.
        if(r.getId() != null) {
            builder = builder.append("_id", new ObjectId(r.getId()));
        }
        return builder.get();
    }

    //Convert a DB object into a Java Object.
    public static Recipe toUser(DBObject obj) {
        Recipe r = new Recipe();
        ObjectId id = (ObjectId) obj.get("_id");
        r.setRname((String) obj.get("rname"));
        r.setRdesc((String) obj.get("rdesc"));
        r.setRingredients((String) obj.get("ringredients"));
        r.setRhours((Integer) obj.get("rhours"));
        r.setRmins((Integer) obj.get("rmins"));
        r.setAdditional((String) obj.get("radditional"));
        return r;
    }
}

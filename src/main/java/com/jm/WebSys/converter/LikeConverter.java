package com.jm.WebSys.converter;

import com.jm.WebSys.domain.Like;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import org.bson.types.ObjectId;

/**
 * Created by James on 05/04/2016.
 *
 * Like Converter Object
 *
 * This converts the database objects for likes into Java Objects, which are used in the Controllers.
 */
public class LikeConverter {

    //Convert a user object to MongoDB Object.
    public static DBObject toDBObject(Like like) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append("rid", like.getRid())
                .append("uid", like.getUid());
        //If the object already has an ID, search via that ID as well.
        if (like.getId() != null) {
            builder = builder.append("_id", new ObjectId(like.getId()));
        }
        return builder.get();
    }
    //Convert a DB object into a Java Object.
    public static Like toLike(DBObject obj) {
        Like like = new Like();
        ObjectId id = (ObjectId) obj.get("_id");
        like.setId(id.toString());
        like.setRid((String) obj.get("rid"));
        like.setUid((String) obj.get("uid"));

        return like;

    }
}

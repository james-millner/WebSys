package com.jm.WebSys.converter;


import com.jm.WebSys.domain.Comment;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by James on 29/03/2016.
 */
public class CommentConverter {

    //Convert a user object to MongoDB Object.
    public static DBObject toDBObject(Comment comment) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append("rid", comment.getRid()).append("timestamp", comment.getTimestamp())
                .append("author", comment.getAuthor())
                .append("comment", comment.getComment());

        //If the object already has an ID, search via that ID as well.
        if (comment.getId() != null) {
            builder = builder.append("_id", new ObjectId(comment.getId()));
        }
        return builder.get();
    }

    //Convert a DB object into a Java Object.
    public static Comment toComment(DBObject obj) {
        Comment comment = new Comment();
        ObjectId id = (ObjectId) obj.get("_id");
        comment.setId((String) obj.get("rid"));
        comment.setTimestamp((Date) obj.get("timestamp"));
        comment.setAuthor((String) obj.get("author"));
        comment.setComment((String) obj.get("comment"));
        return comment;
    }
}

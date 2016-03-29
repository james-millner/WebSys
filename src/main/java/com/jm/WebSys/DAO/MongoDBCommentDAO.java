package com.jm.WebSys.DAO;

import com.jm.WebSys.converter.CommentConverter;
import com.jm.WebSys.converter.RecipeConverter;
import com.jm.WebSys.domain.Comment;
import com.jm.WebSys.domain.Recipe;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 29/03/2016.
 */
public class MongoDBCommentDAO {

    private DBCollection db;

    public MongoDBCommentDAO(MongoClient mongo) {
        this.db = mongo.getDB("WebSys").getCollection("comments");
    }

    public Comment createComment(Comment comment) {
        DBObject obj = CommentConverter.toDBObject(comment);
        this.db.insert(obj);
        ObjectId id = (ObjectId) obj.get("_id");
        comment.setId(id.toString());
        return comment;
    }

    public List<Comment> getRecipeComments(Comment comment) {
        String rid = comment.getRid();
        List<Comment> comments = new ArrayList<Comment>();
        DBObject query = new BasicDBObject();
        query.put("rid", rid);
        DBCursor cursor = db.find(query);
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            Comment c = CommentConverter.toComment(obj);
            comments.add(c);
        }
        return comments;
    }

}

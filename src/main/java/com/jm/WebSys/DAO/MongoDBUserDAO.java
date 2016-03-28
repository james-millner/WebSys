package com.jm.WebSys.DAO;

import com.jm.WebSys.domain.User;
import com.jm.WebSys.converter.UserConverter;

import com.mongodb.*;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;

/**
 * Created by James on 06/03/2016.
 */
public class MongoDBUserDAO {

    private DBCollection db;

    public MongoDBUserDAO(MongoClient mongo) {
        this.db = mongo.getDB("WebSys").getCollection("users");
    }

    public User createUser(User u) {
        DBObject obj = UserConverter.toDBObject(u);
        this.db.insert(obj);
        ObjectId id = (ObjectId) obj.get("_id");
        u.setId(id.toString());
        return u;
    }

    public User getUser(User user) {
        String name = user.getUsername();
        DBObject query = new BasicDBObject();
        query.put("username", name);
        DBCursor cursor = db.find(query);
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            user = UserConverter.toUser(obj);
        }
        return user;
    }
}

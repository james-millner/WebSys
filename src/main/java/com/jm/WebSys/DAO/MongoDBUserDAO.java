package com.jm.WebSys.DAO;

import com.jm.WebSys.domain.User;
import com.jm.WebSys.converter.UserConverter;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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
}

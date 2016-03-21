package com.jm.WebSys.DAO;

import com.jm.WebSys.domain.Recipe;
import com.jm.WebSys.converter.RecipeConverter;

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
public class MongoDBRecipeDAO {

    private DBCollection db;

    public MongoDBRecipeDAO(MongoClient mongo) {
        this.db = mongo.getDB("WebSys").getCollection("recipes");
    }

    public Recipe createRecipe(Recipe r) {
        DBObject obj = RecipeConverter.toDBObject(r);
        this.db.insert(obj);
        ObjectId id = (ObjectId) obj.get("_id");
        r.setId(id.toString());
        return r;
    }
}

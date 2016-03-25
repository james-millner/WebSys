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

import java.util.ArrayList;
import java.util.List;

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

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<Recipe>();
        DBCursor cursor = db.find();
        while(cursor.hasNext()) {
            DBObject object = cursor.next();
            Recipe recipe = RecipeConverter.toRecipe(object);
            recipes.add(recipe);
        }
        return recipes;
    }

    public Recipe getRecipe(Recipe recipe) {
        DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(recipe.getId())).get();
        DBObject result = this.db.findOne(query);
        return RecipeConverter.toRecipe(result);
    }
}

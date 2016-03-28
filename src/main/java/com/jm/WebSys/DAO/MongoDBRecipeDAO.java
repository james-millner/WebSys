package com.jm.WebSys.DAO;

import com.jm.WebSys.converter.UserConverter;
import com.jm.WebSys.domain.Recipe;
import com.jm.WebSys.converter.RecipeConverter;

import com.mongodb.*;

import com.mongodb.DBCollection;
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

    public List<Recipe> getCreatorsRecipes(Recipe recipe) {
        String creator = recipe.getCreator();
        List<Recipe> recipes = new ArrayList<Recipe>();
        DBObject query = new BasicDBObject();
        query.put("creator", creator);
        DBCursor cursor = db.find(query);
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            Recipe r = RecipeConverter.toRecipe(obj);
            recipes.add(r);
        }
        return recipes;
    }

    public void updateRecipe(Recipe recipe) {
        DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(recipe.getId())).get();
        this.db.update(query, RecipeConverter.toDBObject(recipe));
    }
}

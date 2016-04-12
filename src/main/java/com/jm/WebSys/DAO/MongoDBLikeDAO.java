package com.jm.WebSys.DAO;

import com.jm.WebSys.converter.LikeConverter;
import com.jm.WebSys.domain.Like;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 05/04/2016.
 *
 * Mongo Like Access Object.
 *
 * Handles all the methods for retrieving Like Objects.
 */
public class MongoDBLikeDAO {

    private DBCollection db;

    public MongoDBLikeDAO(MongoClient mongo) {
        this.db = mongo.getDB("WebSys").getCollection("likes");
    }

    public Like createLike(Like like) {
        DBObject obj = LikeConverter.toDBObject(like);
        this.db.insert(obj);
        ObjectId id = (ObjectId) obj.get("_id");
        like.setId(id.toString());
        return like;
    }

    public List<Like> getLikes() {
        List<Like> likes = new ArrayList<Like>();
        DBCursor cursor = db.find();
        while(cursor.hasNext()) {
            DBObject object = cursor.next();
            Like like = LikeConverter.toLike(object);
            likes.add(like);
        }
        return likes;
    }

    public List<Like> getLikesByRID(Like like) {
        String rid = like.getRid();
        List<Like> likes = new ArrayList<Like>();
        DBObject query = new BasicDBObject();
        query.put("rid", rid);
        DBCursor cursor = db.find(query);
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            Like c = LikeConverter.toLike(obj);
            likes.add(c);
        }
        return likes;
    }

    public List<Like> getLikesByUID(Like like) {
        String uid = like.getUid();
        List<Like> likes = new ArrayList<Like>();
        DBObject query = new BasicDBObject();
        query.put("uid", uid);
        DBCursor cursor = db.find(query);
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            Like c = LikeConverter.toLike(obj);
            likes.add(c);
        }
        return likes;
    }

    public void deleteLike(Like l) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(l.getId())).get();
        this.db.remove(query);
    }

}

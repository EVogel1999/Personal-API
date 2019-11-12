package com.example.personal.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseAccess {
    public static final String PERSONAL_DB = "personal";
    public static final String PROJECT_COLLECTION = "project";

    private static DatabaseAccess access;

    private MongoClient client;

    public static DatabaseAccess getInstance() {
        if (access == null) {
            access = new DatabaseAccess();
        }
        return access;
    }

    private DatabaseAccess() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGO_CONNECTION");
        client = new MongoClient(new MongoClientURI(uri));
    }

    public MongoDatabase getDatabase(String db_name) {
        return client.getDatabase(db_name);
    }
}

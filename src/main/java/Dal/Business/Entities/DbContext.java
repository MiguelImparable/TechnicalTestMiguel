package Dal.Business.Entities;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import Dal.Dal.Repositories.PlayListRepository;

public class DbContext {

    private static PlayListRepository playListRepository;
    private static DbContext instance;
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    private DbContext(String connectionString, String databaseName) {
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(databaseName);
    }

    public static DbContext getInstance(String connectionString, String databaseName) {
        if (instance == null) {
            synchronized (DbContext.class) {
                if (instance == null) {
                    instance = new DbContext(connectionString, databaseName);
                }
            }
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public PlayListRepository getPlayListRepository() {
        return playListRepository;
    }
}

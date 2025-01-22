package Dal.Dal.Repositories;

import Dal.Dal.Repositories.Abstractions.IRepository;
import Entities.Business.Entities.Playlist;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class PlayListRepository implements IRepository<Playlist> {

    private final MongoCollection<Playlist> collection;

    public PlayListRepository(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("yourDatabaseName"); // Cambia "yourDatabaseName" por el nombre de tu base de datos
        this.collection = database.getCollection("playlist", Playlist.class); // Cambia "playlist" por el nombre de tu colección
    }

    @Override
    public CompletableFuture<Void> addAsync(Playlist entity) {
        return CompletableFuture.runAsync(() -> collection.insertOne(entity));
    }

    @Override
    public CompletableFuture<Void> updateAsync(Playlist entity) {
        return CompletableFuture.runAsync(() -> {
            if (entity.getId() != null) {
                collection.replaceOne(
                    new org.bson.Document("_id", new ObjectId(entity.getId())),
                    entity
                );
            } else {
                throw new IllegalArgumentException("Entity ID cannot be null for update.");
            }
        });
    }

    @Override
    public CompletableFuture<Void> deleteAsync(Playlist entity) {
        return CompletableFuture.runAsync(() -> {
            if (entity.getId() != null) {
                collection.deleteOne(new org.bson.Document("_id", new ObjectId(entity.getId())));
            } else {
                throw new IllegalArgumentException("Entity ID cannot be null for delete.");
            }
        });
    }

    @Override
    public CompletableFuture<Optional<Playlist>> getByIdAsync(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Playlist playlist = collection.find(new org.bson.Document("_id", new ObjectId(id))).first();
                return Optional.ofNullable(playlist);
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public CompletableFuture<List<Playlist>> getAllAsync() {
        return CompletableFuture.supplyAsync(() -> collection.find().into(new java.util.ArrayList<>()));
    }

    @Override
    public CompletableFuture<List<Playlist>> findAsync(java.util.function.Predicate<Playlist> predicate) {
        return CompletableFuture.supplyAsync(() -> {
            List<Playlist> playlists = collection.find().into(new java.util.ArrayList<>());
            return playlists.stream().filter(predicate).collect(Collectors.toList());
        });
    }

    @Override
    public CompletableFuture<Boolean> anyAsync(java.util.function.Predicate<Playlist> predicate) {
        return CompletableFuture.supplyAsync(() -> {
            List<Playlist> playlists = collection.find().into(new java.util.ArrayList<>());
            return playlists.stream().anyMatch(predicate);
        });
    }
}

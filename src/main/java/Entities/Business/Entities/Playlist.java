package Entities.Business.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "PlayLists")
public class Playlist {

    @Id 
    private UUID id;

    private String name;

    public Playlist() {
    }

    // Constructor con parámetros
    public Playlist(String name) {
        this.name = name;
    }

    // Getters y Setters
    public Date getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

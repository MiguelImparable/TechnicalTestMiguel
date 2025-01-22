package Module.Business.WorkFlow;

import Dal.Business.Entities.DbContext;
import Entities.Business.Dto.CreatePlaylistRequest;
import Entities.Business.Dto.GenericResponse;
import Entities.Business.Entities.Playlist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayListWF {

    private final DbContext dbContext;

    public PlayListWF(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    public GenericResponse<List<Playlist>> addPlaylist(CreatePlaylistRequest request) {
        GenericResponse<List<Playlist>> response = new GenericResponse<>();

        if (request == null || request.getName() == null || request.getName().trim().isEmpty()) {
            response.setExceptions(List.of("Invalid playlist request: Name cannot be null or empty."));
            return response;
        }

        Playlist newPlaylist = new Playlist();
        newPlaylist.setId(UUID.randomUUID());
        newPlaylist.setName(request.getName());

        dbContext.getPlayListRepository().addAsync(newPlaylist);

        List<Playlist> playlists = (List<Playlist>) dbContext.getPlayListRepository().getAllAsync();
        response.setObject(playlists);
        return response;
    }

    public GenericResponse<List<Playlist>> getAllPlaylists() {
        GenericResponse<List<Playlist>> response = new GenericResponse<>();
        List<Playlist> playlists = (List<Playlist>) dbContext.getPlayListRepository().getAllAsync();
        response.setObject(playlists);
        return response;
    }

    public GenericResponse<List<Playlist>> getPlaylistByName(String name) {
        GenericResponse<List<Playlist>> response = new GenericResponse<>();

        if (name == null || name.trim().isEmpty()) {
            response.setExceptions(List.of("Playlist name cannot be null or empty."));
            return response;
        }

        List<Playlist> playlists = (List<Playlist>) dbContext.getPlayListRepository().findAsync(playlist -> playlist.getName().equalsIgnoreCase(name));

        response.setObject(playlists);
        return response;
    }

    public GenericResponse<String> deletePlaylist(String id) {
        GenericResponse<String> response = new GenericResponse<>();
    
        if (id == null || id.trim().isEmpty()) {
            response.setExceptions(List.of("Playlist ID cannot be null or empty."));
            return response;
        }
    
        try {
            Optional<Playlist> playlistToDelete = dbContext.getPlayListRepository().getByIdAsync(id).join();
    
            if (playlistToDelete.isEmpty()) {
                response.setExceptions(List.of("Playlist not found with the provided ID."));
                return response;
            }
    
            dbContext.getPlayListRepository().deleteAsync(playlistToDelete.get()).join();
            response.setObject("Playlist successfully deleted.");
        } catch (Exception e) {
            response.setExceptions(List.of("An error occurred while deleting the playlist: " + e.getMessage()));
        }
    
        return response;
    }
    
}

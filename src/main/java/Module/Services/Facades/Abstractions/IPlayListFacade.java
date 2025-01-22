package Module.Services.Facades.Abstractions;

import java.util.List;
import Entities.Business.Dto.CreatePlaylistRequest;
import Entities.Business.Dto.GenericResponse;
import Entities.Business.Entities.Playlist;

public interface IPlayListFacade {
    
    GenericResponse<List<Playlist>> addPlaylist(CreatePlaylistRequest request);

    GenericResponse<List<Playlist>> getAllPlaylists();

    GenericResponse<List<Playlist>> getPlaylistByName(String request);

    GenericResponse<String> deletePlaylist(String request);
}

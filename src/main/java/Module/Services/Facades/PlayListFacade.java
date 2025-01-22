package Module.Services.Facades;

import java.util.List;

import Dal.Business.Entities.DbContext;
import Module.Business.WorkFlow.PlayListWF;
import Module.Services.Facades.Abstractions.IPlayListFacade;
import Entities.Business.Dto.CreatePlaylistRequest;
import Entities.Business.Dto.GenericResponse;
import Entities.Business.Entities.Playlist;

public class PlayListFacade implements IPlayListFacade {

    private final PlayListWF playListWF;

    public PlayListFacade(DbContext dbContext) {
        this.playListWF = new PlayListWF(dbContext);
    }

    @Override
    public GenericResponse<List<Playlist>> addPlaylist(CreatePlaylistRequest request) {
        return playListWF.addPlaylist(request);
    }

    @Override
    public GenericResponse<List<Playlist>> getAllPlaylists() {
        return playListWF.getAllPlaylists();
    }

    @Override
    public GenericResponse<List<Playlist>> getPlaylistByName(String request) {
        return playListWF.getPlaylistByName(request);
    }

    @Override
    public GenericResponse<String> deletePlaylist(String request) {
        return playListWF.deletePlaylist(request);
    }
}

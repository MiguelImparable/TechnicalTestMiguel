package Host.Controllers;

import Entities.Business.Dto.CreatePlaylistRequest;
import Entities.Business.Dto.GenericResponse;
import Entities.Business.Entities.Playlist;
import Host.Validators.ValidatorRequest;
import Module.Services.Facades.Abstractions.IPlayListFacade;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lists")
public class PlayListController {

    private final IPlayListFacade playListFacade;

    public PlayListController(IPlayListFacade playListFacade) {
        this.playListFacade = playListFacade;
    }

    /**
     * Endpoint para agregar una nueva lista de reproducción.
     * @param playlist La lista de reproducción a agregar.
     * @return Una respuesta HTTP con el estado 201 si la lista es creada exitosamente, o 400 si el nombre es inválido.
     */
    @PostMapping
    public GenericResponse<List<Playlist>> addPlaylist(@RequestBody CreatePlaylistRequest request) {  
        ValidatorRequest<CreatePlaylistRequest> validatorRequest = new ValidatorRequest<CreatePlaylistRequest>();
        
        if (validatorRequest.Validate(request)) {
            return playListFacade.addPlaylist(request);
        }

        return null;
    }

    /**
     * Endpoint para obtener todas las listas de reproducción.
     * @return Una lista de todas las listas de reproducción existentes.
     */
    @GetMapping
    public GenericResponse<List<Playlist>> getAllPlaylists() {
        return playListFacade.getAllPlaylists();
    }

    /**
     * Endpoint para obtener una lista de reproducción por su nombre.
     * @param listName El nombre de la lista de reproducción.
     * @return La lista de reproducción seleccionada si existe, o un error 404 si no se encuentra.
     */
    @GetMapping("/{listName}")
    public GenericResponse<List<Playlist>> getPlaylistByName(@PathVariable String request) {
        ValidatorRequest<String> validatorRequest = new ValidatorRequest<String>();

        if (validatorRequest.Validate(request)) {
            return playListFacade.getPlaylistByName(request);
        }

        return null;
    }

    /**
     * Endpoint para borrar una lista de reproducción.
     * @param listName El nombre de la lista a borrar.
     * @return Respuesta HTTP con el estado 204 si la eliminación es exitosa o 404 si la lista no existe.
     */
    @DeleteMapping("/{listName}")
    public GenericResponse<String> deletePlaylist(@PathVariable String request) {
        ValidatorRequest<String> validatorRequest = new ValidatorRequest<String>();

        if (validatorRequest.Validate(request)) {
            return playListFacade.deletePlaylist(request);
        }

        return null;
    }
}

package bts.sio.api.controller;

import bts.sio.api.model.Olympiade;
import bts.sio.api.service.OlympiadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@RestController
@Tag(name = "Olympiades", description = "API de gestion des Olympiades")
public class OlympiadeController {

    @Autowired
    private OlympiadeService olympiadeService;

    /**
     * Read - Get one olympiade
     * @param id The id of the olympiade
     * @return An Olympiade object full filled
     */
    @Operation(summary = "Récupérer une Olympiade par son ID",
            description = "Recherche une Olympiade spécifique par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Olympiade trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Olympiade.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Olympiade non trouvée",
                    content = @Content)
    })
    @GetMapping("/olympiade/{id}")
    public Olympiade getOlympiade(
            @Parameter(description = "ID de l'Olympiade à récupérer")
            @PathVariable("id") final Long id) {
        Optional<Olympiade> olympiade = olympiadeService.getOlympiade(id);
        if(olympiade.isPresent()) {
            return olympiade.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all olympiades
     * @return - An Iterable object of Olympiade full filled
     */
    @Operation(summary = "Récupérer toutes les Olympiades",
            description = "Renvoie la liste complète des Olympiades disponibles")
    @ApiResponse(responseCode = "200",
            description = "Liste des Olympiades récupérée avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Olympiade.class)) })
    @GetMapping("/olympiades")
    public Iterable<Olympiade> getLesOlympiades() {
        return olympiadeService.getLesOlympiades();
    }
}

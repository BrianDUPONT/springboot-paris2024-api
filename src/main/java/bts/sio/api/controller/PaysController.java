package bts.sio.api.controller;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Pays;
import bts.sio.api.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pays", description = "API de gestion des pays participants aux Jeux Olympiques Paris 2024")
public class PaysController {

    @Autowired
    private PaysService paysService;

    /**
     * Read - Get all pays
     * @return - An Iterable object of Pays full filled
     */
    @Operation(summary = "Récupérer tous les pays",
            description = "Renvoie la liste complète des pays participants aux Jeux Olympiques")
    @ApiResponse(responseCode = "200",
            description = "Liste des pays récupérée avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pays.class)) })
    @GetMapping("/pays")
    public Iterable<Pays> getAthletes() {
        return paysService.getLesPays();
    }

    /**
     * Read - Get one pays
     * @param id The id of the pays
     * @return An Athlete object full filled
     */
    @GetMapping("/pays/{id}")
    public Pays getAthlete(@PathVariable("id") final Long id) {
        Optional<Pays> pays = paysService.getPays(id);
        if(pays.isPresent()) {
            return pays.get();
        } else {
            return null;
        }
    }

    /**
     * Create - Add a new pays
     * @param pays An object pays
     * @return The pays object saved
     */
    @PostMapping("/pays")
    public Pays createPays(@RequestBody Pays pays) {
        return paysService.savePays(pays);
    }
}

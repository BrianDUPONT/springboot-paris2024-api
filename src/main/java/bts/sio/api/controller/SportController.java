package bts.sio.api.controller;

import bts.sio.api.model.Sport;
import bts.sio.api.service.SportService;
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
@Tag(name = "Sports", description = "API de gestion des sports participants aux Jeux Olympiques Paris 2024")
public class SportController {

    @Autowired
    private SportService sportService;

    /**
     * Create - Add a new sport
     * @param sport An object sport
     * @return The sport object saved
     */
    @Operation(summary = "Créer un nouveau sport",
            description = "Ajoute un nouveau sport à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Sport créé avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sport.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/sport")
    public Sport createSport(@RequestBody Sport sport) {
        return sportService.saveSport(sport);
    }

    /**
     * Read - Get one sport
     * @param id The id of the sport
     * @return An Sport object full filled
     */
    @Operation(summary = "Récupérer un sport par son ID",
            description = "Recherche un sport spécifique par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Sport trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sport.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Sport non trouvé",
                    content = @Content)
    })
    @GetMapping("/sport/{id}")
    public Sport getSport(
            @Parameter(description = "ID du sport à récupérer")
            @PathVariable("id") final Long id) {
        Optional<Sport> sport = sportService.getSport(id);
        if(sport.isPresent()) {
            return sport.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all sports
     * @return - An Iterable object of Sport full filled
     */
    @Operation(summary = "Récupérer tous les sports",
            description = "Renvoie la liste complète des sports disponibles")
    @ApiResponse(responseCode = "200",
            description = "Liste des sports récupérée avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Sport.class)) })
    @GetMapping("/sports")
    public Iterable<Sport> getSports() {
        return sportService.getSports();
    }

    /**
     * Update - Update an existing sport
     * @param id - The id of the sport to update
     * @param sport - The sport object updated
     * @return The updated sport object
     */
    @Operation(summary = "Mettre à jour un sport",
            description = "Met à jour les informations d'un sport existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Sport mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sport.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Sport non trouvé",
                    content = @Content)
    })
    @PutMapping("/sport/{id}")
    public Sport updateSport(
            @Parameter(description = "ID du sport à mettre à jour")
            @PathVariable("id") final Long id,
            @Parameter(description = "Données actualisées du sport")
            @RequestBody Sport sport) {
        Optional<Sport> s = sportService.getSport(id);
        if(s.isPresent()) {
            Sport currentSport = s.get();

            String nom = sport.getNom();
            if(nom != null) {
                currentSport.setNom(nom);
            }
            String descriptif = sport.getDescriptif();
            if(descriptif != null) {
                currentSport.setDescriptif(descriptif);
            }

            sportService.saveSport(currentSport);
            return currentSport;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a sport
     * @param id - The id of the sport to delete
     */
    @Operation(summary = "Supprimer un sport",
            description = "Supprime un sport existant par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Sport supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Sport non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/sport/{id}")
    public void deleteSport(
            @Parameter(description = "ID du sport à supprimer")
            @PathVariable("id") final Long id) {
        sportService.deleteSport(id);
    }
}

package bts.sio.api.controller;

import bts.sio.api.model.Epreuve;
import bts.sio.api.service.EpreuveService;
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
@Tag(name = "Épreuves", description = "API de gestion des épreuves des Jeux Olympiques Paris 2024")
public class EpreuveController {

    @Autowired
    private EpreuveService epreuveService;

    /**
     * Create - Add a new epreuve
     * @param epreuve An object epreuve
     * @return The epreuve object saved
     */
    @Operation(summary = "Créer une nouvelle épreuve",
            description = "Ajoute une nouvelle épreuve à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Épreuve créée avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Epreuve.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/epreuve")
    public Epreuve createEpreuve(
            @Parameter(description = "Épreuve à créer")
            @RequestBody Epreuve epreuve) {
        return epreuveService.saveEpreuve(epreuve);
    }

    /**
     * Read - Get one epreuve
     * @param id The id of the epreuve
     * @return An Epreuve object full filled
     */
    @Operation(summary = "Récupérer une épreuve par son ID",
            description = "Recherche une épreuve spécifique par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Épreuve trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Epreuve.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Épreuve non trouvée",
                    content = @Content)
    })
    @GetMapping("/epreuve/{id}")
    public Epreuve getEpreuve(
            @Parameter(description = "ID de l'épreuve à récupérer")
            @PathVariable("id") final Long id) {
        Optional<Epreuve> epreuve = epreuveService.getEpreuve(id);
        if(epreuve.isPresent()) {
            return epreuve.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all epreuves
     * @return - An Iterable object of Epreuve full filled
     */
    @Operation(summary = "Récupérer toutes les épreuves",
            description = "Renvoie la liste complète des épreuves disponibles")
    @ApiResponse(responseCode = "200",
            description = "Liste des épreuves récupérée avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Epreuve.class)) })
    @GetMapping("/epreuves")
    public Iterable<Epreuve> getEpreuves() {
        return epreuveService.getEpreuves();
    }

    /**
     * Update - Update an existing epreuve
     * @param id - The id of the epreuve to update
     * @param epreuve - The epreuve object updated
     * @return
     */
    @Operation(summary = "Mettre à jour une épreuve",
            description = "Met à jour les informations d'une épreuve existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Épreuve mise à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Epreuve.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Épreuve non trouvée",
                    content = @Content)
    })
    @PutMapping("/epreuve/{id}")
    public Epreuve updateEpreuve(
            @Parameter(description = "ID de l'épreuve à mettre à jour")
            @PathVariable("id") final Long id,
            @Parameter(description = "Données actualisées de l'épreuve")
            @RequestBody Epreuve epreuve) {
        Optional<Epreuve> s = epreuveService.getEpreuve(id);
        if(s.isPresent()) {
            Epreuve currentEpreuve = s.get();

            String nom = epreuve.getNom();
            if(nom != null) {
                currentEpreuve.setNom(nom);
            }

            epreuveService.saveEpreuve(currentEpreuve);
            return currentEpreuve;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an epreuve
     * @param id - The id of the epreuve to delete
     */
    @Operation(summary = "Supprimer une épreuve",
            description = "Supprime une épreuve existante par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Épreuve supprimée avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Épreuve non trouvée",
                    content = @Content)
    })
    @DeleteMapping("/epreuve/{id}")
    public void deleteEpreuve(
            @Parameter(description = "ID de l'épreuve à supprimer")
            @PathVariable("id") final Long id) {
        epreuveService.deleteEpreuve(id);
    }
}

package bts.sio.api.controller;

import bts.sio.api.model.Actualite;
import bts.sio.api.model.Sport;
import bts.sio.api.model.Epreuve;
import bts.sio.api.service.ActualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@Tag(name = "Actualités", description = "API de gestion des actualités des Jeux Olympiques Paris 2024")
public class ActualiteController {

    @Autowired
    private ActualiteService actualiteService;

    /**
     * Create - Add a new actualite
     * @param actualite An object actualite
     * @return The actualite object saved
     */
    @Operation(summary = "Créer une nouvelle actualité",
            description = "Ajoute une nouvelle actualité à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Actualité créée avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Actualite.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/actualite")
    public Actualite createActualite(
            @Parameter(description = "Actualité à créer")
            @RequestBody Actualite actualite) {
        return actualiteService.saveActualite(actualite);
    }

    /**
     * Read - Get one actualite
     * @param id The id of the actualite
     * @return An Actualite object full filled
     */
    @Operation(summary = "Récupérer une actualité par son ID",
            description = "Recherche une actualité spécifique par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Actualité trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Actualite.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Actualité non trouvée",
                    content = @Content)
    })
    @GetMapping("/actualite/{id}")
    public Actualite getActualite(
            @Parameter(description = "ID de l'actualité à récupérer")
            @PathVariable("id") final Long id) {
        Optional<Actualite> actualite = actualiteService.getActualite(id);
        if(actualite.isPresent()) {
            return actualite.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all actualites
     * @return - An Iterable object of Actualite full filled
     */
    @Operation(summary = "Récupérer toutes les actualités",
            description = "Renvoie la liste complète des actualités disponibles")
    @ApiResponse(responseCode = "200",
            description = "Liste des actualités récupérée avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Actualite.class)) })
    @GetMapping("/actualites")
    public Iterable<Actualite> getActualites() {
        return actualiteService.getActualites();
    }

    /**
     * Update - Update an existing actualite
     * @param id - The id of the actualite to update
     * @param actualite - The actualite object updated
     * @return
     */
    @Operation(summary = "Mettre à jour une actualité",
            description = "Met à jour les informations d'une actualité existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Actualité mise à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Actualite.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Actualité non trouvée",
                    content = @Content)
    })
    @PutMapping("/actualite/{id}")
    public Actualite updateActualite(
            @Parameter(description = "ID de l'actualité à mettre à jour")
            @PathVariable("id") final Long id,
            @Parameter(description = "Données actualisées de l'actualité")
            @RequestBody Actualite actualite) {
        Optional<Actualite> a = actualiteService.getActualite(id);
        if(a.isPresent()) {
            Actualite currentActualite = a.get();

            String titre = actualite.getTitre();
            if(titre != null) {
                currentActualite.setTitre(titre);
            }
            String contenu = actualite.getContenu();
            if(contenu != null) {
                currentActualite.setContenu(contenu);;
            }

            LocalDate date = actualite.getDate();
            if(date != null) {
                currentActualite.setDate(date);
            }

            Epreuve epreuve = actualite.getEpreuve();
            if(epreuve != null) {
                currentActualite.setEpreuve(epreuve);;
            }

            Sport sport = actualite.getSport();
            if(sport != null) {
                currentActualite.setSport(sport);;
            }

            actualiteService.saveActualite(currentActualite);
            return currentActualite;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an actualite
     * @param id - The id of the actualite to delete
     */
    @Operation(summary = "Supprimer une actualité",
            description = "Supprime une actualité existante par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Actualité supprimée avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Actualité non trouvée",
                    content = @Content)
    })
    @DeleteMapping("/actualite/{id}")
    public void deleteActualite(
            @Parameter(description = "ID de l'actualité à supprimer")
            @PathVariable("id") final Long id) {
        actualiteService.deleteActualite(id);
    }
}
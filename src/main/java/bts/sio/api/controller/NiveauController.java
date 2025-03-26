package bts.sio.api.controller;

import bts.sio.api.model.Niveau;
import bts.sio.api.service.NiveauService;
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
@Tag(name = "Niveaux", description = "API de gestion des niveaux des Jeux Olympiques Paris 2024")
public class NiveauController {

    @Autowired
    private NiveauService niveauService;

    /**
     * Create - Add a new niveau
     * @param niveau An object niveau
     * @return The niveau object saved
     */
    @Operation(summary = "Créer un nouveau niveau",
            description = "Ajoute un nouveau niveau à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Niveau créé avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Niveau.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/niveau")
    public Niveau createNiveau(
            @Parameter(description = "Niveau à créer")
            @RequestBody Niveau niveau) {
        return niveauService.saveNiveau(niveau);
    }

    /**
     * Read - Get one niveau
     * @param id The id of the niveau
     * @return An Niveau object full filled
     */
    @Operation(summary = "Récupérer un niveau par son ID",
            description = "Recherche un niveau spécifique par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Niveau trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Niveau.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Niveau non trouvé",
                    content = @Content)
    })
    @GetMapping("/niveau/{id}")
    public Niveau getNiveau(
            @Parameter(description = "ID du niveau à récupérer")
            @PathVariable("id") final Long id) {
        Optional<Niveau> niveau = niveauService.getNiveau(id);
        return niveau.orElse(null);
    }

    /**
     * Read - Get all niveaux
     * @return - An Iterable object of Niveau full filled
     */
    @Operation(summary = "Récupérer tous les niveaux",
            description = "Renvoie la liste complète des niveaux disponibles")
    @ApiResponse(responseCode = "200",
            description = "Liste des niveaux récupérée avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Niveau.class)) })
    @GetMapping("/niveaux")
    public Iterable<Niveau> getNiveaux() {
        return niveauService.getNiveaux();
    }

    /**
     * Update - Update an existing niveau
     * @param id - The id of the niveau to update
     * @param niveau - The niveau object updated
     * @return
     */
    @Operation(summary = "Mettre à jour un niveau",
            description = "Met à jour les informations d'un niveau existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Niveau mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Niveau.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Niveau non trouvé",
                    content = @Content)
    })
    @PutMapping("/niveau/{id}")
    public Niveau updateNiveau(
            @Parameter(description = "ID du niveau à mettre à jour")
            @PathVariable("id") final Long id,
            @Parameter(description = "Données actualisées du niveau")
            @RequestBody Niveau niveau) {
        Optional<Niveau> a = niveauService.getNiveau(id);
        if(a.isPresent()) {
            Niveau currentNiveau = a.get();

            String libelle = niveau.getLibelle();
            if(libelle != null) {
                currentNiveau.setLibelle(libelle);
            }

            niveauService.saveNiveau(currentNiveau);
            return currentNiveau;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an niveau
     * @param id - The id of the niveau to delete
     */
    @Operation(summary = "Supprimer un niveau",
            description = "Supprime un niveau existant par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Niveau supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Niveau non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/niveau/{id}")
    public void deleteNiveau(
            @Parameter(description = "ID du niveau à supprimer")
            @PathVariable("id") final Long id) {
        niveauService.deleteNiveau(id);
    }

}

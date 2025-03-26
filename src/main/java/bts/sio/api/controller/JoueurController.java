package bts.sio.api.controller;

import bts.sio.api.model.Joueur;
import bts.sio.api.model.Sport;
import bts.sio.api.service.JoueurService;
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
@Tag(name = "Joueurs", description = "API de gestion des joueurs des Jeux Olympiques Paris 2024")
public class JoueurController {

    @Autowired
    private JoueurService joueurService;

    /**
     * Create - Add a new joueur
     * @param joueur An object joueur
     * @return The joueur object saved
     */
    @Operation(summary = "Créer un nouveau joueur",
            description = "Ajoute un nouveau joueur à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Joueur créé avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Joueur.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/joueur")
    public Joueur createJoueur(
            @Parameter(description = "Joueur à créer")
            @RequestBody Joueur joueur) {
        return joueurService.saveJoueur(joueur);
    }

    /**
     * Read - Get one joueur
     * @param id The id of the joueur
     * @return An Joueur object full filled
     */
    @Operation(summary = "Récupérer un joueur par son ID",
            description = "Recherche un joueur spécifique par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Joueur trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Joueur.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Joueur non trouvé",
                    content = @Content)
    })
    @GetMapping("/joueur/{id}")
    public Joueur getJoueur(
            @Parameter(description = "ID du joueur à récupérer")
            @PathVariable("id") final Long id) {
        Optional<Joueur> joueur = joueurService.getJoueur(id);
        return joueur.orElse(null);
    }

    /**
     * Read - Get all joueurs
     * @return - An Iterable object of Joueur full filled
     */
    @Operation(summary = "Récupérer tous les joueurs",
            description = "Renvoie la liste complète des joueurs disponibles")
    @ApiResponse(responseCode = "200",
            description = "Liste des joueurs récupérée avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Joueur.class)) })
    @GetMapping("/joueurs")
    public Iterable<Joueur> getJoueurs() {
        return joueurService.getJoueurs();
    }

    /**
     * Update - Update an existing joueur
     * @param id - The id of the joueur to update
     * @param joueur - The joueur object updated
     * @return
     */
    @Operation(summary = "Mettre à jour un joueur",
            description = "Met à jour les informations d'un joueur existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Joueur mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Joueur.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Joueur non trouvé",
                    content = @Content)
    })
    @PutMapping("/joueur/{id}")
    public Joueur updateJoueur(
            @Parameter(description = "ID du joueur à mettre à jour")
            @PathVariable("id") final Long id,
            @Parameter(description = "Données actualisées du joueur")
            @RequestBody Joueur joueur) {
        Optional<Joueur> a = joueurService.getJoueur(id);
        if(a.isPresent()) {
            Joueur currentJoueur = a.get();

            String nom = joueur.getNom();
            if(nom != null) {
                currentJoueur.setNom(nom);
            }
            String prenom = joueur.getPrenom();
            if(prenom != null) {
                currentJoueur.setPrenom(prenom);
            }

            LocalDate dateNaissance = joueur.getDateNaiss();
            if(dateNaissance != null) {
                currentJoueur.setDateNaiss(dateNaissance);
            }

            Sport sport = joueur.getSport();
            if(sport != null) {
                currentJoueur.setSport(sport);
            }

            joueurService.saveJoueur(currentJoueur);
            return currentJoueur;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an joueur
     * @param id - The id of the joueur to delete
     */
    @Operation(summary = "Supprimer un joueur",
            description = "Supprime un joueur existant par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Joueur supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Joueur non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/joueur/{id}")
    public void deleteJoueur(
            @Parameter(description = "ID du joueur à supprimer")
            @PathVariable("id") final Long id) {
        joueurService.deleteJoueur(id);
    }

}

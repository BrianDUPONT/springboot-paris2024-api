package bts.sio.api.controller;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Sport;
import bts.sio.api.model.Pays;
import bts.sio.api.service.AthleteService;
import bts.sio.api.service.SportService;
import bts.sio.api.service.PaysService;
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
@Tag(name = "Athlètes", description = "API de gestion des athlètes participants aux Jeux Olympiques Paris 2024")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    /**
     * Create - Add a new athlete
     * @param athlete An object athlete
     * @return The athlete object saved
     */
    @Operation(summary = "Créer un nouvel athlète",
            description = "Ajoute un nouvel athlète à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Athlète créé avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/athlete")
    public Athlete createAthlete(
            @Parameter(description = "Athlète à créer")
            @RequestBody Athlete athlete) {
        return athleteService.saveAthlete(athlete);
    }

    /**
     * Read - Get one athlete
     * @param id The id of the athlete
     * @return An Athlete object full filled
     */
    @Operation(summary = "Récupérer un athlète par son ID",
            description = "Recherche un athlète spécifique par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Athlète trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Athlète non trouvé",
                    content = @Content)
    })
    @GetMapping("/athlete/{id}")
    public Athlete getAthlete(
            @Parameter(description = "ID de l'athlète à récupérer")
            @PathVariable("id") final Long id) {
        Optional<Athlete> athlete = athleteService.getAthlete(id);
        if(athlete.isPresent()) {
            return athlete.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all athletes
     * @return - An Iterable object of Athlete full filled
     */
    @Operation(summary = "Récupérer tous les athlètes",
            description = "Renvoie la liste complète des athlètes disponibles")
    @ApiResponse(responseCode = "200",
            description = "Liste des athlètes récupérée avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Athlete.class)) })
    @GetMapping("/athletes")
    public Iterable<Athlete> getAthletes() {
        return athleteService.getAthletes();
    }

    /**
     * Update - Update an existing athlete
     * @param id - The id of the athlete to update
     * @param athlete - The athlete object updated
     * @return
     */
    @Operation(summary = "Mettre à jour un athlète",
            description = "Met à jour les informations d'un athlète existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Athlète mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Athlète non trouvé",
                    content = @Content)
    })
    @PutMapping("/athlete/{id}")
    public Athlete updateAthlete(
            @Parameter(description = "ID de l'athlète à mettre à jour")
            @PathVariable("id") final Long id,
            @Parameter(description = "Données actualisées de l'athlète")
            @RequestBody Athlete athlete) {
        Optional<Athlete> a = athleteService.getAthlete(id);
        if(a.isPresent()) {
            Athlete currentAthlete = a.get();

            String nom = athlete.getNom();
            if(nom != null) {
                currentAthlete.setNom(nom);
            }
            String prenom = athlete.getPrenom();
            if(prenom != null) {
                currentAthlete.setPrenom(prenom);;
            }

            LocalDate dateNaissance = athlete.getDateNaiss();
            if(dateNaissance != null) {
                currentAthlete.setDateNaiss(dateNaissance);
            }

            Pays pays = athlete.getPays();
            if(pays != null) {
                currentAthlete.setPays(pays);;
            }

            Sport sport = athlete.getSport();
            if(sport != null) {
                currentAthlete.setSport(sport);;
            }

            athleteService.saveAthlete(currentAthlete);
            return currentAthlete;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an athlete
     * @param id - The id of the athlete to delete
     */
    @Operation(summary = "Supprimer un athlète",
            description = "Supprime un athlète existant par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Athlète supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Athlète non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/athlete/{id}")
    public void deleteAthlete(
            @Parameter(description = "ID de l'athlète à supprimer")
            @PathVariable("id") final Long id) {
        athleteService.deleteAthlete(id);
    }

    /**
     * Read - Get athletes by country
     * @param paysId The ID of the country
     * @return A list of athletes from the specified country
     */
    @Operation(summary = "Récupérer les athlètes par pays",
            description = "Renvoie la liste des athlètes appartenant à un pays spécifique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des athlètes du pays récupérée avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Pays non trouvé",
                    content = @Content)
    })
    @GetMapping("/athletes/pays/{paysId}")
    public Iterable<Athlete> getAthletesByPays(
            @Parameter(description = "ID du pays dont on veut récupérer les athlètes")
            @PathVariable("paysId") final Long paysId) {
        return athleteService.getAthletesByPays(paysId);
    }
}
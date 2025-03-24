package bts.sio.api.controller;

import bts.sio.api.model.Joueur;
import bts.sio.api.model.Sport;
import bts.sio.api.service.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class JoueurController {

    @Autowired
    private JoueurService joueurService;

    /**
     * Create - Add a new joueur
     * @param joueur An object joueur
     * @return The joueur object saved
     */
    @PostMapping("/joueur")
    public Joueur createJoueur(@RequestBody Joueur joueur) {
        return joueurService.saveJoueur(joueur);
    }


    /**
     * Read - Get one joueur
     * @param id The id of the joueur
     * @return An Joueur object full filled
     */
    @GetMapping("/joueur/{id}")
    public Joueur getJoueur(@PathVariable("id") final Long id) {
        Optional<Joueur> joueur = joueurService.getJoueur(id);
        if(joueur.isPresent()) {
            return joueur.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all joueurs
     * @return - An Iterable object of Joueur full filled
     */
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
    @PutMapping("/joueur/{id}")
    public Joueur updateJoueur(@PathVariable("id") final Long id, @RequestBody Joueur joueur) {
        Optional<Joueur> a = joueurService.getJoueur(id);
        if(a.isPresent()) {
            Joueur currentJoueur = a.get();

            String nom = joueur.getNom();
            if(nom != null) {
                currentJoueur.setNom(nom);
            }
            String prenom = joueur.getPrenom();
            if(prenom != null) {
                currentJoueur.setPrenom(prenom);;
            }

            LocalDate dateNaissance = joueur.getDateNaiss();
            if(dateNaissance != null) {
                currentJoueur.setDateNaiss(dateNaissance);
            }

            Sport sport = joueur.getSport();
            if(sport != null) {
                currentJoueur.setSport(sport);;
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
    @DeleteMapping("/joueur/{id}")
    public void deleteJoueur(@PathVariable("id") final Long id) {
        joueurService.deleteJoueur(id);
    }

}

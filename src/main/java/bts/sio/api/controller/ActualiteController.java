package bts.sio.api.controller;

import bts.sio.api.model.Actualite;
import bts.sio.api.model.Sport;
import bts.sio.api.model.Epreuve;
import bts.sio.api.service.ActualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class ActualiteController {

    @Autowired
    private ActualiteService actualiteService;

    /**
     * Create - Add a new actualite
     * @param actualite An object actualite
     * @return The actualite object saved
     */
    @PostMapping("/actualite")
    public Actualite createActualite(@RequestBody Actualite actualite) {
        return actualiteService.saveActualite(actualite);
    }


    /**
     * Read - Get one actualite
     * @param id The id of the actualite
     * @return An Actualite object full filled
     */
    @GetMapping("/actualite/{id}")
    public Actualite getActualite(@PathVariable("id") final Long id) {
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
    @PutMapping("/actualite/{id}")
    public Actualite updateActualite(@PathVariable("id") final Long id, @RequestBody Actualite actualite) {
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
    @DeleteMapping("/actualite/{id}")
    public void deleteActualite(@PathVariable("id") final Long id) {
        actualiteService.deleteActualite(id);
    }

}

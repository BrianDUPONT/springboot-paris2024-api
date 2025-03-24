package bts.sio.api.controller;

import bts.sio.api.model.Niveau;
import bts.sio.api.service.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class NiveauController {

    @Autowired
    private NiveauService niveauService;

    /**
     * Create - Add a new niveau
     * @param niveau An object niveau
     * @return The niveau object saved
     */
    @PostMapping("/niveau")
    public Niveau createNiveau(@RequestBody Niveau niveau) {
        return niveauService.saveNiveau(niveau);
    }


    /**
     * Read - Get one niveau
     * @param id The id of the niveau
     * @return An Niveau object full filled
     */
    @GetMapping("/niveau/{id}")
    public Niveau getNiveau(@PathVariable("id") final Long id) {
        Optional<Niveau> niveau = niveauService.getNiveau(id);
        if(niveau.isPresent()) {
            return niveau.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all niveaux
     * @return - An Iterable object of Niveau full filled
     */
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
    @PutMapping("/niveau/{id}")
    public Niveau updateNiveau(@PathVariable("id") final Long id, @RequestBody Niveau niveau) {
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
    @DeleteMapping("/niveau/{id}")
    public void deleteNiveau(@PathVariable("id") final Long id) {
        niveauService.deleteNiveau(id);
    }

}

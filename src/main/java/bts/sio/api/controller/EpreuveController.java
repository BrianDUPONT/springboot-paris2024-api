package bts.sio.api.controller;

import bts.sio.api.model.Epreuve;
import bts.sio.api.service.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EpreuveController {

    @Autowired
    private EpreuveService epreuveService;

    /**
     * Create - Add a new epreuve
     * @param epreuve An object epreuve
     * @return The epreuve object saved
     */
    @PostMapping("/epreuve")
    public Epreuve createEpreuve(@RequestBody Epreuve epreuve) {
        return epreuveService.saveEpreuve(epreuve);
    }


    /**
     * Read - Get one epreuve
     * @param id The id of the epreuve
     * @return An Epreuve object full filled
     */
    @GetMapping("/epreuve/{id}")
    public Epreuve getEpreuve(@PathVariable("id") final Long id) {
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
    @PutMapping("/epreuve/{id}")
    public Epreuve updateEpreuve(@PathVariable("id") final Long id, @RequestBody Epreuve epreuve) {
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
    @DeleteMapping("/epreuve/{id}")
    public void deleteEpreuve(@PathVariable("id") final Long id) {
        epreuveService.deleteEpreuve(id);
    }

}

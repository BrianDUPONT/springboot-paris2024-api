package bts.sio.api.controller;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Pays;
import bts.sio.api.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PaysController {

    @Autowired
    private PaysService paysService;

    /**
     * Read - Get all apys
     * @return - An Iterable object of Pays full filled
     */
    @GetMapping("/pays")
    public Iterable<Pays> getAthletes() {
        return paysService.getLesPays();
    }

    /**
     * Read - Get one pays
     * @param id The id of the pays
     * @return An Athlete object full filled
     */
    @GetMapping("/pays/{id}")
    public Pays getAthlete(@PathVariable("id") final Long id) {
        Optional<Pays> pays = paysService.getPays(id);
        if(pays.isPresent()) {
            return pays.get();
        } else {
            return null;
        }
    }

    /**
     * Create - Add a new pays
     * @param pays An object pays
     * @return The pays object saved
     */
    @PostMapping("/pays")
    public Pays createPays(@RequestBody Pays pays) {
        return paysService.savePays(pays);
    }
}

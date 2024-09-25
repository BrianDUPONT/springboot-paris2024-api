package bts.sio.api.controller;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Sport;
import bts.sio.api.model.Pays;
import bts.sio.api.service.AthleteService;
import bts.sio.api.service.SportService;
import bts.sio.api.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    /**
     * Create - Add a new athlete
     * @param athlete An object athlete
     * @return The athlete object saved
     */
    @PostMapping("/athlete")
    public Athlete createAthlete(@RequestBody Athlete athlete) {
        return athleteService.saveAthlete(athlete);
    }


    /**
     * Read - Get one athlete
     * @param id The id of the athlete
     * @return An Athlete object full filled
     */
    @GetMapping("/athlete/{id}")
    public Athlete getAthlete(@PathVariable("id") final Long id) {
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
    @PutMapping("/athlete/{id}")
    public Athlete updateAthlete(@PathVariable("id") final Long id, @RequestBody Athlete athlete) {
        Optional<Athlete> e = athleteService.getAthlete(id);
        if(e.isPresent()) {
            Athlete currentAthlete = e.get();

            String nom = athlete.getNom();
            if(nom != null) {
                currentAthlete.setNom(nom);
            }
            String prenom = athlete.getPrenom();
            if(prenom != null) {
                currentAthlete.setPrenom(prenom);;
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
    @DeleteMapping("/athlete/{id}")
    public void deleteAthlete(@PathVariable("id") final Long id) {
        athleteService.deleteAthlete(id);
    }

}

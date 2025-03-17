package bts.sio.api.service;

import bts.sio.api.model.Athlete;
import bts.sio.api.repository.AthleteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Data
@Service
public class AthleteService {
    @Autowired
    private AthleteRepository athleteRepository;

    public Optional<Athlete> getAthlete(final Long id) {
        return athleteRepository.findById(id);
    }

    public Iterable<Athlete> getAthletes() {
        return athleteRepository.findAll();
    }

    public void deleteAthlete(final Long id) {
        athleteRepository.deleteById(id);
    }

    public Athlete saveAthlete(Athlete athlete) {
        Athlete savedAthlete = athleteRepository.save(athlete);
        return savedAthlete;
    }

    public Iterable<Athlete> getAthletesByPays(Long paysId) {
        return athleteRepository.findByPaysId(paysId);
    }
}

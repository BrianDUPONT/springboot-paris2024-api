package bts.sio.api.service;

import bts.sio.api.model.Sport;
import bts.sio.api.repository.SportRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Data
@Service
public class SportService {
    @Autowired
    private SportRepository sportRepository;

    public Optional<Sport> getSport(final Long id) {
        return sportRepository.findById(id);
    }

    public Iterable<Sport> getSports() {
        return sportRepository.findAll();
    }

    public void deleteSport(final Long id) {
        sportRepository.deleteById(id);
    }

    public Sport saveSport(Sport sport) {
        Sport savedSport = sportRepository.save(sport);
        return savedSport;
    }
}

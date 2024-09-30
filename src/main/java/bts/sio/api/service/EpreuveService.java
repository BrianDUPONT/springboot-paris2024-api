package bts.sio.api.service;

import bts.sio.api.model.Epreuve;
import bts.sio.api.repository.EpreuveRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Data
@Service
public class EpreuveService {
    @Autowired
    private EpreuveRepository epreuveRepository;

    public Optional<Epreuve> getEpreuve(final Long id) {
        return epreuveRepository.findById(id);
    }

    public Iterable<Epreuve> getEpreuves() {
        return epreuveRepository.findAll();
    }

    public void deleteEpreuve(final Long id) {
        epreuveRepository.deleteById(id);
    }

    public Epreuve saveEpreuve(Epreuve epreuve) {
        Epreuve savedEpreuve = epreuveRepository.save(epreuve);
        return savedEpreuve;
    }
}

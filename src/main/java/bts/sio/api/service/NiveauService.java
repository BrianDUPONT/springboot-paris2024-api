package bts.sio.api.service;

import bts.sio.api.model.Niveau;
import bts.sio.api.repository.NiveauRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class NiveauService {
    @Autowired
    private NiveauRepository niveauRepository;

    public Optional<Niveau> getNiveau(final Long id) {
        return niveauRepository.findById(id);
    }

    public Iterable<Niveau> getNiveaux() {
        return niveauRepository.findAll();
    }

    public void deleteNiveau(final Long id) {
        niveauRepository.deleteById(id);
    }

    public Niveau saveNiveau(Niveau niveau) {
        Niveau savedNiveau = niveauRepository.save(niveau);
        return savedNiveau;
    }
}

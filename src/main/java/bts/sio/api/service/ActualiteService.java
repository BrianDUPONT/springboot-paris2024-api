package bts.sio.api.service;

import bts.sio.api.model.Actualite;
import bts.sio.api.repository.ActualiteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Data
@Service
public class ActualiteService {
    @Autowired
    private ActualiteRepository actualiteRepository;

    public Optional<Actualite> getActualite(final Long id) {
        return actualiteRepository.findById(id);
    }

    public Iterable<Actualite> getActualites() {
        return actualiteRepository.findAll();
    }

    public void deleteActualite(final Long id) {
        actualiteRepository.deleteById(id);
    }

    public Actualite saveActualite(Actualite actualite) {
        Actualite savedActualite = actualiteRepository.save(actualite);
        return savedActualite;
    }
}

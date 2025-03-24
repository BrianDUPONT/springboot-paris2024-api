package bts.sio.api.service;

import bts.sio.api.model.Joueur;
import bts.sio.api.repository.JoueurRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class JoueurService {
    @Autowired
    private JoueurRepository athleteRepository;
    @Autowired
    private JoueurRepository joueurRepository;

    public Optional<Joueur> getJoueur(final Long id) {
        return joueurRepository.findById(id);
    }

    public Iterable<Joueur> getJoueurs() {
        return joueurRepository.findAll();
    }

    public void deleteJoueur(final Long id) {
        joueurRepository.deleteById(id);
    }

    public Joueur saveJoueur(Joueur joueur) {
        Joueur savedJoueur = joueurRepository.save(joueur);
        return savedJoueur;
    }
}

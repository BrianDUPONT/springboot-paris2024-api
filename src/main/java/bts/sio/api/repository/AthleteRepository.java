package bts.sio.api.repository;

import bts.sio.api.model.Athlete;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends CrudRepository<Athlete, Long> {
    // Méthode pour récupérer les athlètes par pays
    List<Athlete> findByPaysId(Long paysId);
}
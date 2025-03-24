package bts.sio.api.repository;

import bts.sio.api.model.Joueur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends CrudRepository<Joueur, Long> {

}

package bts.sio.api.repository;

import bts.sio.api.model.Niveau;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauRepository extends CrudRepository<Niveau, Long> {

}

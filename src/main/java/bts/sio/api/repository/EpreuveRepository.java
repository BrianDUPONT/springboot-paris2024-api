package bts.sio.api.repository;

import bts.sio.api.model.Epreuve;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpreuveRepository extends CrudRepository<Epreuve, Long> {
}

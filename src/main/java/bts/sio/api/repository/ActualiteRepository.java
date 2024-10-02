package bts.sio.api.repository;

import bts.sio.api.model.Actualite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualiteRepository extends CrudRepository<Actualite, Long> {

}
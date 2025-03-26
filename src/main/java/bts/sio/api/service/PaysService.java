package bts.sio.api.service;

import bts.sio.api.model.Pays;
import bts.sio.api.model.Pays;
import bts.sio.api.repository.PaysRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public Optional<Pays> getPays(final Long id) {
        return paysRepository.findById(id);
    }

    public Iterable<Pays> getLesPays() {
        return paysRepository.findAll();
    }

    public Pays savePays(Pays pays) {
            if (pays.getId() == 0) {
                pays.setId(null);
            }
        return paysRepository.save(pays);
    }
}

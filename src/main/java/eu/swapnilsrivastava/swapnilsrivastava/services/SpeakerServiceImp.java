package eu.swapnilsrivastava.swapnilsrivastava.services;

import java.util.List;

import eu.swapnilsrivastava.swapnilsrivastava.model.Speaker;
import eu.swapnilsrivastava.swapnilsrivastava.repository.HibernateSpeakerRepositoryImpl;
import eu.swapnilsrivastava.swapnilsrivastava.repository.SpeakerRepository;

public class SpeakerServiceImp implements SpeakerService {
    
    private SpeakerRepository repository = new HibernateSpeakerRepositoryImpl();

    @Override
    public List<Speaker> findAll() {
        return repository.findAll();
    }
}

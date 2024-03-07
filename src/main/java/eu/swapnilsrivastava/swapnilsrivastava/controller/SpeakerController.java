package eu.swapnilsrivastava.swapnilsrivastava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import eu.swapnilsrivastava.swapnilsrivastava.model.Speaker;
import eu.swapnilsrivastava.swapnilsrivastava.repository.SpeakerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/speakers")
public class SpeakerController {
    
    @Autowired
    private SpeakerRepository speakerRepository; 

    @GetMapping
    public List<Speaker> getSpeakers() {
        return speakerRepository.findAll(); 
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker getSpeaker(@PathVariable Long id) {
        return speakerRepository.getOne(id); 
    }

    @PostMapping()
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }
    
    @DeleteMapping("{id}")
    public void delete(@RequestParam Long id) {
        // need to check the childern record for the speaker
        speakerRepository.deleteById(id);
    }
    
}

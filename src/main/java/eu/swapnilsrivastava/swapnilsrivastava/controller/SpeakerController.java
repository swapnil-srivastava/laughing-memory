package eu.swapnilsrivastava.swapnilsrivastava.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import eu.swapnilsrivastava.swapnilsrivastava.model.Speaker;
import eu.swapnilsrivastava.swapnilsrivastava.repository.SpeakerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



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

    @PutMapping("{id}")
    public Speaker updatSpeaker(@PathVariable Long id, @RequestBody Speaker speaker) {
        // In case of put we are replacing all the values of speaker object but if it was a PATCH we only would need to all the key which is nesscary. 
        // TODO: Adding the validation for each key and return 400 if the value is wrong 
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id"); // in the third parameter we are ignoring the property for copying
        return speakerRepository.saveAndFlush(speaker);
    }
    
}

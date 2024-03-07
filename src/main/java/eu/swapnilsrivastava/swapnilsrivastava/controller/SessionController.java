package eu.swapnilsrivastava.swapnilsrivastava.controller;

import org.springframework.web.bind.annotation.RestController;

import eu.swapnilsrivastava.swapnilsrivastava.model.Session;
import eu.swapnilsrivastava.swapnilsrivastava.repository.SessionRepository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session requestMethodName(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping("{id}")
    public void delete(@RequestParam Long id) {
        // need to check the childern record for the session
        sessionRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Session updateSpeaker(@PathVariable Long id, @RequestBody Session session) {
        // as this is a put , we expect all the keys/attributes to be passed in. a Patch would only need what attribute which is changes or needs to be changed. 
        // TODO: add the validation for all the attributes which are passed in
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }



}

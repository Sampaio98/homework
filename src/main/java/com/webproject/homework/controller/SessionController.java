package com.webproject.homework.controller;

import com.webproject.homework.model.Session;
import com.webproject.homework.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/session")
public class SessionController {

    @Autowired
    private SessionService service;

    @PostMapping
    public ResponseEntity<Session> insert(@RequestBody Session session) {
        Session sessionSaved = service.insert(session);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(sessionSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(sessionSaved);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Session> update(@PathVariable("id") Long id, @RequestBody Session session) {
        Session sessionUpdated = service.update(id, session);
        return ResponseEntity.ok().body(sessionUpdated);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Session> findById(@PathVariable("id") Long id) {
        Session session = service.findById(id);
        return ResponseEntity.ok().body(session);
    }

    @GetMapping
    public ResponseEntity<List<Session>> findAll(){
        List<Session> sessions = service.findAll();
        return ResponseEntity.ok().body(sessions);
    }
}

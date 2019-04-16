package com.webproject.homework.controller;

import com.webproject.homework.service.SnackService;
import com.webproject.homework.model.Snack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/snack")
public class SnackController {

    @Autowired
    private SnackService service;

    @PostMapping
    public ResponseEntity<Snack> insert(@RequestBody Snack snack) {
        Snack snackSaved = service.insert(snack);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(snack.getId()).toUri();
        return ResponseEntity.created(uri).body(snackSaved);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Snack> update(@PathVariable("id") Long id, @RequestBody Snack snack) {
        Snack snackUpdated = service.update(id, snack);
        return ResponseEntity.ok().body(snackUpdated);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Snack> findById(@PathVariable("id") Long id) {
        Snack snack = service.findById(id);
        return ResponseEntity.ok().body(snack);
    }

    @GetMapping
    public ResponseEntity<List<Snack>> findAll(){
        List<Snack> snacks = service.findAll();
        return ResponseEntity.ok().body(snacks);
    }
}

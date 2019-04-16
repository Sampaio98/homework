package com.webproject.homework.controller;

import com.webproject.homework.model.Ingredient;
import com.webproject.homework.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService service;

    @PostMapping
    public ResponseEntity<Ingredient> insert(@RequestBody Ingredient ingredient) {
        Ingredient ingredientSaved = service.insert(ingredient);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(ingredient.getId()).toUri();
        return ResponseEntity.created(uri).body(ingredientSaved);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable("id") Long id, @RequestBody Ingredient ingredient) {
        Ingredient ingredientUpdated = service.update(id, ingredient);
        return ResponseEntity.ok().body(ingredientUpdated);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable("id") Long id) {
        Ingredient ingredient = service.findById(id);
        return ResponseEntity.ok().body(ingredient);
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> findAll(){
        List<Ingredient> ingredients = service.findAll();
        return ResponseEntity.ok().body(ingredients);
    }
}

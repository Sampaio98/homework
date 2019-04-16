package com.webproject.homework.service;

import com.webproject.homework.model.Ingredient;
import com.webproject.homework.repository.IngredientRepository;
import com.webproject.homework.service.exception.DataIntegrityValidationException;
import com.webproject.homework.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public Ingredient insert(Ingredient ingredient) {
        validateIngredient(ingredient);
        return repository.save(ingredient);
    }

    public Ingredient update(Long id, Ingredient ingredient) {
        Ingredient ingredientFromDb = findById(id);
        ingredientFromDb.update(ingredient);
        return repository.save(ingredientFromDb);
    }

    public Ingredient findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Ingrediente não encontrado.".toUpperCase()));
    }

    public List<Ingredient> findAll() {
        return repository.findAll();
    }

    private void validateIngredient(Ingredient ingredient) {
        Ingredient ingredientFound = repository.findByName(ingredient.getName());
        if (ingredientFound != null) {
            throw new DataIntegrityValidationException(ingredient.getName() + " já está cadastrado, tente outro.".toUpperCase());
        }
    }
}

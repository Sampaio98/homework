package com.webproject.homework.service;

import com.webproject.homework.model.Snack;
import com.webproject.homework.repository.SnackRepository;
import com.webproject.homework.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SnackService {

    @Autowired
    private SnackRepository repository;

    @Autowired
    private IngredientService ingredientService;

    public Snack insert(Snack snack) {
        snack.validateFields();
        return repository.save(snack);
    }

    public Snack update(Long id, Snack snack) {
        Snack snackFromDb = findById(id);
        snackFromDb.update(snack);
        return repository.save(snackFromDb);
    }

    public Snack findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Lanche não encontrado.".toUpperCase()));
    }

    public List<Snack> findAll() {
        return repository.findAll();
    }
}

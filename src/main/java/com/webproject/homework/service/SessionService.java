package com.webproject.homework.service;

import com.webproject.homework.model.Session;
import com.webproject.homework.repository.SessionRepository;
import com.webproject.homework.service.exception.DataIntegrityValidationException;
import com.webproject.homework.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository repository;

    public Session insert(Session session) {
        validateSession(session);
        return repository.save(session);
    }

    public Session update(Long id, Session session) {
        Session sessionFromDb = findById(id);
        sessionFromDb.update(session);
        return repository.save(sessionFromDb);
    }

    public Session findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Seção não encontrada.".toUpperCase()));
    }

    public List<Session> findAll() {
        return repository.findAll();
    }

    private void validateSession(Session session) {
        session.validateFields();
        Session sessionFromDb = repository.findByName(session.getName());
        if (sessionFromDb != null) {
            throw new DataIntegrityValidationException(
                    new StringBuilder()
                    .append(session.getName())
                    .append(" já está cadastrado.").toString().toUpperCase());
        }
    }
}

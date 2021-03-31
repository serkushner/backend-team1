package com.exadel.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public abstract class GenericRepositoryServiceImpl<T, U> implements GenericRepositoryService<T, U> {

    @Autowired
    private GenericRepository<T, U> genericRepository;

    @Override
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    @Override
    public void save(T entity) {
        genericRepository.save(entity);
    }

    @Override
    public void delete(T entity) {
        genericRepository.delete(entity);
    }

    @Override
    public void update(T entity) {
        genericRepository.save(entity);
    }

    @Override
    public void deleteById(U id) {
        genericRepository.deleteById(id);
    }

    @Override
    public void findById(U id) {
        genericRepository.findById(id);
    }

    @Override
    public boolean existById(U id) {
        return genericRepository.existsById(id);
    }

}

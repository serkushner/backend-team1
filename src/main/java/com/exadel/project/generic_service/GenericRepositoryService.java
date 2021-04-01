package com.exadel.project.generic_service;

import java.util.List;

public interface GenericRepositoryService<T, U>  {

    List<T> findAll();

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    void deleteById(U id);

    void findById(U id);

    boolean existById(U id);

}

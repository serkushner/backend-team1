package com.exadel.project.common.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.repository.rsql.RsqlSpecification;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Getter
public abstract class BaseService<T, R extends JpaRepository<T, Long> & JpaSpecificationExecutor<T>>{
    @Autowired
    private R repository;

    public List<T> getAllEntities(String search){
        if (search == null){
            return repository.findAll();
        }
        return repository.findAll(RsqlSpecification.<T>rsql(search));
    }

    public T getEntityById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}

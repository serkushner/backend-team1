package com.exadel.project.common.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.repository.rsql.RsqlSpecification;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Getter
public abstract class BaseService<T, R extends JpaRepository<T, Long> & JpaSpecificationExecutor<T>>{
    @Autowired
    private R repository;

    public List<T> getAllEntities(String search, Sort sort){
        if (search == null){
            return repository.findAll(sort);
        }
        return repository.findAll(getRsqlSpecification().rsql(search), sort);
    }

    public T getEntityById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public abstract RsqlSpecification getRsqlSpecification();
}

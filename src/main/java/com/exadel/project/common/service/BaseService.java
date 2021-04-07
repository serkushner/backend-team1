package com.exadel.project.common.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.repository.rsql.RsqlSpecification;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public abstract class BaseService<T, R extends JpaRepository<T, Long> & JpaSpecificationExecutor<T>>{
    protected String defaultSortingField = "id";
    protected String defaultSortingDirection = "asc";
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

    public Sort getSort(String sortFields){
        return sortFields == null ? Sort.by(getDirection(defaultSortingDirection), defaultSortingField)
                : Sort.by(getOrders(sortFields));
    }

    public Sort.Direction getDirection(String direction){
        return Sort.Direction.valueOf(direction.toUpperCase());
    }

    public List<Sort.Order> getOrders(String sortFields){
        return Stream.of(sortFields)
                .flatMap(o->Stream.of(o.split(","))
                        .map(a->a.split(":"))
                        .map(b->new Sort.Order(b.length > 1 ? getDirection(b[1]) : Sort.DEFAULT_DIRECTION, b[0])))
                .collect(Collectors.toList());
    }
}

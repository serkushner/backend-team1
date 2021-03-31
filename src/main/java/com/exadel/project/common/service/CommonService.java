package com.exadel.project.common.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.repository.rsql.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Getter
public abstract class CommonService <T, R extends JpaRepository<T, Long> & JpaSpecificationExecutor<T>>{
    @Autowired
    R repository;

    public List<T> getAllEntities(String search){
        if (search == null){
            return repository.findAll();
        }
        Node rootNode = new RSQLParser().parse(search);
        Specification<T> spec = rootNode.accept(new CustomRsqlVisitor<T>());
        return repository.findAll(spec);
    }

    public T getEntityById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}

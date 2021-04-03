package com.exadel.project.common.repository.rsql;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class RsqlSpecification {

    public static <T> Specification<T> rsql(final String rsqlQuery) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Node rsql = null;
                try{
                    rsql = new RSQLParser().parse(rsqlQuery);
                }catch (RSQLParserException e){
                    return null;
                }
                JpaRsqlConverter<T> jpaRsqlConverter = new JpaRsqlConverter<>(cb);
                return rsql.accept(jpaRsqlConverter, root);
            }
        };
    }
}
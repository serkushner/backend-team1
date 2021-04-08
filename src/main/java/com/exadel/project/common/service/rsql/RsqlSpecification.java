package com.exadel.project.common.service.rsql;

import com.exadel.project.common.service.rsql.JpaRsqlConverter;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class RsqlSpecification {

    public <T> Specification<T> rsql(final String rsqlQuery) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Node rsql = null;
                try{
                    rsql = new RSQLParser().parse(rsqlQuery);
                }catch (RSQLParserException e){
                    return null;
                }
                JpaRsqlConverter jpaRsqlConverter = getJpaRsqlConverter(cb);
                return rsql.accept(jpaRsqlConverter, root);
            }
        };
    }

    public abstract  JpaRsqlConverter getJpaRsqlConverter(CriteriaBuilder cb);
}
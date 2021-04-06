package com.exadel.project.common.repository.rsql;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.InternshipType;
import com.exadel.project.internship.entity.Subject;
import cz.jirutka.rsql.parser.ast.*;
import lombok.Getter;

import javax.persistence.criteria.*;
import java.util.List;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.*;
@Getter
public abstract class JpaRsqlConverter implements RSQLVisitor<Predicate, Root> {

    private final CriteriaBuilder builder;

    public JpaRsqlConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public Predicate visit(AndNode node, Root root) {
        return builder.and(processNodes(node.getChildren(), root));
    }

    public Predicate visit(OrNode node, Root root) {
        return builder.or(processNodes(node.getChildren(), root));
    }

    public abstract Predicate visit(ComparisonNode node, Root root);

    private Predicate[] processNodes(List<Node> nodes, Root root) {
        Predicate[] predicates = new Predicate[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            predicates[i] = nodes.get(i).accept(this, root);
        }
        return predicates;
    }

    public abstract Path visitToJoin(ComparisonNode node, Root root);
}
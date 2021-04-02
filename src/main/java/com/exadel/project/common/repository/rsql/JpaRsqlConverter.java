package com.exadel.project.common.repository.rsql;

import cz.jirutka.rsql.parser.ast.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.*;

public class JpaRsqlConverter implements RSQLVisitor<Predicate, Root> {

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

    public Predicate visit(ComparisonNode node, Root root) {
        ComparisonOperator operator = node.getOperator();
        Path attrPath = root.get(node.getSelector());
        Object argument = node.getArguments().get(0);
        if (operator.equals(EQUAL)) {
            return builder.equal(attrPath, argument);
        }
        if (operator.equals(IN)){
            List<String> arguments = node.getArguments();
            Predicate[] predicates = new Predicate[arguments.size()];
            for (int i = 0; i < arguments.size(); i++) {
                predicates[i] = builder.equal(attrPath, arguments.get(i));
            }
            return builder.or(predicates);
        }
        return null;
    }

    private Predicate[] processNodes(List<Node> nodes, Root root) {
        Predicate[] predicates = new Predicate[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            predicates[i] = nodes.get(i).accept(this, root);
        }
        return predicates;
    }
}
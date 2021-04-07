package com.exadel.project.common.service.rsql;

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

    public Predicate visit(ComparisonNode node, Root root){
        String selector = node.getSelector();
        Path attrPath = selector.contains(".") ? visitToJoin(node, root) : root.get(node.getSelector());
        List<String> arguments = node.getArguments();
        ComparisonOperator operator = node.getOperator();
        if (operator.equals(EQUAL)) {
            return getBuilder().equal(attrPath, checkAndConvertToEnum(selector, arguments.get(0)));
        }
        if (operator.equals(IN)){
            Predicate[] predicates = new Predicate[arguments.size()];
            for (int i = 0; i < arguments.size(); i++) {
                predicates[i] = getBuilder().equal(attrPath, checkAndConvertToEnum(selector, arguments.get(i)));
            }
            return getBuilder().or(predicates);
        }
        return null;
    }

    public abstract Object checkAndConvertToEnum(String selector, String argument);

    private Predicate[] processNodes(List<Node> nodes, Root root) {
        Predicate[] predicates = new Predicate[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            predicates[i] = nodes.get(i).accept(this, root);
        }
        return predicates;
    }

    public abstract Path visitToJoin(ComparisonNode node, Root root);
}
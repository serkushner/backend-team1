package com.exadel.project.common.repository.rsql;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.InternshipType;
import com.exadel.project.internship.entity.Subject;
import cz.jirutka.rsql.parser.ast.*;
import javax.persistence.criteria.*;
import java.util.List;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.*;

public class JpaRsqlConverter<T> implements RSQLVisitor<Predicate, Root> {

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
        try{
            String selector = node.getSelector();
            Path attrPath = selector.contains(".") ? visitToJoin(node, root) : root.get(node.getSelector());
            List<String> arguments = node.getArguments();
            ComparisonOperator operator = node.getOperator();
            if (operator.equals(EQUAL)) {
                return builder.equal(attrPath, arguments.get(0));
            }
            if (operator.equals(IN)){
                Predicate[] predicates = new Predicate[arguments.size()];
                for (int i = 0; i < arguments.size(); i++) {
                    predicates[i] = builder.equal(attrPath, arguments.get(i));
                }
                return builder.or(predicates);
            }
        }catch (IllegalArgumentException e){
            return null;
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

    private Path visitToJoin(ComparisonNode node, Root root) {
        String selector = node.getSelector();
        String[] selectors = selector.split("\\.");
        switch (selectors[0]){
            case "subjects":
                Join<T, Subject> subjectJoin = root.join(selectors[0]);
                return subjectJoin.get(selectors[1]);
            case "countries":
                Join<T, Country> countryJoin = root.join(selectors[0]);
                return countryJoin.get(selectors[1]);
            case "internshipType":
                Join<T, InternshipType> internshipTypeJoin = root.join(selectors[0]);
                return internshipTypeJoin.get(selectors[1]);
        }
        return null;
    }
}
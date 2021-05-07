package com.exadel.project.interviewer.service.rsql;

import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.common.service.rsql.JpaRsqlConverter;
import com.exadel.project.country.entity.Country;
import com.exadel.project.internship.entity.Format;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.Published;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerType;
import com.exadel.project.subject.entity.Subject;
import cz.jirutka.rsql.parser.ast.ComparisonNode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class InterviewerJpaRsqlConverter extends JpaRsqlConverter {

    public InterviewerJpaRsqlConverter(CriteriaBuilder builder) {
        super(builder);
    }

    @Override
    public Path visitToJoin(ComparisonNode node, Root root) {
        String selector = node.getSelector();
        String[] selectors = selector.split("\\.");
        switch (selectors[0]){
            case "subjects":
                Join<Interviewer, Subject> subjectJoin = root.join(selectors[0]);
                return subjectJoin.get(selectors[1]);
        }
        throw new IllegalArgumentException(selectors[0] + " not found");
    }

    @Override
    public Object checkAndConvertToEnum(String selector, String argument) {
        switch (selector){
            case "type": return InterviewerType.valueOf(argument.toUpperCase());
            default: return argument;
        }
    }
}

package com.exadel.project.trainee.service.rsql;

import com.exadel.project.common.service.rsql.JpaRsqlConverter;
import com.exadel.project.internship.entity.*;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.entity.TraineeStatus;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class AdditionalInfoJpaRsqlConverter extends JpaRsqlConverter {

    public AdditionalInfoJpaRsqlConverter(CriteriaBuilder builder) {
        super(builder);
    }

    @Override
    public Object checkAndConvertToEnum(String selector, String argument) {
        switch (selector){
            case "traineeStatus": return TraineeStatus.valueOf(argument.toUpperCase());
            default: return argument;
        }
    }

    @Override
    public Path visitToJoin(ComparisonNode node, Root root) {
        String selector = node.getSelector();
        String[] selectors = selector.split("\\.");
        switch (selectors[0]){
            case "internship":
                Join<AdditionalInfo, Internship> internshipJoin = root.join(selectors[0]);
                return internshipJoin.get(selectors[1]);
            case "trainee":
                Join<AdditionalInfo, Trainee> traineeJoin = root.join(selectors[0]);
                return traineeJoin.get(selectors[1]);
        }
        throw new IllegalArgumentException(selectors[0] + " not found");
    }

    @Override
    public Comparable getArgument(ComparisonNode node, String str) {
        if (node.getSelector().toLowerCase().contains("date")){
            return LocalDate.parse(str);
        }else {
            return str;
        }
    }
}
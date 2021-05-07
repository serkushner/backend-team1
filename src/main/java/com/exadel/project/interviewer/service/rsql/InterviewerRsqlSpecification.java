package com.exadel.project.interviewer.service.rsql;

import com.exadel.project.common.service.rsql.JpaRsqlConverter;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class InterviewerRsqlSpecification extends RsqlSpecification {

    @Override
    public JpaRsqlConverter getJpaRsqlConverter(CriteriaBuilder cb) {
        return new InterviewerJpaRsqlConverter(cb);
    }
}

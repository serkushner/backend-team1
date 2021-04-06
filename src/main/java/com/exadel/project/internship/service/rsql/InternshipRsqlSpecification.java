package com.exadel.project.internship.service.rsql;

import com.exadel.project.common.repository.rsql.JpaRsqlConverter;
import com.exadel.project.common.repository.rsql.RsqlSpecification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;

@Service
public class InternshipRsqlSpecification extends RsqlSpecification {

    @Override
    public JpaRsqlConverter getJpaRsqlConverter(CriteriaBuilder cb) {
        return new InternshipJpaRsqlConverter(cb);
    }
}

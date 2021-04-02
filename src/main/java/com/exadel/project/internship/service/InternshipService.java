package com.exadel.project.internship.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.repository.rsql.RsqlSpecification;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.repository.InternshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class InternshipService extends BaseService<Internship, InternshipRepository> {

    public List<Internship> getAllInternshipsStartAfterToday(String search) {
        if (search == null){
            return getRepository().findAllByStartDateIsAfter(LocalDate.now(), JpaSort.by("startDate"));
        }
        Specification<Internship> specification = RsqlSpecification.<Internship>rsql(search);
        specification = specification.and(new Specification<Internship>() {
            @Override
            public Predicate toPredicate(Root<Internship> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("startDate"), LocalDate.now());
            }
        });
        getRepository().findAll(specification, JpaSort.by("startDate"));
        return getRepository().findAll(specification);
    }

    public Internship getInternshipStartAfterTodayById(Long id) throws EntityNotFoundException {
        return getRepository().findByIdAndStartDateAfter(id, LocalDate.now()).orElseThrow(EntityNotFoundException::new);
    }
}
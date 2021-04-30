package com.exadel.project.internship.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.internship.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService extends BaseService<Subject, SubjectRepository> {

    private final SubjectRepository subjectRepository;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public Subject findSubjectByName(String name){
        return subjectRepository.findByName(name);
    }

    public List<String> getSubjectsNames(){
        return findBySpecifications(null, getSort(defaultSortingField)).stream().map(Subject::getName).collect(Collectors.toList());
    }
}

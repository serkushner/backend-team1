package com.exadel.project.subject.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.subject.entity.Subject;
import com.exadel.project.subject.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubjectService extends BaseService<Subject, SubjectRepository> {

    private final SubjectRepository subjectRepository;
    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public Subject getByName(String name){
        return subjectRepository.findSubjectByName(name);
    }
}
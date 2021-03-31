package com.exadel.project.internship.service;

import com.exadel.project.common.service.CommonService;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.repository.InternshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InternshipService extends CommonService<Internship, InternshipRepository> {
}
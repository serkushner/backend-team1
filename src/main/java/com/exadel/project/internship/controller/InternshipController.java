package com.exadel.project.internship.controller;


import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipInfoDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InternshipController {

    @Autowired
    private InternshipService internshipService;

    @GetMapping("internship")
    public List<InternshipDTO> findAllInternships(@RequestParam(value = "search", required = false) String search){
        return internshipService.getAllDTOs(search);
    }
    //TODO write Exception handler [BE-39]
    @GetMapping("internship/{id}")
    public InternshipInfoDTO findInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return internshipService.getDTOById(id);
    }
}

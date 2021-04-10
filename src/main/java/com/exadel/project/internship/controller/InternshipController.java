package com.exadel.project.internship.controller;


import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("internship")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    @GetMapping
    public List<InternshipDTO> findInternships(@RequestParam(value = "search", required = false) String search,
                                               @RequestParam(value = "sort", required = false) String sort){
            return internshipService.getAll(search, sort);
    }

    @GetMapping("/{id}")
    public InternshipDetailsDTO findInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return internshipService.getById(id);
    }
}

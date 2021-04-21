package com.exadel.project.internship.controller;


import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("internship")
@RequiredArgsConstructor
public class PostedInternshipController {

    private final InternshipService internshipService;
    private static final String ID = "/{id}";

    @GetMapping
    public ResponseEntity<List<InternshipDTO>> findPostedInternships(@RequestParam(value = "search",
            required = false) String search, @RequestParam(value = "sort", required = false) String sort){
//        return ResponseEntity.ok(internshipService.getAllPosted(search, sort));
        return ResponseEntity.ok(internshipService.getAllPosted());
    }

    @GetMapping(value = ID)
    public ResponseEntity<InternshipDetailsDTO> findPostedInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(internshipService.getPostedById(id));
    }

    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InternshipDetailsDTO> updatePostedInternship(@PathVariable Long id,
                                                                       @RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.updatePostedInternship(id, dto));
    }
}

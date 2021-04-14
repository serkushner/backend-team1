package com.exadel.project.interviewer.controller;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.interviewer.dto.InterviewerDto;
import com.exadel.project.interviewer.service.InterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("interviewer")
@RequiredArgsConstructor
public class InterviewerController {

    private final InterviewerService interviewerService;

    private static final String ID = "/{id}";

    @GetMapping(value = ID)
    public ResponseEntity<InterviewerDto> getInterviewerById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(interviewerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<InterviewerDto>> findBySpecification(@RequestParam(value = "search", required = false) String search,
                                                                    @RequestParam(value = "sort", required = false) String sort) {
        List<InterviewerDto> dtoList = interviewerService.getAll(search, sort);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewerDto> createInterviewer(@RequestBody InterviewerDto dto) {
        return ResponseEntity.ok(interviewerService.createInterviewer(dto));
    }

    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewerDto> updateInterviewer(@PathVariable Long id, @RequestBody InterviewerDto dto) {
        return ResponseEntity.ok(interviewerService.updateInterviewer(id, dto));
    }

    @DeleteMapping(value = ID + "/delete")
    public ResponseEntity<Void> deleteInterviewer(@PathVariable Long id) {
        interviewerService.deleteInterviewer(id);
        return ResponseEntity.noContent().build();
    }

}

package com.exadel.project.interview.controller;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("interview")
@RequiredArgsConstructor
public class InterviewController {
    private final InterviewService interviewService;

    private static final String ID = "/{id}";

    @GetMapping(value = ID)
    public ResponseEntity<InterviewDTO> getInterviewById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(interviewService.getInterviewById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewDTO> createInterview(@RequestBody InterviewDTO dto) {
        return ResponseEntity.ok(interviewService.addInterview(dto));
    }

    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewDTO> updateInterview(@PathVariable Long id, @RequestBody InterviewDTO dto) {
        return ResponseEntity.ok(interviewService.updateInterviewById(id, dto));
    }

    @DeleteMapping(value = ID + "/delete")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long id) {
        interviewService.deleteInterviewById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.exadel.project.interviewer.controller;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.interview.dto.InterviewTimeListRequestDTO;
import com.exadel.project.interview.dto.InterviewTimeAppointmentDTO;
import com.exadel.project.interview.dto.InterviewTimeRequestDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import com.exadel.project.interviewer.dto.InterviewerRequestDTO;
import com.exadel.project.interviewer.dto.InterviewerResponseDTO;
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

    private static final String ID = "/{interviewerId}";

    @GetMapping(value = ID)
    public ResponseEntity<InterviewerResponseDTO> getInterviewerById(@PathVariable Long interviewerId) throws EntityNotFoundException {
        return ResponseEntity.ok(interviewerService.getById(interviewerId));
    }

    @GetMapping
    public ResponseEntity<List<InterviewerResponseDTO>> findBySpecification(@RequestParam(value = "search", required = false) String search,
                                                                            @RequestParam(value = "sort", required = false) String sort) {
        List<InterviewerResponseDTO> dtoList = interviewerService.getAll(search, sort);
        return ResponseEntity.ok(dtoList);
    }

    @RequestMapping("/available")
    @GetMapping
    public ResponseEntity<List<InterviewTimeAppointmentDTO>> getAllAvailableInterviewers(@RequestParam(value = "search") String search) {
        List<InterviewTimeAppointmentDTO> dtoList = interviewerService.getAllAvailable(search);
        return ResponseEntity.ok(dtoList);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewerResponseDTO> createInterviewer(@RequestBody InterviewerRequestDTO dto) {
        return ResponseEntity.ok(interviewerService.addInterviewer(dto));
    }

    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewerResponseDTO> updateInterviewer(@PathVariable Long interviewerId, @RequestBody InterviewerRequestDTO dto) {
        return ResponseEntity.ok(interviewerService.updateInterviewer(interviewerId, dto));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Void> deleteInterviewer(@PathVariable Long interviewerId) {
        interviewerService.deleteInterviewer(interviewerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(ID + "/time")
    public ResponseEntity<List<InterviewTimeResponseDTO>> addTimeToInterviewer(@PathVariable Long interviewerId, @RequestBody InterviewTimeListRequestDTO interviewTimeListRequestDTO){
        return ResponseEntity.ok(interviewerService.addInterviewTimeToInterviewer(interviewTimeListRequestDTO.getInterviewTimes(), interviewerId));
    }
}
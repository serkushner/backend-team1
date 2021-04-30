package com.exadel.project.interviewer.controller;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.interview.dto.InterviewTimeDTO;
import com.exadel.project.interviewer.dto.InterviewerDTO;
import com.exadel.project.interviewer.service.InterviewerService;
import com.exadel.project.subject.service.SubjectService;
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
    private final SubjectService subjectService;

    private static final String ID = "/{id}";

    @GetMapping(value = ID)
    public ResponseEntity<InterviewerDTO> getInterviewerById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(interviewerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<InterviewerDTO>> findBySpecification(@RequestParam(value = "search", required = false) String search,
                                                                    @RequestParam(value = "sort", required = false) String sort) {
        List<InterviewerDTO> dtoList = interviewerService.getAll(search, sort);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewerDTO> createInterviewer(@RequestBody InterviewerDTO dto) {
        return ResponseEntity.ok(interviewerService.addInterviewer(dto));
    }

    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewerDTO> updateInterviewer(@PathVariable Long id, @RequestBody InterviewerDTO dto) {
        return ResponseEntity.ok(interviewerService.updateInterviewer(id, dto));
    }

    @DeleteMapping(value = ID + "/delete")
    public ResponseEntity<Void> deleteInterviewer(@PathVariable Long id) {
        interviewerService.deleteInterviewer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<String>> getSubjectsToInterviewer(){
        return ResponseEntity.ok(subjectService.getSubjectsNames());
    }

    @PostMapping(ID + "/time")
    public ResponseEntity<List<InterviewTimeDTO>> addTimeToInterviewer(@PathVariable Long id, @RequestBody List<InterviewTimeDTO> interviewTimeDTO){
        return ResponseEntity.ok(interviewerService.addInterviewTimeToInterviewer(interviewTimeDTO, id));
    }

    @DeleteMapping(ID + "/time/delete")
    public ResponseEntity<Void> deleteTimeFromInterviewer(@PathVariable Long id, @RequestBody InterviewTimeDTO interviewTimeDTO){
        interviewerService.deleteInterviewTimeFromInterviewer(interviewTimeDTO, id);
        return ResponseEntity.noContent().build();
    }
}

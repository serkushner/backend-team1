package com.exadel.project.internship.controller;


import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.S3Service;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("internship")
@RequiredArgsConstructor
public class InternshipController {

    private final S3Service s3Service;
    private final InternshipService internshipService;
    private static final String ID = "/{id}";
    private static final String ACCEPTED_INTERNSHIP = "internship";
    private static final String NOT_POSTED_INTERNSHIP = "unposted-internship";

    @GetMapping(value = ACCEPTED_INTERNSHIP)
    public ResponseEntity<List<InternshipDTO>> findInternships(@RequestParam(value = "search",
            required = false) String search, @RequestParam(value = "sort", required = false) String sort){
        return ResponseEntity.ok(internshipService.getAllPosted(search, sort));
    }

    @GetMapping(value = ACCEPTED_INTERNSHIP + ID)
    public ResponseEntity<InternshipDetailsDTO> findInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(internshipService.getPostedById(id));
    }

    @PutMapping(value = ACCEPTED_INTERNSHIP + ID, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InternshipDetailsDTO> updatePostedInternship(@PathVariable Long id,
                                                                  @RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.updatePostedInternship(id, dto));
    }

    @GetMapping(value = NOT_POSTED_INTERNSHIP)
    public ResponseEntity<List<InternshipDTO>> findInternships(@RequestParam(value = "search",
            required = false) String search, @RequestParam(value = "sort", required = false) String sort){
        return ResponseEntity.ok(internshipService.getAllUnposted(search, sort));
    }

    @GetMapping(value = NOT_POSTED_INTERNSHIP + ID)
    public ResponseEntity<InternshipDetailsDTO> findInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(internshipService.getUnpostedById(id));
    }

    @PostMapping(value = NOT_POSTED_INTERNSHIP + "/registration")
    public ResponseEntity<InternshipDetailsDTO> addInternship(@RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.addUnpostedInternship(dto));
    }

    @PostMapping(value = NOT_POSTED_INTERNSHIP + "/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(s3Service.uploadFile(file));
    }

    @DeleteMapping(value = NOT_POSTED_INTERNSHIP + ID + "/delete")
    public ResponseEntity<Void> deleteInternship(@PathVariable Long id) {
        internshipService.deleteUnpostedInternshipById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = NOT_POSTED_INTERNSHIP + ID, consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InternshipDetailsDTO> updateUnpostedInternship(@PathVariable Long id,
                                                                  @RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.updateUnpostedInternship(id, dto));
    }
}

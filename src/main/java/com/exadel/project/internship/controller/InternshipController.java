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
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class InternshipController {

    private final S3Service s3Service;
    private final InternshipService internshipService;
    private static final String ID = "/{id}";
    private static final String ADMINS = "/internship-administration";
    private static final String INTERNS = "/internship";

    @GetMapping(INTERNS)
    public ResponseEntity<List<InternshipDTO>> findPostedInternships(@RequestParam(value = "search",
            required = false) String search, @RequestParam(value = "sort", required = false) String sort) {
        return ResponseEntity.ok(internshipService.getAllPosted(search, sort));
    }

    @GetMapping(value = INTERNS + ID)
    public ResponseEntity<InternshipDetailsDTO> findPostedInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(internshipService.getPostedById(id));
    }

    @PatchMapping(value = ADMINS + ID)
    public ResponseEntity<InternshipDetailsDTO> findAndChangeInternshipPublishedStatusById(
            @PathVariable ("id") Long id, @RequestParam(value = "published", required = true) String published) throws EntityNotFoundException {
        return ResponseEntity.ok(internshipService.changeInternshipPublishedStatusById(id, published));
    }

    @GetMapping(ADMINS)
    public ResponseEntity<List<InternshipDTO>> findInternships(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false) String sort) {
        return ResponseEntity.ok(internshipService.getAll(search, sort));
    }

    @GetMapping(value = ADMINS + ID)
    public ResponseEntity<InternshipDetailsDTO> findInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(internshipService.getById(id));
    }

    @PostMapping(value = ADMINS + "/registration")
    public ResponseEntity<InternshipDetailsDTO> addUnpostedInternship(@RequestBody InternshipDetailsDTO dto) {
        InternshipDetailsDTO dtoCreated = internshipService.addUnpostedInternship(dto);
        return ResponseEntity.created(URI.create(ADMINS + '/' + dtoCreated.getId())).body(dtoCreated);
    }

    @PostMapping(value = ADMINS + "/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(s3Service.uploadFile(file));
    }

    @DeleteMapping(value = ADMINS + ID)
    public ResponseEntity<Void> deleteUnpostedInternship(@PathVariable Long id) {
        internshipService.deleteUnpostedInternshipById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = ADMINS + ID, consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InternshipDetailsDTO> updateUnpostedInternship(@PathVariable Long id,
                                                                  @RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.updateUnpostedInternship(id, dto));
    }
}

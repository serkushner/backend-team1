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
@RequestMapping("unposted-internship")
@RequiredArgsConstructor
public class UnpostedInternshipController {

    private final S3Service s3Service;
    private final InternshipService internshipService;
    private static final String ID = "/{id}";

    @GetMapping
    public ResponseEntity<List<InternshipDTO>> findUnpostedInternships(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false) String sort) {
//        return ResponseEntity.ok(internshipService.getAllUnposted(search, sort));
        return ResponseEntity.ok(internshipService.getAllUnposted());
    }

    @GetMapping(value = ID)
    public ResponseEntity<InternshipDetailsDTO> findUnpostedInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(internshipService.getUnpostedById(id));
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<InternshipDetailsDTO> addUnpostedInternship(@RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.addUnpostedInternship(dto));
    }

    @PostMapping(value = "/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(s3Service.uploadFile(file));
    }

    @DeleteMapping(value = ID + "/delete")
    public ResponseEntity<Void> deleteUnpostedInternship(@PathVariable Long id) {
        internshipService.deleteUnpostedInternshipById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InternshipDetailsDTO> updateUnpostedInternship(@PathVariable Long id,
                                                                  @RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.updateUnpostedInternship(id, dto));
    }
}

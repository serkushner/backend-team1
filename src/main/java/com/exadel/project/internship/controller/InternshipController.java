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
@RequestMapping("internship")
@RequiredArgsConstructor
public class InternshipController {

    private final S3Service s3Service;
    private final InternshipService internshipService;
    private static final String ID = "/{id}";

    @GetMapping
    public ResponseEntity<List<InternshipDTO>> findInternships(@RequestParam(value = "search",
            required = false) String search,
                                               @RequestParam(value = "sort", required = false) String sort){
        return ResponseEntity.ok(internshipService.getAll(search, sort));
    }

    @GetMapping(value = ID)
    public ResponseEntity<InternshipDetailsDTO> findInternshipById(@PathVariable ("id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(internshipService.getById(id));
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<InternshipDetailsDTO> addInternship(@RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.addInternship(dto));
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(s3Service.uploadFile(file));
    }

    @DeleteMapping(value = ID +"/delete")
    public ResponseEntity<Void> deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternshipById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InternshipDetailsDTO> updateInternship(@PathVariable Long id, @RequestBody InternshipDetailsDTO dto) {
        return ResponseEntity.ok(internshipService.updateInternship(id, dto));
    }
}

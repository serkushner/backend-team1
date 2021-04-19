package com.exadel.project.trainee.controller;

import com.exadel.project.common.service.S3Service;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.service.AdditionalInfoService;
import com.exadel.project.trainee.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("internship")
@RequiredArgsConstructor
public class TraineeController {

    private final S3Service s3Service;
    private final TraineeService traineeService;
    private final AdditionalInfoService additionalInfoService;

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadCv(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(s3Service.uploadFile(file));
    }

    @PostMapping("/{id}/registration")
    public ResponseEntity<TraineeDTO> addTrainee(@RequestBody TraineeDTO traineeDTO, @PathVariable Long id){
        return ResponseEntity.ok(traineeService.addTrainee(traineeDTO, id));
    }

    @GetMapping("trainee")
    public ResponseEntity<List<TraineeToAdminDTO>> getActualTrainees(@RequestParam(value = "search", required = false) String search,
                                                                     @RequestParam(value = "sort", required = false) String sort){
        return ResponseEntity.ok(additionalInfoService.findByNotFinishedInternships(search, sort));
    }

    @GetMapping("trainee/{id}")
    public ResponseEntity<TraineeDTO> getTrainee(@PathVariable ("id") Long id){
        return null;
    }

    @GetMapping("trainee/{id}/history")
    public ResponseEntity<TraineeDTO> getTraineeHistory(@PathVariable ("id") Long id){
        return null;
    }

    @DeleteMapping("trainee/{id}/delete")
    public ResponseEntity<Void> deleteTrainee(@PathVariable ("id") Long id){
        return null;
    }
}
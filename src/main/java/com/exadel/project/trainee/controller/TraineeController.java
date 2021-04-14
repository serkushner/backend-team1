package com.exadel.project.trainee.controller;

import com.exadel.project.common.service.S3Service;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("internship")
@RequiredArgsConstructor
public class TraineeController {

    private final S3Service s3Service;
    private final TraineeService traineeService;

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadCv(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(s3Service.uploadFile(file));
    }

    @PostMapping("/{id}/registration")
    public ResponseEntity<TraineeDTO> addTrainee(@RequestBody TraineeDTO traineeDTO, @PathVariable Long id){
        return ResponseEntity.ok(traineeService.addTrainee(traineeDTO, id));
    }

}

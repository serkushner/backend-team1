package com.exadel.project.trainee.controller;

import com.exadel.project.common.service.S3Service;
import com.exadel.project.trainee.dto.*;
import com.exadel.project.trainee.service.AdditionalInfoService;
import com.exadel.project.trainee.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TraineeController {

    private final S3Service s3Service;
    private final TraineeService traineeService;
    private final AdditionalInfoService additionalInfoService;

    @PostMapping("internship/{id}/upload")
    public ResponseEntity<String> uploadCv(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(s3Service.uploadFile(file));
    }

    @PostMapping("internship/{id}/registration")
    public ResponseEntity<TraineeDTO> addTrainee(@RequestBody TraineeDTO traineeDTO, @PathVariable Long id){
        return ResponseEntity.ok(traineeService.addTrainee(traineeDTO, id));
    }

    @GetMapping("trainee")
    public ResponseEntity<List<TraineeToAdminDTO>> getActualTraineesRegistrations(@RequestParam(value = "search", required = false) String search,
                                                                                  @RequestParam(value = "sort", required = false) String sort){
        return ResponseEntity.ok(additionalInfoService.findByNotFinishedInternships(search, sort));
    }

    @GetMapping("trainee/ai/{id}")
    public ResponseEntity<TraineeToAdminDetailsDTO> getTraineeAdditionalInfo(@PathVariable Long id){
        return ResponseEntity.ok(additionalInfoService.getAdditionalInfoById(id));
    }

    @GetMapping("trainee/{id}/history")
    public ResponseEntity<List<TraineeHistoryDTO>> getTraineeHistory(@RequestParam(value = "search", required = false) String search,
                                                                     @RequestParam(value = "sort", required = false) String sort,
                                                                     @PathVariable Long id){
        return ResponseEntity.ok(additionalInfoService.getTraineeHistory(id, search, sort));
    }

    @PutMapping("trainee/{id}")
    public ResponseEntity<TraineeDTO> updateTrainee(@RequestBody TraineeDTO traineeDTO, @PathVariable Long id){
        return ResponseEntity.ok(traineeService.updateTrainee(traineeDTO, id));
    }

    @DeleteMapping("trainee/{id}/delete")
    public ResponseEntity<Void> deleteTrainee(@PathVariable Long id){
        traineeService.deleteTrainee(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("trainee/ai/{id}/delete")
    public ResponseEntity<Void> deleteAdditionalInfo(@PathVariable Long id){
        additionalInfoService.deleteAdditionalInfo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("unsubscribe/{encryptedId}")
    public ResponseEntity<String> unsubscribeTrainee(@PathVariable String encryptedId){
        traineeService.unsubscribeTrainee(encryptedId);
        return ResponseEntity.ok("thanks for being with us");
    }

    @PostMapping("notify")
    public ResponseEntity<NotifyTraineesDTO> notifyTrainees(@RequestBody NotifyTraineesDTO dto){
        return ResponseEntity.ok(additionalInfoService.notifyTrainees(dto));
    }
}
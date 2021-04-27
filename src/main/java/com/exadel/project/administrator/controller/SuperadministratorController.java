package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.ApproveDto;
import com.exadel.project.administrator.service.SuperadministratorService;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SuperadministratorController {

    private final SuperadministratorService superadministratorService;

    private static final String ID = "/{id}";

    @PostMapping(value ="trainee/ai" + ID)
    public ResponseEntity<TraineeToAdminDTO> changeTraineeStatus(@PathVariable Long id, @RequestBody ApproveDto approveDto) {
        return ResponseEntity.ok(superadministratorService.changeTraineeStatus(id, approveDto.getIsApproved()));
    }

}

package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.ApproveDto;
import com.exadel.project.administrator.service.SuperadministratorService;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import com.exadel.project.trainee.service.TraineeStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuperadministratorController {

    private final SuperadministratorService superadministratorService;

    private static final String ID = "/{id}";

    //@Secured("ROLE_SUPERADMIN")
    @PostMapping(value ="trainee/ai" + ID)
    public ResponseEntity<Void> approveTraineeStatus(@PathVariable Long id, @RequestBody ApproveDto approveDto) {
        superadministratorService.approveTraineeStatusBySuperadmin(id, approveDto.getIsApproved());
        return ResponseEntity.noContent().build();
    }

}

package com.exadel.project.trainee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainee_status")
public class TraineeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trainee_status_id")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30, message = "trainee_status should be from 2 to 30 symbols")
    @Column(name = "trainee_status")
    private String traineeStatus;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Trainee> trainees = new ArrayList<>();

    public void setTrainee(List<Trainee> trainees ) {
        if (trainees != null) {
            trainees.forEach(a -> {
                a.setStatus(this);
            });
        }
        this.trainees = trainees;
    }
}

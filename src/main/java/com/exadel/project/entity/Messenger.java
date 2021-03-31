package com.exadel.project.entity;

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
@Table(name = "messenger")
public class Messenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "messenger_id")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30, message = "title should be from 2 to 30 symbols")
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "messenger", cascade = CascadeType.ALL)
    private List<Trainee> trainees = new ArrayList<>();

    public void setTrainee(List<Trainee> trainees) {
        if (trainees != null) {
            trainees.forEach(a -> {
                a.setMessenger(this);
            });
        }
        this.trainees = trainees;
    }

}

package com.exadel.project.mark.entity;

import com.exadel.project.trainee.entity.Trainee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mark_id")
    private Long id;

    @Column(name = "mark")
    private Integer mark;

    @OneToMany(mappedBy = "mark", cascade = CascadeType.ALL)
    private List<Trainee> trainees = new ArrayList<>();

    public void setTrainee(List<Trainee> trainees) {
        if (trainees != null) {
            trainees.forEach(a -> {
                a.setMark(this);
            });
        }
        this.trainees = trainees;
    }
}

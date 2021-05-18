package com.exadel.project.internship.event;

import com.exadel.project.internship.entity.Internship;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class InternshipPublishedEvent extends ApplicationEvent {
    private final Internship internship;

    public InternshipPublishedEvent(Object source, Internship internship) {
        super(source);
        this.internship = internship;
    }
}

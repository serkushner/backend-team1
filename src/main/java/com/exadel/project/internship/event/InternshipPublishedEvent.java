package com.exadel.project.internship.event;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.service.InternshipService;
import com.exadel.project.subject.entity.Subject;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class InternshipPublishedEvent extends ApplicationEvent {
    private final List<Subject> subjects;
    private final Internship internship;

    public InternshipPublishedEvent(Object source, List<Subject> subjects, Internship internship) {
        super(source);
        this.subjects = subjects;
        this.internship = internship;
    }
}

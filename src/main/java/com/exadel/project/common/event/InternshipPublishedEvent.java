package com.exadel.project.common.event;

import com.exadel.project.subject.entity.Subject;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class InternshipPublishedEvent extends ApplicationEvent {
    private final List<Subject> subjects;

    public InternshipPublishedEvent(Object source, List<Subject> subjects) {
        super(source);
        this.subjects = subjects;
    }
}

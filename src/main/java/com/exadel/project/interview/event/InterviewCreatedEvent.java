package com.exadel.project.interview.event;

import com.exadel.project.interview.entity.Interview;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class InterviewCreatedEvent extends ApplicationEvent {
    private final Interview interview;

    public InterviewCreatedEvent(Object source, Interview interview) {
        super(source);
        this.interview = interview;
    }
}

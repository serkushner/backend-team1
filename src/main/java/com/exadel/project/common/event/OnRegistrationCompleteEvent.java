package com.exadel.project.common.event;

import com.exadel.project.subject.entity.Subject;
import com.exadel.project.trainee.entity.AdditionalInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final AdditionalInfo additionalInfo;

    public OnRegistrationCompleteEvent(
            Object source, AdditionalInfo additionalInfo) {
        super(source);
        this.additionalInfo = additionalInfo;
    }
}

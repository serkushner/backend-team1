package com.exadel.project.trainee.event;

import com.exadel.project.trainee.entity.AdditionalInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class TraineeRegistrationCompleteEvent extends ApplicationEvent {

    private final AdditionalInfo additionalInfo;

    public TraineeRegistrationCompleteEvent(Object source, AdditionalInfo additionalInfo) {
        super(source);
        this.additionalInfo = additionalInfo;
    }
}


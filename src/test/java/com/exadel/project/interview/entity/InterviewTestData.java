package com.exadel.project.interview.entity;

import org.springframework.stereotype.Component;

@Component
public class InterviewTestData {
    public Interview getTechInterview() {
        Interview interview = new Interview();
        interview.setId(1L);
        interview.setName("tech interview");
        return interview;
    }

    public Interview getHrInterview() {
        Interview interview = new Interview();
        interview.setId(2L);
        interview.setName("hr interview");
        return interview;
    }
}

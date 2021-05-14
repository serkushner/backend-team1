package com.exadel.project.trainee.entity;

import lombok.Getter;

@Getter
public enum TraineeStatus {
    EMAIL_NOT_CONFIRM("registered", null),
    REGISTERED("recruiter_interview_pending", null),
    RECRUITER_INTERVIEW_PENDING("recruiter_interview_accepted", "recruiter_interview_rejected"),
    RECRUITER_INTERVIEW_REJECTED("recruiter_interview_passed", "rejected"),
    RECRUITER_INTERVIEW_ACCEPTED("recruiter_interview_passed", "rejected"),
    RECRUITER_INTERVIEW_PASSED("technical_interview_pending", null),
    TECHNICAL_INTERVIEW_PENDING("technical_interview_accepted", "technical_interview_rejected"),
    TECHNICAL_INTERVIEW_REJECTED("accepted", "rejected"),
    TECHNICAL_INTERVIEW_ACCEPTED("accepted", "rejected"),
    REJECTED(null, null),
    ACCEPTED(null, null);

    TraineeStatus(String nextPositive, String nextNegative) {
        this.nextPositive = nextPositive;
        this.nextNegative = nextNegative;
    }

    private final String nextPositive;
    private final String nextNegative;

    public static TraineeStatus getNextStatus(TraineeStatus currentStatus, Boolean positive){
        String nextStatus = positive ? currentStatus.nextPositive : currentStatus.nextNegative;
        return TraineeStatus.valueOf(nextStatus.toUpperCase());
    }
}
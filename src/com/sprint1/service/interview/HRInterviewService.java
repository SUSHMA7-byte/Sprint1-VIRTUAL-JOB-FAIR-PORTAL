package com.sprint1.service.interview;

import com.sprint1.model.Interview;

import java.time.LocalDateTime;

public class HRInterviewService extends Interview {
    public HRInterviewService() {
        super(0, null, "", "");
    }

    @Override
    public void conductInterview() {
        System.out.println("Conducting HR interview...");
        System.out.println("Datetime: " + getInterviewDatetime());
        System.out.println("Result: " + getResultStatus());
    }
}
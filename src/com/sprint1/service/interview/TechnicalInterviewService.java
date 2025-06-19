package com.sprint1.service.interview;

import com.sprint1.model.Interview;

public class TechnicalInterviewService extends Interview {
    public TechnicalInterviewService() {
        super(0, null, "", "");
    }

    @Override
    public void conductInterview() {
        System.out.println("Conducting technical interview...");
        System.out.println("Datetime: " + getInterviewDatetime());
        System.out.println("Result: " + getResultStatus());
    }
}

package com.sprint1.model;

import java.time.LocalDateTime;

public class Application {
    private int application_id;
    private String application_status;
    private LocalDateTime application_date;

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }

    public LocalDateTime getApplication_date() {
        return application_date;
    }

    public void setApplication_date(LocalDateTime application_date) {
        this.application_date = application_date;
    }

    public Application(int application_id, String application_status, LocalDateTime application_date) {
        this.application_id = application_id;
        this.application_status = application_status;
        this.application_date = application_date;
    }

    @Override
    public String toString() {
        return "Application{" +
                "application_id=" + application_id +
                ", application_status='" + application_status + '\'' +
                ", application_date=" + application_date +
                '}';
    }
}

package com.sprint1.service.notification;

public class SMSNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}


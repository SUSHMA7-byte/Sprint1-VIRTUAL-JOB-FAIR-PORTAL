package com.sprint1.service.notification;

public class EmailNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending Email to " + recipient + ": " + message);
    }
}

package org.example.notification;

public class BasicMessage implements Message {

    @Override
    public void send(String text) {
        System.out.println("Notificación enviada: " + text);
    }
}

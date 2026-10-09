package org.example.notification;

public class LoggingDecorator implements Message {
    private final Message wrappedMessage;

    public LoggingDecorator(Message wrappedMessage) {
        this.wrappedMessage = wrappedMessage;
    }

    @Override
    public void send(String text) {
        System.out.println("[LOG] Enviando notificación: " + text);
        wrappedMessage.send(text);
    }
}

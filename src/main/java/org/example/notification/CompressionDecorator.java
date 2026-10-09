package org.example.notification;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

public class CompressionDecorator implements Message {
    private final Message wrappedMessage;

    public CompressionDecorator(Message wrappedMessage) {
        this.wrappedMessage = wrappedMessage;
    }

    @Override
    public void send(String text){
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

            try (GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
                gzipStream.write(text.getBytes(StandardCharsets.UTF_8));
            }

            String compressedText = Base64.getEncoder()
                    .encodeToString(byteStream.toByteArray());

            wrappedMessage.send(compressedText);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo comprimir la notificación.", exception);
        }

    }
}

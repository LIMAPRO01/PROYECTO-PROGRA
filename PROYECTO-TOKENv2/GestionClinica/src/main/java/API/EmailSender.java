/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author Mario
 */
public class EmailSender {
    private final String username;
    private final String password;
    private final String smtpHost;
    private final String smtpPort;

    public EmailSender(String username, String password, String smtpHost, String smtpPort) {
        this.username = username;
        this.password = password;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public void sendEmail(String to, String subject, String body, File attachment) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        Multipart multipart = new MimeMultipart();

        // Cuerpo del mensaje
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(body, "utf-8");
        multipart.addBodyPart(textPart);

        // Adjunto (si existe)
        if (attachment != null) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            try {
                attachmentPart.attachFile(attachment);
                multipart.addBodyPart(attachmentPart);
            } catch (IOException e) {
                throw new MessagingException("Error al adjuntar el archivo: " + e.getMessage(), e);
            }
        }

        message.setContent(multipart);

        Transport.send(message);
    }
    
}

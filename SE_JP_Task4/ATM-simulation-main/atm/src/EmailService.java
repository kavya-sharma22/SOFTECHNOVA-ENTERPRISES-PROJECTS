import java.io.File;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.*;

/**
 * EmailService class represents a utility for sending emails using SMTP protocol.
 */
class EmailService extends JDialog {

    // Username and password for the email service
    private String username;
    private String password;

    // Properties for configuring the email session
    private Properties properties;

    // Email session to be reused
    private Session emailSession;

    /**
     * Default constructor with default email credentials.
     */
    public EmailService() {
        this("##Your Email", "##Your email key");
    }

    /**
     * Constructor with custom email credentials.
     *
     * @param username The username for the email service.
     * @param password The password for the email service.
     */
    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;
        configureProperties();
        this.emailSession = getSession();
    }

    /**
     * Configures properties for the email session.
     */
    private void configureProperties() {
        this.properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    /**
     * Private method to get the email session.
     *
     * @return The email session.
     */
    private Session getSession() {
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * Public method to send email with optional attachments and HTML or plain text format.
     *
     * @param recipients  Comma-separated email addresses.
     * @param subject     Email subject.
     * @param body        Email body.
     * @param attachments List of file paths to attachments.
     * @param html        True if the email body is in HTML format, false for plain text.
     */
    public void sendEmail(String recipients, String subject, String body, List<String> attachments, boolean html) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Message message = new MimeMessage(emailSession);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
                    message.setSubject(subject);

                    // Create the message part
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    if (html) {
                        messageBodyPart.setContent(body, "text/html");
                    } else {
                        messageBodyPart.setText(body);
                    }

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);

                    // Add attachments if any
                    if (attachments != null && !attachments.isEmpty()) {
                        for (String filePath : attachments) {
                            MimeBodyPart attachmentPart = new MimeBodyPart();
                            DataSource source = new FileDataSource(filePath);
                            attachmentPart.setDataHandler(new DataHandler(source));
                            attachmentPart.setFileName(new File(filePath).getName());
                            multipart.addBodyPart(attachmentPart);
                        }
                    }

                    message.setContent(multipart);

                    // Send the message
                    Transport.send(message);
                    System.out.println("Email sent successfully!");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        worker.execute();
    }
}

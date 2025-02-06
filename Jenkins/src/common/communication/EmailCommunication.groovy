package org.example

class EmailCommunication extends CommunicationHelper {

    String smtpServer
    String recipient
    String sender

    EmailCommunication(String smtpServer, String recipient, String sender) {
        this.smtpServer = smtpServer
        this.recipient = recipient
        this.sender = sender
    }

    @Override
    void sendMessage(String status, String message) {
        if (!smtpServer || !recipient || !sender) {
            error "SMTP details not configured!"
        }

        // Basic email content setup
        def emailSubject = "Build Status: ${status}"
        def emailBody = "${status}: ${message}"

        // Example using `mail` step to send an email
        mail(
            to: recipient,
            subject: emailSubject,
            body: emailBody,
            from: sender,
            smtpHost: smtpServer
        )

        echo "Email notification sent successfully."
    }
}

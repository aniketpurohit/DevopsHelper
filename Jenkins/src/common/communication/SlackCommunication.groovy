package org.example

class SlackCommunication extends CommunicationHelper {

    String slackWebhookUrl

    SlackCommunication(String slackWebhookUrl) {
        this.slackWebhookUrl = slackWebhookUrl
    }

    @Override
    void sendMessage(String status, String message) {
        if (!slackWebhookUrl) {
            error "Slack webhook URL is not configured!"
        }

        def payload = [
            channel: "#build-status",
            username: "jenkins",
            text: "${status}: ${message}"
        ]

        // Send HTTP POST request to Slack webhook URL
        def response = httpRequest(
            url: slackWebhookUrl,
            httpMode: 'POST',
            contentType: 'APPLICATION_JSON',
            requestBody: groovy.json.JsonOutput.toJson(payload),
            validResponseCodes: '200'
        )

        if (response != null) {
            echo "Slack notification sent successfully."
        } else {
            error "Failed to send Slack notification."
        }
    }
}

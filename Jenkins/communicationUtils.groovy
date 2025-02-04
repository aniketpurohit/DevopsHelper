def notifySlack(String status, String message) {
    echo "Sending notification to Slack..."
    sh """
        curl -X POST --data-urlencode 'payload={
            "channel": "#build-status",
            "username": "jenkins",
            "text": "${status}: ${message}"
        }' https://hooks.slack.com/services/XXXX/YYYY/ZZZZ
    """
}


def sendEmailNotification(String status, String recipientEmail) {
    echo "Sending email notification..."
    emailext(
        subject: "Jenkins Build Status: ${status}",
        body: "Build status: ${status}",
        to: recipientEmail
    )
}

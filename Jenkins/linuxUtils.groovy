def checkDiskUsage() {
    sh 'df -h'
}

def deleteLargeLogs() {
    sh 'find /var/log -type f -size +100M -exec rm -f {} \\;'
}

def restartService(String serviceName) {
    sh "sudo systemctl restart ${serviceName}"
}

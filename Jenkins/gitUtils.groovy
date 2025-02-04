def checkoutBranch(String branchName) {
    echo "Checking out branch: ${branchName}"
    sh "git checkout ${branchName}"
}

def getGitCommitHash() {
    def commitHash = sh(script: "git rev-parse HEAD", returnStdout: true).trim()
    echo "Commit Hash: ${commitHash}"
    return commitHash
}

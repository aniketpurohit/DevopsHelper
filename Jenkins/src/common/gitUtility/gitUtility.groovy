package common.gitUtility

class GitUtility {

    String gitUrl
    String gitBranch
    String gitCommit
    String gitUsername // Optional username for HTTPS authentication
    String gitToken    // Optional token for HTTPS authentication
    String sshPrivateKey // Optional private key for SSH authentication

    GitUtility(String gitUrl, String gitBranch, String gitCommit = null, String gitUsername = null, String gitToken = null, String sshPrivateKey = null) {
        this.gitUrl = gitUrl
        this.gitBranch = gitBranch
        this.gitCommit = gitCommit
        this.gitUsername = gitUsername
        this.gitToken = gitToken
        this.sshPrivateKey = sshPrivateKey
    }

    // Validate Git details
    private void validateGitDetails() {
        if (!gitUrl) {
            error "Git URL is not configured!"
        }
        if (!gitBranch) {
            error "Git Branch is not configured!"
        }
    }

    // Get Git details
    void getGitDetails() {
        validateGitDetails()

        // If no commit is provided, attempt to get the latest commit hash from the repository
        if (!gitCommit) {
            gitCommit = getLatestCommit()
        }

        echo "Git URL: ${gitUrl}"
        echo "Git Branch: ${gitBranch}"
        echo "Git Commit: ${gitCommit}"
    }

    // Clone the Git repository with optional authentication
    void cloneRepository(String targetDir) {
        validateGitDetails()

        // Setup Git authentication (SSH or HTTPS)
        def gitCloneCmd = buildGitCloneCommand(targetDir)

        echo "Cloning repository ${gitUrl} into ${targetDir}..."
        sh gitCloneCmd
    }

    // Fetch the latest commit from the given Git repository and branch
    private String getLatestCommit() {
        echo "Fetching the latest commit for branch: ${gitBranch}..."
        def commitHash = sh(script: "git ls-remote ${gitUrl} ${gitBranch} | awk '{print \$1}'", returnStdout: true).trim()
        if (!commitHash) {
            error "Failed to fetch commit hash for branch ${gitBranch} from ${gitUrl}."
        }
        return commitHash
    }

    // Checkout to a specific branch
    void checkoutBranch() {
        validateGitDetails()

        echo "Checking out branch: ${gitBranch}..."
        sh "git checkout ${gitBranch}"
    }

    // Fetch changes from the repository
    void fetchChanges() {
        validateGitDetails()

        echo "Fetching changes from ${gitUrl}..."
        sh "git fetch ${gitUrl} ${gitBranch}"
    }

    // Get the status of the current repository
    void getGitStatus() {
        validateGitDetails()

        echo "Getting the Git status of the repository..."
        sh "git status"
    }

    // Build the Git command based on authentication method
    private String buildGitCloneCommand(String targetDir) {
        String gitCloneCmd = ""
        
        if (sshPrivateKey) {
            // SSH Authentication
            writeFile file: '/tmp/git_private_key', text: sshPrivateKey
            sh "chmod 600 /tmp/git_private_key"
            gitCloneCmd = "GIT_SSH_COMMAND='ssh -i /tmp/git_private_key' git clone -b ${gitBranch} ${gitUrl} ${targetDir}"
        } else if (gitUsername && gitToken) {
            // HTTPS Authentication (with username and token)
            def httpsUrl = "https://${gitUsername}:${gitToken}@${gitUrl.replaceFirst('https://', '')}"
            gitCloneCmd = "git clone -b ${gitBranch} ${httpsUrl} ${targetDir}"
        } else {
            // No authentication
            gitCloneCmd = "git clone -b ${gitBranch} ${gitUrl} ${targetDir}"
        }

        return gitCloneCmd
    }
}


// alternative way to clone repository
class GitUtilityScm {

    String gitUrl
    String gitBranch
    String gitCommit
    String gitCredentialsId  // Optional: Jenkins credentials ID for Git authentication (SSH or HTTPS)

    GitUtilityScm(String gitUrl, String gitBranch, String gitCommit = null, String gitCredentialsId = null) {
        this.gitUrl = gitUrl
        this.gitBranch = gitBranch
        this.gitCommit = gitCommit
        this.gitCredentialsId = gitCredentialsId
    }

    // Validate Git details
    private void validateGitDetails() {
        if (!gitUrl) {
            error "Git URL is not configured!"
        }
        if (!gitBranch) {
            error "Git Branch is not configured!"
        }
    }

    // Get Git details (including optional commit hash)
    void getGitDetails() {
        validateGitDetails()

        // If no commit is provided, attempt to get the latest commit hash from the repository
        if (!gitCommit) {
            gitCommit = getLatestCommit()
        }

        echo "Git URL: ${gitUrl}"
        echo "Git Branch: ${gitBranch}"
        echo "Git Commit: ${gitCommit}"
    }

    // Clone the Git repository and checkout the specified branch
    void cloneRepository() {
        validateGitDetails()

        echo "Cloning repository ${gitUrl} and checking out branch ${gitBranch}..."

        // Use the 'checkout' step to clone the repository and checkout the branch
        checkout([
            $class: 'GitSCM',
            branches: [[name: "*/${gitBranch}"]],
            userRemoteConfigs: [[
                url: gitUrl,
                credentialsId: gitCredentialsId // Optional: pass credentialsId for authentication
            ]]
        ])
    }

    // Fetch the latest commit from the given Git repository and branch
    private String getLatestCommit() {
        echo "Fetching the latest commit for branch: ${gitBranch}..."

        // Use the 'git' step to fetch commit details
        def commitHash = sh(script: "git ls-remote ${gitUrl} ${gitBranch} | awk '{print \$1}'", returnStdout: true).trim()

        if (!commitHash) {
            error "Failed to fetch commit hash for branch ${gitBranch} from ${gitUrl}."
        }
        return commitHash
    }

    // Checkout to a specific branch using Jenkins Git Plugin
    void checkoutBranch() {
        validateGitDetails()

        echo "Checking out branch: ${gitBranch}..."
        checkout([
            $class: 'GitSCM',
            branches: [[name: "*/${gitBranch}"]],
            userRemoteConfigs: [[
                url: gitUrl,
                credentialsId: gitCredentialsId
            ]]
        ])
    }

    // Fetch changes from the repository using Git plugin
    void fetchChanges() {
        validateGitDetails()

        echo "Fetching changes from ${gitUrl}..."
        sh "git fetch ${gitUrl} ${gitBranch}"
    }

    // Get the status of the current repository
    void getGitStatus() {
        validateGitDetails()

        echo "Getting the Git status of the repository..."
        sh "git status"
    }
}

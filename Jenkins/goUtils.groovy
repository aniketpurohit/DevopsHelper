def installGoDependencies() {
    echo "Installing Go dependencies..."
    sh 'go mod tidy'
    sh 'go mod vendor'
}

def runGoTests() {
    echo "Running Go tests..."
    sh 'go test ./... -v'
}

def buildGoApplication() {
    echo "Building Go application..."
    sh 'go build -o myapp'
}

def lintGoCode() {
    echo "Running Go linting with golangci-lint..."
    sh 'golangci-lint run'
}

def runGoStaticAnalysis() {
    echo "Running Go static analysis with staticcheck..."
    sh 'staticcheck ./...'
}


def buildGoDockerImage(String imageName) {
    echo "Building Go Docker image: ${imageName}"
    sh "docker build -t ${imageName} ."
}


def runGoApplication() {
    echo "Running Go application..."
    sh './myapp'
}


def uploadGoArtifact(String bucketName, String artifactPath) {
    echo "Uploading Go artifact to S3..."
    sh "aws s3 cp ${artifactPath} s3://${bucketName}/"
}


def cleanGoBuildCache() {
    echo "Cleaning Go build cache..."
    sh 'go clean -cache'
}


def generateGoDocs() {
    echo "Generating Go documentation..."
    sh 'godoc -http=:6060'
}

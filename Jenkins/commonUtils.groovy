def setEnvVars(Map envVars) {
    /*
    The function sets the environment variables in the Jenkins pipeline.
    ARGS:
    envVars: A map containing the environment variables to be set.
    */
    echo 'Setting environment variables...'
    envVars.each { key, value ->
        sh "export ${key}=${value}"
    }
}

def loadConfigFile(String configFilePath) {
    /*
    The function reads the configuration file and returns the configuration.
    ARGS:
    configFilePath: The path to the configuration file.
    */
    echo "Loading configuration from ${configFilePath}..."
    def config = readYaml file: configFilePath
    return config
}

def makeDirectory(Map directory) {
    /*
    The function creates a directory in the Jenkins pipeline.
    ARGS:
    directory: A map containing the directory path and its children.
    */
    if (directory.parent) {  // Check if parent is not null or empty
        for (child in directory.children) {
            def temp = "${directory.parent}/${child}"  // Correct variable interpolation
            sh "mkdir -p ${temp}"  // Create the directory
        }
    }
    else if (directory.children) {
        for (child in directory.children) {
            sh "mkdir -p ${child}"
        }
    }
    else {
        error 'Parent directory path is not provided.'
    }
}

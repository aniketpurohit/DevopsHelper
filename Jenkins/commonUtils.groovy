def setEnvVars(Map envVars) {
    echo "Setting environment variables..."
    envVars.each { key, value ->
        sh "export ${key}=${value}"
    }
}

def loadConfigFile(String configFilePath) {
    echo "Loading configuration from ${configFilePath}..."
    def config = readYaml file: configFilePath
    return config
}

def makeDirectory(Map directory) {
    if (directory.parent) {  // Check if parent is not null or empty
        for (child in directory.children) {
            def temp = "${directory.parent}/${child}"  // Correct variable interpolation
            sh "mkdir -p ${temp}"  // Create the directory
        }
    } 
    else if(directory.children){
        for(child in directory.children){
        sh "mkdir -p ${child}"
        }
    }
    else {
        error "Parent directory path is not provided."
    }
}
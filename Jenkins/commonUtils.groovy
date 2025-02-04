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

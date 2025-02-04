def installPythonDependencies() {
    echo "Installing Python dependencies..."
    sh 'pip install -r requirements.txt'
}

def runPythonTests() {
    echo "Running Python tests with pytest..."
    sh 'pytest --maxfail=3 --disable-warnings'
}

def buildPythonWheel() {
    echo "Building Python wheel..."
    sh 'python setup.py bdist_wheel'
}

def lintPythonCode() {
    echo "Running Python linting with pylint..."
    sh 'pylint **/*.py'
}

def createPythonVirtualEnv() {
    echo "Creating Python virtual environment..."
    sh 'python3 -m venv venv'
    sh '. venv/bin/activate'
}

def buildPythonDockerImage(String imageName) {
    echo "Building Python Docker image: ${imageName}"
    sh "docker build -t ${imageName} ."
}


def uploadPythonArtifactToPyPI() {
    echo "Uploading Python package to PyPI..."
    sh 'twine upload dist/*'
}

def cleanPythonCache() {
    echo "Cleaning Python cache..."
    sh 'find . -type d -name "__pycache__" -exec rm -r {} +'
}

def runPythonStaticAnalysis() {
    echo "Running static analysis with flake8..."
    sh 'flake8 .'
}


def publishPythonDocs() {
    echo "Publishing Python documentation..."
    sh 'sphinx-build -b html docs/ docs/_build'
}

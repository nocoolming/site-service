pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                echo "hello world"
                sh 'java --version'
                sh './gradlew build'
            }
        }
    }
}
pipeline {
    agent any
    tools {
        jdk "jdk21"
    }
    stages {
        stage('Test') {
            steps {
                echo "hello world"
                sh 'java --version'
                sh './gradlew build'
                sh 'docker -version'
            }
        }
    }
}
pipeline {
    agent any
    tools {
        jdk "jdk21"
        gradle "8.4"
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
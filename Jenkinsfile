pipeline {
    agent {
        docker { image 'openjdk:21' }
    }
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
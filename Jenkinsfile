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
                sh 'gradle build'
                sh 'docker info'
                dev version = sh '$(date +%Y%m%d%H%M%S%N)'
                echo "${version}"
                docker.withRegistry("", "DockerHubCredentials") {
                           def image = docker.image("nocoolming/site-service");
                           image.push()
                         }
            }
        }
    }
}
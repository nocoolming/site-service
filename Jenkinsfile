pipeline {
    agent any
    tools {
        jdk "jdk21"
        gradle "8.4"
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('DockerHubCredentials')
    }
    stages {
        stage('Login') {
            steps {
                sh 'echo "hello"'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Build') {
            steps {
                sh 'version=$(date +%Y%m%d%H%M%S%N)'
                sh 'imageName=nocoolming/site-service:${version}'
                sh 'echo $imageName'
                sh 'docker build -t ${imageName}'
                sh 'docker push ${imageName}'
            }
        }
    }
}
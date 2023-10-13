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
                sh 'echo "login to docker hub"'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Build') {
            steps {
                sshagent(['ssh-agent']) {
                    // some block
                    sh './publish.sh'
                }
            }
        }
    }
}
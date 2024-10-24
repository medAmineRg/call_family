pipeline {
    agent any

    tools {
        maven 'MAVEN3'
    }

    environment {
        DOCKER_HOST = 'unix:///var/run/docker.sock'
    }

    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build and Start Containers') {
            steps {
                script {
                    // Docker build and run commands
                    sh 'docker compose build --no-cache'
                    sh 'docker compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Something went wrong during the pipeline.'
        }
    }
}

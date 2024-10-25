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

        stage('Run Tests') {
            steps {
                sh 'mvn test'
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

        stage('Push to Docker Registry') {
            environment {
                DOCKER_REGISTRY = 'mohamed99amine'
            }
            steps {
                withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhub')]) {
                    sh 'docker login -u mohamed99amine -p ${dockerhub}'
                }
                sh 'docker tag callfamily_app:latest $DOCKER_REGISTRY/callfamily_app:latest'
                sh 'docker push $DOCKER_REGISTRY/callfamily_app:latest'
            }
        }

    }

}

post {
    always {
        echo 'Pipeline finished.'
        sh 'docker system prune -a --volumes -f'
    }
    success {
        echo 'Build and deployment successful!'
    }
    failure {
        echo 'Something went wrong during the pipeline.'
    }
}

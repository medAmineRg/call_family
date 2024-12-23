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
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                }
                sh 'docker push mohamed99amine/callfamily_app:latest'
            }
        }

        stage('Deploy to Production') {
            steps {
                sh 'scp docker-compose.yml root@91.108.113.159:/tmp'
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
}

pipeline {
    agent any

    tools {
        maven 'maven3'
    }

    stages {
        stage('clone') {
            steps {
                git 'https://github.com/medev/callfamily.git'
            }
        }

        stage('build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('UNIT TEST') {
            steps {
                sh 'mvn test'
            }
        }

        stage('INTEGRATION TEST') {
            steps {
                sh 'mvn verify -DskipUnitTests'
            }
        }
    }

}
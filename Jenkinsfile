pipeline {
    agent any

    tools {
        maven 'MAVEN3'
    }

    stages {
        stage('clone') {
            steps {
                git branch: 'origin', url: 'https://github.com/medAmineRg/call_family.git'
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
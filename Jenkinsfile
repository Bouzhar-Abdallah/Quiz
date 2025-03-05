pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'lambidos_github_personal_token', url: 'https://github.com/Bouzhar-Abdallah/Quiz'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mkdir -p target && cp target/*.jar target/app.jar'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/app.jar', fingerprint: true
            }
        }
    }
}

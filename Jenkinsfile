pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'your-credentials-id', url: 'https://github.com/your-username/your-repo.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
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

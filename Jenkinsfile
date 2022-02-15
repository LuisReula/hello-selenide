pipeline {
    agent any

    stages {
        stage('Test'){
            steps{
                sh './gradlew clean test check'
            }
            post{
                always {
                    junit 'build/test-results/test/*.xml'
                    jacoco execPattern: 'build/jacoco/*.exec'
                }
            }
        }

    }
}
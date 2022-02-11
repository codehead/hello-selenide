pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                sh './gradlew clean test check'
            }
            post {
                // If Gradle was able to run the tests, even if some of the test
                // failed, record the test results
                always {
                    allure([
                        reportBuildPolicy: 'ALWAYS',
                        jdk: '',
                        includeProperties: false,
                        properties: [],
                        results: [[path: 'build/allure-results']],
                    ])
                    junit 'build/test-results/test/*.xml'
                    jacoco execPattern: 'build/jacoco/*.exec'
                    recordIssues(
                        tools: [
                            pmdParser(pattern: 'build/reports/pmd/*.xml')
                        ]
                    )
                }
            }
        }
        stage('Build') {
            steps {
                // Run Gradle Wrapper
                sh "./gradlew assemble"
            }
            post {
                // If Gradle was able to run the tests archive the jar files
                success {
                    archiveArtifacts 'build/libs/*.jar'
                }
            }
        }
        stage('Deploy') {
            steps {
                   echo 'Deploying...'
            }
        }
    }
}

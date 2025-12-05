pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
    }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:${PATH}"
        TEST_ENV = 'dev'
    }

    stages {
        stage('Checkout') {
            steps {
                echo '🔄 Checking out source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '🏗️ Building project...'
                sh './gradlew clean build -x test'
            }
        }

        stage('Test - API') {
            steps {
                echo '🧪 Running API tests...'
                sh './gradlew test -Ptag=api'
            }
        }

        stage('Test - UI') {
            steps {
                echo '🧪 Running UI tests...'
                sh './gradlew test -Ptag=ui'
            }
        }

        stage('Test - All') {
            steps {
                echo '🧪 Running all tests...'
                sh './gradlew test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo '📊 Generating Allure report...'
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        results: [[path: 'build/allure-results']]
                    ])
                }
            }
        }
    }

    post {
        always {
            echo '📝 Collecting test results...'
            junit 'build/test-results/test/**/*.xml'
        }

        success {
            echo '✅ Pipeline SUCCESS!'
            archiveArtifacts artifacts: 'build/allure-results/**', allowEmptyArchive: true
        }

        failure {
            echo '❌ Pipeline FAILED!'
            archiveArtifacts artifacts: 'build/reports/**', allowEmptyArchive: true
        }
    }
}

pipeline {
    agent any 
    stages {
        stage('checkout') {
            steps {
                
            }
        }

        stage('test') {
            steps {
                sh 'gradle clean test || true'
                junit 'build/test-results/test/**/*.xml'
                
                sh 'gradle findbugsMain'
                sh 'gradle findbugsTest'
                sh 'gradle pmdMain'
                sh 'gradle pmdTest'
                sh 'gradle jacocoTestReport'
                
            }
        }
        stage('publish reports') {
            steps {
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/jacoco/test/html', reportFiles: 'index.html', reportName: 'Jacoco Coverage', reportTitles: ''])
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/pmd', reportFiles: 'main.html', reportName: 'PMD main', reportTitles: ''])
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/pmd', reportFiles: 'test.html', reportName: 'PMD test', reportTitles: ''])
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports', reportFiles: 'findbugs.html', reportName: 'Findbugs', reportTitles: ''])

            }
        }
    }
}
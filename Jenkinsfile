pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                   echo "PATH = ${PATH}"
                   echo "M2_HOME = ${M2_HOME}"
                   '''
             }
         }

        stage('Run the webapp') {
            steps {
                sh 'cd webapp/spring-login-master/'
                sh 'pwd'
                sh 'mvn clean install'
            }
        }

    }
}
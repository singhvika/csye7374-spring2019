pipeline {
    agent any
    stages {
        stage('Run the webapp') {
            steps {
                sh 'cd webapp/spring-login-master'
                sh 'mvn clean install'
            }
        }
    }
}
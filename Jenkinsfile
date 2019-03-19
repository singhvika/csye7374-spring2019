pipeline {
    agent any
    stages {
        stage('Run the webapp') {
            steps {
                sh '/webapp/spring-login-master'
                sh './mvnw clean install'
            }
        }
    }
}
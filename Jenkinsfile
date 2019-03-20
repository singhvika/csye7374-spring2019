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
                dir('webapp/spring-login-master/'){
                    sh 'pwd'
                    sh 'mvn clean install'
                }
                dockerCmd 'build --tag automatingguy/sparktodo:SNAPSHOT .'
                ansiblePlaybook(
                    limit: 'localhost',
                    playbook: '/ansible/docker-push-image.yaml',
                    extraVars: [
                    applicationName: 'mywebapp',
                    tag: 'csye7374image',
                    ecr: 'csye7374',
                    accountId: '945221634161'
                    ]
                )
            }
        }

    }
}
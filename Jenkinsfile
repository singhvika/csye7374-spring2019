podTemplate(
    label: 'mypod',
    inheritFrom: 'default',
    containers: [
        containerTemplate(
            name: 'maven-container',
            image: 'maven:3.6.0-jdk-8-alpine',
            ttyEnabled: true,
            command: 'cat'
        ),
        containerTemplate(
            name: 'docker-container',
            image: 'docker:18.02',
            ttyEnabled: true,
            command: 'cat'
        )
    ],
    volumes: [
        hostPathVolume(
            hostPath: '/var/run/docker.sock',
            mountPath: '/var/run/docker.sock'
        )
    ]
) {
    node('mypod') {
         stage('Checkout') {
             checkout scm
         }

        stage('Current Working Directory'){
            sh 'pwd'
        }

        container('docker-container') {
            stage('Build') {
                dir('webapp/spring-login-master/') {
                    sh 'docker build -t build_testing .'
                }
            }

        }
    }
}
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
    ]
) {
    node('mypod') {
        
         stage ('Extract') {
            checkout scm
        }
        
        stage ('Initialize') {
            container ('maven-container') {
                dir('webapp/spring-login-master/'){
                    sh 'pwd'
                    sh 'mvn clean install'
                }
            }
         }

         stage ('Docker') {
            container ('docker-container') {
                sh 'pwd'
                dir('webapp/spring-login-master/'){
                    sh 'pwd'
                    sh "docker build -t cloudapp1:1 ."
                    sh "docker push -t cloudapp1:1 ."
                }
            }
        }
    }
}
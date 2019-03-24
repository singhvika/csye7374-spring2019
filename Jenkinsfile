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
            name: 'kubectl-container',
            image: 'lachlanevenson/k8s-kubectl:v1.13.4',
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

         stage ('Docker build') {
            
            container ('docker-container') {
                dir('webapp/spring-login-master/'){
                    docker.build('csye7374')
                    docker.withRegistry('https://479392477648.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:ecr-credentials') {
                        docker.image('csye7374').push('latest15')
                    }
                }
            }
         }

         stage ('Deploy application') {    
            
            container ('kubectl-container') {
                dir('k8s/app/'){
                   sh 'kubectl apply -f configmap.yaml'
                   sh 'kubectl apply -f loadbalancer.yaml'
                   sh 'kubectl apply -f deployment.yaml'
                }
            }
         }
    }
}

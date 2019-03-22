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
                    docker.withRegistry('https://945221634161.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:ecr-credentials') {
                        docker.image('csye7374').push('latest14')
                    }
                }
            }
         }

         stage ('Deploy application') {    
            
            checkout scm
            dir('ansible/'){
                echo 'Deploying application'
                sh 'sudo -s'
                ansiblePlaybook( 
                    playbook: 'k8s-setup.yaml',
                    extraVars: [
                    clusterName: 'dcunham.k8s.csye6225-fall2018-dcunham.me',
                    nodeCount: 3,
                    nodeSize: 't2.medium',
                    masterSize: 't2.medium',
                    stateStore: 'dcunham.k8s.csye6225-fall2018-dcunham.me'
                ])
            }
         }
    }
}

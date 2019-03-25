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
    ],
    envVars: [
        secretEnvVar(
                key: 'AWS_ACCOUNT_ID',
                secretName: 'my-secret',
                secretKey: 'aws-account-id'
            )
    ]
) {
    node('mypod') {
        def BUILDTS = "${BUILD_TIMESTAMP}"
        def BUILDTAG = "build-${BUILDTS}"
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
                    docker.build("csye7374")
                    docker.withRegistry('https://${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:aws-kops-user') {
                        docker.image("csye7374").push("${BUILDTAG}")
                        docker.image("csye7374").push("latest")
                        
                    }
                }
            }
         }
        sed 's/word1/word2/g' input.file
        stage('Prepare App Deoplyment yaml'){
            dir ('k8s/app'){
                sh "sed 's/account_id_to_replace/${AWS_ACCOUNT_ID}/g' deployment.yaml"
                sh "cat deployment.yaml"
            }
        }
        
        

         
         
    }
}

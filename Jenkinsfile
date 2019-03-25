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

        stage('Prepare App Deoplyment yaml'){
            dir ('k8s/app'){
                sh "sed 's/account_id_to_replace/${AWS_ACCOUNT_ID}/g' deployment.yaml > deployment-account.yaml"
                sh "sed 's/tag_to_replace/${BUILDTAG}/g' deployment-account.yaml > deployment-tag.yaml"                
                sh "cat deployment-tag.yaml"
            }
        }
        
        

         
         
    }
}

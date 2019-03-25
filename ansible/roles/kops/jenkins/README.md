# Continuous Deployment Pipeline with Jenkins

 ## Requirements
 
 First off, we require your Kubernetes Cluster and AWS Cloud Resources to be up.
 
 The Project we have prepared here, also expects a secret (files) to be present:
 
 1. `var/jenkins_secrets/jenkins-secrets/my-secret`
 
    - To do so, run `kubectl create secret generic my-secret --from-literal=github-secret-user=YOUR_GITHUB_USERNAME --from-literal=github-secret-password=YOUR_GITHUB_PASSWORD --from-literal=aws-access-key-id=AWS_ACCESS_KEY_ID --from-literal=aws-secret-access-key=AWS_SECRET_ACCESS_KEY  --from-literal=aws-account-id=YOUR_AWS_ACCOUNT_ID --namespace default`
 
 This contains your Github and AWS credentials.
 
 [Both of these are mounted using Volume Mounts for our Jenkins Agent and used as Environment Variables, Refer values_jenkins.yaml]
 
 ### Setup Jenkins
 
 Go to the root of your ansible directory and run, 
 `ansible-playbook setup-jenkins.yaml` 
 
 - This will setup your Jenkins using its helm chart.
 - Retreive the default Jenkins login password for default user 'admin'.
 - Give you the login Url
 - Create a Cluster-Role-Binding for Jenkin's 'default' service account in 'default' namespace
 
 The overriden values_jenkins.yaml file does the followingL
 
 - Increases the default CPU and Memory size for both, Jenkins Master and Agent.
 - Specifies additional Plugins to be installed.
 - Configures the Github Credentials using 'Configure as a Code' feature in Jenkins.
 - Configures the Job for Jenkins.
 - Mounts volume for secrets onto Jankins Agent.
  
 #### Delete the Jenkins Release created using `helm chart`
 
 Manually run `helm del --purge RELASE_NAME` and you can run the playbook again to setup. 
  
 
 




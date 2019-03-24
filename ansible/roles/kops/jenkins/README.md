# CI-CD Pipeline with Jenkins


## Requirements:
---
 First off, we reuqire your Kubernetes Cluster and AWS Cloud Resources to be up.
 
 The Project we have prepared here, also expects two secrets (files) to be present:
 
 1. `var/deploy/ecrets/github-secret-user`
 2. `var/deploy/ecrets/github-secret-password`
 
 The first one is a secret for your github username and second one is for your github password to github repositories.
 
 [Both of these are mounted using Volume Mounts for our Jenkins Agent, Refer values_jenkins.yaml]
 
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
  
  
 
 




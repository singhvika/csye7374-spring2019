#Ansible Playbooks


##To run the kubernetes cluster setup playbook, use following command:

`ansible-playbook k8s-setup.yaml --extra-vars "clusterName=cluster-name stateStore=state-store-name nodeCount=node-count nodeSize=node-size masterSize=master-size"`


##To build the web application using maven, change the directory to /webapp/spring-login-master and run the following command:

`mvn clean install`


##To build and push the docker images to ECR, use the following command:

`ansible-playbook docker-push-image.yaml --extra-vars "applicationName=application-name tag=image-tag ecr=ecr-name accountId=aws-account-id”`


##To deploy the containers with docker image using pods, use the following command:

`ansible-playbook pod-deployment.yaml --extra-vars "accountId=aws-account-id tag=image-tag containerName=container-name appName=app-name podName=pod-name ecr=ecr-name"`


##To run the kubernetes cluster teardown playbook, use following command:

`ansible-playbook k8s-teardown.yaml --extra-vars "clusterName=cluster_name  stateStore=state_store_name"`

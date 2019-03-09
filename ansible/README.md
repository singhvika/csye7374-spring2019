# Ansible Playbooks


### To run the kubernetes cluster setup playbook, use following command:

`ansible-playbook k8s-setup.yaml --extra-vars "clusterName=cluster-name stateStore=state-store-name nodeCount=node-count nodeSize=node-size masterSize=master-size"`


##### Dashboard can now be accessed locally via: 
 
(http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/)


### To build the web application using maven, change the directory to /webapp/spring-login-master and run the following command:

`mvn clean install`


### To build and push the docker images to ECR, use the following command:

`ansible-playbook docker-push-image.yaml --extra-vars "applicationName=application-name tag=image-tag ecr=ecr-name accountId=aws-account-id"`

### To Setup all the Cloud Resources, You need you install boto3 and botocore on your machine.
`pip install boto3`

`pip install botocore` 

#### And then use: 

`ansible-playbook setup-cloud-resources.yaml --extra-vars "clusterName=cluster-name"`

### To deploy the containers with docker image using pods, use the following command:

`ansible-playbook pod-deployment.yaml --extra-vars "accountId=aws-account-id tag=image-tag containerName=container-name appName=app-name podName=pod-name ecr=ecr-name s3endpoint=s3-bucket-endpoint"`


### To run the kubernetes cluster teardown playbook, use following command:

`ansible-playbook k8s-teardown.yaml --extra-vars "clusterName=cluster_name  stateStore=state_store_name"`


##### Make REST calls to your application by creating a proxy

`kubectl port-forward csye7374 8888:8080`

`curl http://localhost:8888`

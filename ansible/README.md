# Ansible Playbooks


#### To run the kubernetes cluster setup playbook, use following command:

`ansible-playbook k8s-setup.yaml --extra-vars "clusterName=cluster-name stateStore=state-store-name nodeCount=node-count nodeSize=node-size masterSize=master-size"`


##### Dashboard can now be accessed locally via: 
 
http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/


#### To Setup all the Cloud Resources, You need you install boto3 and botocore on your machine.

`pip install boto3` and 
`pip install botocore`

 And then use: 

`ansible-playbook setup-cloud-resources.yaml --extra-vars "clusterName=cluster-name dbName=db-name dbUser=db-user dbPassword=db-password"`


 Build the web application using maven, change the directory to /webapp/spring-login-master and run the following command:

`mvn clean install`

---
### Deploying the App Manually

- #### To build and push the docker images to ECR, use the following command:
  
  `ansible-playbook docker-push-image.yaml --extra-vars "applicationName=application-name tag=image-tag ecr=ecr-name"`
  
  
- #### To deploy the containers with docker image using pods, use the following command:
  
  `ansible-playbook app-deployment.yaml --extra-vars "clusterName=cluster-name tag=image-tag containerName=container-name appName=app-name podName=pod-name ecr=ecr-name"`
  
### Triggering deployment using Jenkins Continuous Deployment Pipeline

- Refer to `README.md` under `/roles/jenkins`
---

#### To setup centralized logging:

`ansible-playbook setup-centralized-logging.yaml --extra-vars "clusterName=cluster-name"`


#### Setup Monitoring using Prometheus and Grafana 

`ansible-playbook setup_prometheus.yaml --extra-vars "appName=app-name"`

`ansible-playbook setup_grafana.yaml --tags "setup"`

`ansible-playbook setup_grafana.yaml --tags "provision"`


##### Get your 'admin' user password by running:

`kubectl get secret --namespace default grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo`


#### To Make REST calls to your application by creating a proxy

`kubectl port-forward csye7374 8888:8080`

`curl http://localhost:8888`


#### To teardown the kubernetes Cloud Resources and Cluster, use following command:

`ansible-playbook teardown-cloud-resources.yaml --extra-vars "clusterName=cluster_name"`

`ansible-playbook k8s-teardown.yaml --extra-vars "clusterName=cluster_name  stateStore=state_store_name"`

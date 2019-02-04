To run the kubernetes cluster setup playbook, use following command:

ansible-playbook k8s-setup.yaml --extra-vars "clusterName=cluster_name stateStore=state_store_name nodeCount=3 nodeSize=t2.micro masterSize=t2.micro"


To run the kubernetes cluster teardown playbook, use following command:

ansible-playbook k8s-teardown.yaml --extra-vars "clusterName=cluster_name  stateStore=state_store_name"
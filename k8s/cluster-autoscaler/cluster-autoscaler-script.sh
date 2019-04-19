#!/bin/bash

set -e

cat cluster-autoscaler.yaml

kubectl apply -f cluster-autoscaler.yaml

CLUSTER_NAME="keskarc.k8s.keskarc.me"
NODE_INSTANCE_GROUP_NAME="nodes"
MASTER_INSTANCE_GROUP_NAME="masters"
ASG_NAME="${NODE_INSTANCE_GROUP_NAME}.${CLUSTER_NAME}"
MASTER_IAM_ROLE="${MASTER_INSTANCE_GROUP_NAME}.${CLUSTER_NAME}"
KOPS_STATE_STORE="s3://keskarc.k8s.keskarc.me"

#Best-effort install script prerequisites
if [[ -f /usr/bin/apt-get && ! -f /usr/bin/jq ]]
then
  sudo apt-get update
  sudo apt-get install -y jq
fi
if [[ -f /bin/yum && ! -f /bin/jq ]]
then
  echo "This may fail if epel cannot be installed. In that case, correct/install epel and retry."
  sudo yum install -y epel-release 
  sudo yum install -y jq || exit
fi
if [[ -f /usr/local/bin/brew && ! -f /usr/local/bin/jq ]]
then
  brew install jq || exit
fi

echo "Make changes in worker instance group config to set min/max node count, then save and exit when you're done"
sleep 1
kops edit ig ${NODE_INSTANCE_GROUP_NAME} --state ${KOPS_STATE_STORE} --name ${CLUSTER_NAME}
echo "Updating cluster..."
kops update cluster --yes --state ${KOPS_STATE_STORE} --name ${CLUSTER_NAME}
printf "\n"

ASG_POLICY_NAME=aws-cluster-autoscaler
unset TESTOUTPUT
TESTOUTPUT=$(aws iam list-policies --output json | jq -r '.Policies[] | select(.PolicyName == "aws-cluster-autoscaler") | .Arn')
if [[ $? -eq 0 && -n "$TESTOUTPUT" ]]
then
  printf "Policy already exists\n"
  ASG_POLICY_ARN=$TESTOUTPUT
else
  printf "Policy does not yet exist, creating now.\n"
  ASG_POLICY=$(aws iam create-policy --policy-name $ASG_POLICY_NAME --policy-document file://asg-policy.json --output json)
  ASG_POLICY_ARN=$(echo $ASG_POLICY | jq -r '.Policy.Arn')
  printf " ✅ \n"
fi

printf "Attaching policy to IAM Role for master nodes\n"
aws iam attach-role-policy --policy-arn $ASG_POLICY_ARN --role-name $MASTER_IAM_ROLE
printf " ✅ \n"

printf "Done\n"
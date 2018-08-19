#!/bin/bash
set -ex

# move to script folder
cd $(dirname $0)
echo $(pwd)

# SET THE FOLLOWING VARIABLES
# docker hub username 
USERNAME=dennisseidel
# docker hub password
if [ -x "$(command -v lpass)" ]; then
  DOCKER_PASSWORD=$(lpass show docker.com --password)
fi
# image name TODO get this from config? 
IMAGE=apilinter
# get latest githash
LATEST_GIT_HASH=$(git log -1 --format=%h)

clustercheck=$(kubectl rollout status -n istio-system deployment/istio-ingressgateway || true)
if [[ $clustercheck != *"successfully rolled out"* ]]; then
  echo "Istio Ingress-Gateway or Cluster not reachable."
  exit 1
fi

# ensure we're up to date
git pull

# gradle build
cd .. && ./gradlew clean build && cd devops

# run build
docker build -t $USERNAME/$IMAGE:latest --build-arg GIT_COMMIT=$LATEST_GIT_HASH ..

#display the githash in the image
docker inspect $USERNAME/$IMAGE:latest | jq '.[].ContainerConfig.Labels'

# push to docker hub
docker login -u $USERNAME -p $DOCKER_PASSWORD 
docker tag $USERNAME/$IMAGE:latest $USERNAME/$IMAGE:v2
# push it
docker push $USERNAME/$IMAGE:latest
docker push $USERNAME/$IMAGE:v2

# install app
kubectl apply -f config/$IMAGE.yaml
# Confirm all services and pods are correctly defined and running
kubectl get services
kubectl get pods
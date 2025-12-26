### Connect Minikube and Kubectl

## Download and install latest version of following
1. Minikube
2. Kubectl (check for latest version in /bin of your Docker folder in local)
3. Docker Desktop(if already present then fine)

## start minikube
minikube start --driver=docker

## check the status of minikube k8s cluster
minikube status                    

## get the information about the minikube cluster
kubectl cluster-info                             

## get the information about the node in cluster
kubectl get node                                             

# connect minikube with minikube's docker deamon
minikube docker-env

-- After the above command paste the last command from the output in the same console
-- and minikube will be connected with its own docker deamon and
-- now we will be able to use Kubernetes docker and build images inside k8s cluster

# build images of docker by going to the path of Dockerfile of your project
docker build -t <image_name>:<tag> .

-- After the above command, docker image will be build in k8s cluster(via docker) which will only be available in k8s docker, not in local docker desktop deamon

### Create Deployment object with Docker image

## Create deployment
kubectl create deployment <mention name of your deployment> -image=<image_name>:<tag> --port=8080

## Check the deployment status
kubectl get deployment

## Get all descriptions of your deployment object
kubectl describe deployment <name-of-your-deployment>

## Check status of your pods
kubectl get pods

-- the above command will make sure our application/deployment is running inside a pod

## deeper inspection of Pods

kubectl describe pod <pod-name>
kubectl logs <pod-name>

## To view our deployment in UI create Service object
kubectl expose deployment <name of deployment> --type=NodePort
        
        ```
        When you run:
        kubectl expose deployment <name> --type=NodePort --port=8080
        
        
        - Kubernetes creates a Service object automatically.
        - That Service is linked to your Deployment’s Pods (via labels).
        - The Service ensures traffic sent to it is routed to the Pods behind the Deployment.
        
        ✅ How to Verify
        After exposing, check:
        kubectl get svc
        
        
        You’ll see something like:
        NAME        TYPE       CLUSTER-IP     EXTERNAL-IP   PORT(S)          AGE
        <name>      NodePort   10.96.123.45   <none>        8080:31555/TCP   1m
        
        
        - Cluster-IP → internal IP for cluster communication.
        - NodePort (e.g., 31555) → external port on the Minikube VM.
        - Port 8080 → the port your container listens on.
        
        🌐 Accessing It in Minikube
        Since you’re on Minikube, you can open the service directly:
        
        minikube service <name> or minikube service <name> --url
        
        
        This will launch the correct URL in your browser (like http://192.168.49.2:31555).
        
        📌 Summary
        - Yes: kubectl expose automatically creates a Service for your Deployment.
        - Yes: You can fetch it with kubectl get service.
        - To actually reach your app, use the assigned NodePort or minikube service helper.
        
        👉 Vishal, since you’re deploying Spring Boot apps, the next step after exposing is usually to hit your REST endpoints via that NodePort. Do you want me to show you a full end-to-end checklist (build → deploy → expose → access) with the exact commands and expected outputs? That way you’ll have a ready workflow to follow every time.
        
        ```

## check logs about your service
kubectl get service

## access the container running inside pod(access the service outside of cluster)
minikube service <deployment name> --url

-- you will get a url which you can use to access your service endpoints
-- congrats you have deployed your application to Kubernetes

## Check Minikube Kubernetes dashboard
minikube dashboard

-- you will get a link/url of UI which shows all details about pods, nodes, cluster, deployment, service etc


=======================================================

### Before Shutting down(close and delete all services and minikube cluster):

## delete kubectl service
kubectl delete service <service name>

## delete deployment object
kubectl delete deployment <deployment object name>

## verify if any pods, services or deployments are running or not(if yes, then delete all before leaving)
kubectl get pods
kubectl get svc
kubectl get deployments

## stop minikube and then delete minikube cluster
minikube stop (this command will stop the local minikube cluster in our machine)

## finally delete the minikube cluster
minikube delete

======================================================================

### Links for Reference on Kubernetes Deployment of Docker image as container with Minikube cluster and kubectl command line interface:

https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbTMtMFBNdUUtY2R3c2JRc0NfdkEzYnYxMnFSQXxBQ3Jtc0trdVNUOGVtVUMzMFN5d3Jtb3Nmb1BFV0c2aFo4cnRFRURWbG9aUGc2YV9VbGxTUkFLSk5ka0NrOGhSWTJUczh5dUJsMUdDX1lzbkdzaW1rM204akVCdHQwZnpEQ2JObnprdWNhQTZfSXNDR1U4NTU1aw&q=https%3A%2F%2Fmedium.com%2F%40javatechie%2Fkubernetes-tutorial-setup-kubernetes-in-windows-run-spring-boot-application-on-k8s-cluster-c6cab8f7de5a&v=xhxmExC9N1U

https://medium.com/@javatechie/kubernetes-installation-guide-windows-mac-f65105146127

https://www.geeksforgeeks.org/devops/how-to-deploy-java-application-in-kubernetes/


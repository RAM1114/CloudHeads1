node{

  def project = 'CloudHeads'
  def appName = 'cloudHeads1'  
  def imageVersion = "${env.BUILD_NUMBER}.0.0" 
  def namespace = 'dev'
  def imageTag = "iad.ocir.io/codeatcustomer1/${project}/${appName}:${imageVersion}"
  
  //Checkout Code from Git
  checkout scm
  
  //Stage 1: Build the JAR file with Maven.
  stage('Build maven') {
	sh("mvn clean install -DskipTests")
  }
  
  //Stage 2: Build the docker image.
  stage('Build image') {
    sh("docker build -t ${imageTag} .")
  }
  
  //Stage 3: Push the image to docker registry
  stage('Push image to registry') {
    sh("docker push ${imageTag}")
  }
  
  //Stage 4: Deploy Application
  stage('Deploy Application') {
	sh("kubectl get ns ${namespace} || kubectl create ns ${namespace}")
	sh("sed -i 's#iad.ocir.io/codeatcustomer1/fsc/CloudHeads1:latest#${imageTag}#g' ./k8s/${namespace}/*.yml")
	sh("kubectl --namespace=${namespace} apply -f k8s/${namespace}/deployment.yml")
	sh("kubectl --namespace=${namespace} apply -f k8s/${namespace}/service.yml")
	
	sh("echo http://`kubectl --namespace=${namespace} get service/${appName} --output=json | jq -r '.status.loadBalancer.ingress[0].ip'` > ${appName}")
  }
  
}


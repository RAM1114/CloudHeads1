pipeline {
    agent any
    
    stages {
        
        stage('Build') { 
            steps {
                sh "mvn install -DskipTests" 
            }
        }
        stage('Create docker image') { 
            steps {
                script {
                    def scmVars = checkout([
                        $class: 'GitSCM',
                        doGenerateSubmoduleConfigurations: false,
                        userRemoteConfigs: [[
                            url: 'https://github.com/ramdasmayya/CloudHeads1.git'
                          ]],
                        branches: [ [name: '*/master'] ]
                      ])
                sh "docker build -f Dockerfile -t cloudheads1:${scmVars.GIT_COMMIT} ." 
                }
            }
        }
        stage('Push image to OCIR') { 
            steps {
                script {
                    
                    def scmVars = checkout([
                        $class: 'GitSCM',
                        doGenerateSubmoduleConfigurations: false,
                        userRemoteConfigs: [[
                            url: 'https://github.com/ramdasmayya/CloudHeads1.git'
                          ]],
                        branches: [ [name: '*/master'] ]
                      ])
                   
                //sh "docker login -u 'codeatcustomer1/ramdasa.mayya@fedex.com' -p 'Q6(IC.413AK+bT<dlr>#' iad.ocir.io"
                sh "docker login -u 'codeatcustomer1/api.user' -p '#orJ7TKqx;jkl{4m9B#l' iad.ocir.io"
                sh "docker tag cloudheads1:${scmVars.GIT_COMMIT} iad.ocir.io/codeatcustomer1/cloudheads/cloudheads1:${scmVars.GIT_COMMIT}"
                sh "docker push iad.ocir.io/codeatcustomer1/cloudheads/cloudheads1:${scmVars.GIT_COMMIT}" 
                env.GIT_COMMIT = scmVars.GIT_COMMIT
                sh "export GIT_COMMIT=${env.GIT_COMMIT}"
                }
               }
            }
        }

}

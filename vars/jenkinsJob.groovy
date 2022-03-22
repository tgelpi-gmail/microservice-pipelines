def call(){
    node {
        stage('Checkout') {
            checkout scm
        }

         mvnHome = tool 'Maven 3.5.0'
        // Execute different stages depending on the job
        if(env.JOB_NAME.contains("deploy")){
            packageArtifact()
        } else if(env.JOB_NAME.contains("test")) {
            buildAndTest()
        }
    }
}

def packageArtifact(){
    stage("Package artifact") {
        sh "mvn package"
    }
}

def buildAndTest(){
    stage("Backend tests"){
        sh "printenv;mvn test"
    }
}

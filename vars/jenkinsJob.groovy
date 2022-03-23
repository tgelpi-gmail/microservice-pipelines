def call(){
    node {
        stage('Checkout') {
            checkout scm
        }

        
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
        withEnv(["PATH=/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"]) {
        sh "mvn package"
        }
    }
}

def buildAndTest(){
    stage("Backend tests") {
        withEnv(["PATH=/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"]) {
        sh "mvn test"
        }
    }
}

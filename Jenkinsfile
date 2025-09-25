pipeline {
  agent any
  tools {
    maven 'maven-3.6'
  }
  stages {
    stage("build jar") {
      steps {
        script {
          echo "building the docker image ..."
          sh 'mvn package'
        }
      }
    }
    stage("deploy") {
      steps {
        script {
          echo "deploying the application"
        }
      }
    }
  }
}

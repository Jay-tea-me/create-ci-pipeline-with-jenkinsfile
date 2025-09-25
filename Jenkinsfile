pipeline {
  agent any
  tools {
    maven 'maven-3.6'
  }
  stages {
    stage("build jar") {
      steps {
        script {
          echo "building the app..."
          sh 'mvn package'
        }
      }
    }
    stage("build image") {
      steps {
        script {
          echo "building the docker image ..."
          withCredentials([usernamePassword(credentialsId: 'e91a009b-9542-4577-9fa9-5753e021693f', passwordVariable: "PASS", usernameVariable: "USER")]) {
            sh 'docker build -t jaybee55/demo-app:jma-2.0 .'
            sh "echo $PASS | docker login -u $USER --password-stdin"
            sh 'docker push jaybee55/demo-app:jma-2.0'
          }
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

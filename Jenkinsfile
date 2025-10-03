@Library('jenkins-shared-library')

def gv 

pipeline {
  agent any
  tools {
    maven 'maven-3.6'
  }
  stages {
     stage("init") {
      steps {
        script {
          gv = load "script.groovy"
        }
      }
    } 
    stage("test") {
      steps {
        script {
          echo "testing the app"
          echo "Executing pipeline for branch $BRANCH_NAME"
        }
      }
    }
    stage("build jar") {
      steps {
        script {
          buildJar()
        }
      }
    }
    stage("build and push image") {
      steps {
        script {
          buildImage "jaybee55/demo-app:jma-3.0"
          dockerLogin()
          dockerPush "jaybee55/demo-app:jma-3.0"
        }
      }
    }
    stage("deploy") {
      steps {
        script {
          gv.deployApp()
        }
      }
    }
  }
}

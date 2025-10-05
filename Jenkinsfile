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
          echo "Executing pipeline for branch $GITHUB_REF_NAME	"
        }
      }
    }
    stage("build") {
      when {
        expression {
          GITHUB_REF_NAME == "main"
        }
      }
      steps {
        script {
          gv.buildApp()
        }
      }
    }
    stage("deploy") {
       when {
        expression {
          GITHUB_REF_NAME == "main"
        }
      }
      steps {
        script {
          gv.buildImage()
          gv.deployApp()
        }
      }
    }
  }
}

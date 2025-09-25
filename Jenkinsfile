def gv 
pipeline {
  agent any
  tools {
    maven 'maven-3.6'
  }
  stages {
    stage("test") {
      steps {
        script {
          echo "testing the app"
          echo "Executing pipeline for branch $BRANCH_NAME"
        }
      }
    }
    stage("build") {
      when {
        expression {
          BRANCH_NAME == "main"
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
          BRANCH_NAME == "main"
        }
      }
      steps {
        script {
          gv.buildImage()
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

library identifier: "jenkins-shared-library@master", retriever: modernSCM([
  $class: "GitSCMSource",
  remote: "https://gitlab.com/justea1/devops/jenkins/jenkins-shared-library.git",
  credentialsId: "jenkins-gitlab"
])

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
          echo "test"
          gv = load "script.groovy"
        }
      }
    } 
    stage("increment version") {
      steps {
        script {
          echo "incrementing app version..."
          sh 'mvn build=helper:parse-version \
            versions:set \
            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
            versions:commit'
          def matcher = readFile('pom.xml') =~ '<version>(.+)<version>'
          def version = matcher[0][1]
          env.IMAGE_NAME = "$version-$BUILD_NUMBER"
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
          buildImage "jaybee55/demo-app:$IMAGE_NAME"
          dockerLogin()
          dockerPush "jaybee55/demo-app:$IMAGE_NAME"
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

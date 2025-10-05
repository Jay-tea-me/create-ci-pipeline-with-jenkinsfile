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
          sh 'mvn build-helper:parse-version \
            versions:set \
            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
            versions:commit'
          def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
          def version = matcher[0][1]
          env.IMAGE_NAME = "jma-$version-$BUILD_NUMBER"
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
          sh "mvn clean package"
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
    stage("commit version update") {
      steps {
        script {
          withCredentials([script.usernamePassword(credentialsId: '95b99489-575d-4f46-a907-cad03fee42ed', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            sh 'git config --global user.email "jenkins@example.com"'
            sh 'git config --global user.name "jenkins"'

            sh 'git status'
            sh 'git branch'
            sh 'git config --list'

            git 'git remote set-url origin https://${USER}:${PASS}//github.com/Jay-tea-me/create-ci-pipeline-with-jenkinsfile.git'
            sh 'git add .'
            sh 'git commit -m "ci: version bump"'
            sh 'git push origin HEAD:jenkins-shared-lib'
        }
      }
    }
  }
}
}


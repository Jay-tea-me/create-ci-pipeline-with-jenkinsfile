pipeline {
  agent any
  parameters {
    choice(name: 'VERSION', choices: ['1.1.0', '1.2.0'])
    booleanParam(name: 'executeTests', defaultValue: true)
  }
  stages {
    stage("build") {
      steps {
        echo 'building the application...'
      }
    }
    stage("test") {
      steps {
        when {
          expression {
            params.executeTests
          }
        }
        echo 'testing the application...'
      }
    }
    stage("deploy") {
      steps {
        echo 'deploying the application...'
        echo "deploying version ${params.VERSION}"
      }
    }
  }
  post {
    always {
      
    }
    failure {
      
    }
    success {
      
    }
  }
  
}

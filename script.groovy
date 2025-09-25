def buildApp() {
    echo 'building the app... :)'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image ..."
    withCredentials([usernamePassword(credentialsId: 'e91a009b-9542-4577-9fa9-5753e021693f', passwordVariable: "PASS", usernameVariable: "USER")]) {
        sh 'docker build -t jaybee55/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push jaybee55/demo-app:jma-2.0'
    }
}

def deployApp() {
    echo "deploying the application"
}

return this //import into jenkins file
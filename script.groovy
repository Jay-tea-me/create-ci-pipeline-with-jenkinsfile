def buildApp() {
    echo 'building the app... :)'
}

def testApp() {
    echo 'testing the app... :)'
}

def deployApp() {
    echo 'deploying the app... :)'
    echo "deploying version ${params.VERSION}"
}
return this //import into jenkins file
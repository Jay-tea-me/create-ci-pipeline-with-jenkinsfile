def buildApp() {
    echo 'building the app... :)'
}

def testApp() {
    echo 'testing the app... :)'
}

def deployApp() {
    env.ENV = input message "Select the environment to deploy to:", ok "Done", parameters: [choice(name: 'ENV', choices: ['dev', 'staging', 'prod'], description: '')]
    echo 'deploying the app... :)'
    echo "deploying version ${params.VERSION}"
    echo "deploying to ${ENV}"
}
return this //import into jenkins file
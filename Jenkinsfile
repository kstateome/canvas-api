@Library('jenkins-shared-libs') _
def config = [ appName: 'canvas-api',
               podName: 'java-21-maven-3.9.9.yaml',
               containerName: 'jdk-21-maven',
               runUnitTests: true,
               runIntegrationTests: false,
               runSonar: true
             ]
javaPipeline(config)

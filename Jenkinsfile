pipeline {
    agent any
    environment {
        testDeploy = "${BRANCH_NAME}"

        regexIgnore = ".*maven-release-plugin.*;.*skipJenkins.*"

        testDeployPromptChannel = "javajavajava"
        releaseConfirmChannel = "javajavajava"
        buildFailureNotificationChannel = "javabuilds"
        releaseBuiltNotificationChannel = "javajavajava"

        JENKINS_AVATAR_URL = "https://jenkins.ome.ksu.edu/static/ce7853c9/images/headshot.png"
    }

    tools {
        maven "Maven 3.5"
        jdk "Java 8"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    if (shouldIgnoreCommit(env.regexIgnore.split(';'))) {
                        error "Ignoring commit"
                    }
                }
                sh 'mvn clean package -DskipTests'
            }

            post {
                always {
                    script {
                        if (shouldIgnoreCommit(env.regexIgnore.split(';'))) {
                            currentBuild.result = 'NOT_BUILT'
                        }
                    }
                }
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
                failure {
                    rocketSend avatar: "$JENKINS_AVATAR_URL", message: "${env.JOB_NAME} had unit test failures on branch ${env.BRANCH_NAME} \nRecent Changes - ${getChangeString(10)}\nBuild: ${BUILD_URL}", rawMessage: true
                }
                unstable {
                    rocketSend avatar: "$JENKINS_AVATAR_URL", message: "${env.JOB_NAME} had unit test failures on branch ${env.BRANCH_NAME} \nRecent Changes - ${getChangeString(10)}\nBuild: ${BUILD_URL}", rawMessage: true
                }
                changed {
                    script {
                        if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                            rocketSend avatar: "$JENKINS_AVATAR_URL", message: "${env.JOB_NAME} now has passing unit tests on branch ${env.BRANCH_NAME} \nRecent Changes - ${getChangeString(10)}\nBuild: ${BUILD_URL}", rawMessage: true
                        }
                    }
                }
            }
        }

        stage('Sonar') {
            when {
                branch 'master'
            }
            steps {
                sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
                sh "mvn sonar:sonar -P sonar -Dsonar.branch=${env.branch_name}"
            }
        }

        stage('Maven Site') {
            when { branch 'master' }
            steps {
                sh 'mvn site-deploy'
            }
            post {
                success {
                    rocketSend avatar: "$JENKINS_AVATAR_URL", channel: 'javabuilds', message: "Successfully generated Maven site documentation for canvas-api: https://jenkins.ome.ksu.edu/maven-site/canvas-api/", rawMessage: true
                }
            }
        }


    }
    post {
        always {
            deleteDir()
        }
    }
}

@NonCPS
def version() {
    pom = readMavenPom file: 'pom.xml'
    pom.version
}

def shouldIgnoreCommit(regexIgnoreList) {
    def lastCommit = sh (script: 'git log --pretty=oneline | head -n 1', returnStdout: true)
    // For loop is used because [].each is not serializable
    for (int i = 0; i < regexIgnoreList.size(); i++) {
        if (lastCommit =~ /${regexIgnoreList[i]}/) {
            return true
        }
    }
    return false
}

@NonCPS
def getChangeString(maxMessages) {
    MAX_MSG_LEN = 100
    COMMIT_HASH_DISPLAY_LEN = 7
    def changeString = ""

    def changeLogSets = currentBuild.changeSets


    for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length && i + j < maxMessages; j++) {
            def entry = entries[j]
            truncated_msg = entry.msg.take(MAX_MSG_LEN)
            commitHash = entry.commitId.take(COMMIT_HASH_DISPLAY_LEN)
            changeString += "${commitHash}... - ${truncated_msg} [${entry.author}]\n"
        }
    }

    if (!changeString) {
        changeString = " There have not been changes since the last build"
    }
    return changeString
}

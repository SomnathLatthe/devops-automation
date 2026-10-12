pipeline{
    agent any
    tools{
        maven 'maven3.10.0'
        dockerTool 'jenkin-docker'
    }
    stages{
        stage('Build Maven') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']],
                    // 💡 ADDED: This clears out any old broken metadata on every fresh pull
                    extensions: [[$class: 'WipeWorkspace']],
                    userRemoteConfigs: [[url: 'https://github.com/SomnathLatthe/devops-automation']]
                )
                sh 'sh mvnw clean install'
            }
        }

        // stage('Build Maven'){
        //     steps{
        //         checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/SomnathLatthe/devops-automation']])
        //         sh 'sh mvnw clean install'
        //     }
        // }
        stage('Build docker image'){
            steps{
                // sh "docker build -t SomnathLatthe/devops-automation ."
                script {
                    // 💡 Using the native Docker plugin wrapper removes the need for the global CLI tool
                    docker.build("somnathdoc/devops-automation:latest", ".")
                }
            }
        }
        // stage("push image to hub"){
        //     steps{
        //         withCredentials([string(credentialsId: 'dochub-pwd', variable: 'dockerhubpwd')]) {
        //             sh 'docker login -u somnathdoc -p ${dockerhubpwd}'
        //         }
        //         sh 'docker push somnathlatthe/devops-automation'
        //     }
        // }


        stage("push image to hub") {
            steps {
                // Use your existing 'dochub-pwd' secret string
                withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    // 💡 1. Must use DOUBLE QUOTES (") so Jenkins can read the variable
                    // 💡 2. Use --password-stdin to safely stream the password and prevent parsing errors
                    sh "echo \${dockerhubpwd} | docker login -u somnathdoc --password-stdin"

                    // Pushes the built image to your repository
                    sh 'docker push somnathdoc/devops-automation:latest'
                }
            }
        }

    }
}
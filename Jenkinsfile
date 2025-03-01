pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK17'  // Ensure JDK 17 is installed in Jenkins
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Compile Groovy Files') {
            steps {
                script {
                    def groovyFiles = findFiles(glob: 'src/main/resources/**/*.groovy')

                    if (groovyFiles.length > 0) {
                        echo "Compiling ${groovyFiles.length} Groovy files..."
                        sh "groovyc -d build/groovy-classes ${groovyFiles.collect { it.path }.join(' ')}"
                    } else {
                        echo "No Groovy files found in resources."
                    }
                }
            }
        }

        stage('Build with Maven') {
            steps {
                sh './mvnw clean package -DskipTests'  // Using Maven Wrapper
            }
        }

        stage('Run Tests') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Package and Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed! Check the logs.'
        }
    }
}
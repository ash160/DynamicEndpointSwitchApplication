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

        stage('Build Application') {
            steps {
                script {
                    if (fileExists('mvnw')) {
                        sh './mvnw clean package -DskipTests'  // Use Maven Wrapper
                    } else if (fileExists('gradlew')) {
                        sh './gradlew build -x test'  // Use Gradle Wrapper
                    } else {
                        error "No Maven or Gradle build file found!"
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    if (fileExists('mvnw')) {
                        sh './mvnw test'
                    } else if (fileExists('gradlew')) {
                        sh './gradlew test'
                    } else {
                        error "No Maven or Gradle build file found!"
                    }
                }
            }
        }

        stage('Package and Archive') {
            steps {
                script {
                    def artifactPath = 'target/*.jar'
                    if (fileExists('build/libs/')) {
                        artifactPath = 'build/libs/*.jar'  // Gradle default output
                    }
                    archiveArtifacts artifacts: artifactPath, fingerprint: true
                }
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
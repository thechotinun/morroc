pipeline {
    agent any
    tools {
        maven 'Maven3.9'
    }

    environment {
        DATASOURCE_URL=credentials('datasource_url')
        DATASOURCE_USERNAME=credentials('datasource_username')
        DATASOURCE_PASSWORD=credentials('datasource_password')
        GOOGLE_CLIENT_ID=credentials('google_client_id')
        GOOGLE_CLIENT_SECRET=credentials('google_client_secret')
        GOOGLE_REDIRECT_URI=credentials('google_redirect_uri')
        JWT_SECRET=credentials('jwt_secret')
    }

    stages {
        stage('Clean and install') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build and run containers') {
            steps {
                echo "Stopping and removing containers"
                sh 'docker-compose down --rmi all'
                
                echo "Building and running containers"
                sh '''
                    docker-compose build \
                        --build-arg DATASOURCE_URL=${DATASOURCE_URL} \
                        --build-arg DATASOURCE_USERNAME=${DATASOURCE_USERNAME} \
                        --build-arg DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD} \
                        --build-arg GOOGLE_CLIENT_ID=${GOOGLE_CLIENT_ID} \
                        --build-arg GOOGLE_CLIENT_SECRET=${GOOGLE_CLIENT_SECRET} \
                        --build-arg GOOGLE_REDIRECT_URI=${GOOGLE_REDIRECT_URI} \
                        --build-arg JWT_SECRET=${JWT_SECRET}
                    docker-compose up -d
                '''
            }
        }
    }
}
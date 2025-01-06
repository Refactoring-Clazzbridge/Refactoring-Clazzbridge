pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'your-docker-repo/your-spring-boot-app:latest'  // 도커 이미지 경로
        KUBECONFIG = '/Users/jeongchan-u/.kube/config'  // Kubeconfig 파일 경로
    }

    stages {
        stage('Build') {
            steps {
                // Docker 이미지 빌드
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Test') {
            steps {
                // 예: 그레이들 테스트
                sh './gradlew test'
            }
        }

        stage('Push Docker Image') {
            steps {
                // Docker 이미지 푸시
                sh 'docker push $DOCKER_IMAGE'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                // Kubernetes에 배포
                sh 'kubectl apply -f kubernetes/k8s-deployment.yaml'
            }
        }
    }
}
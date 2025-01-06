# Build 단계
FROM node:20-alpine as builder

WORKDIR /FrontEnd

# package.json과 lock 파일 복사 및 의존성 설치
COPY package.json package-lock.json ./
RUN npm install

# 소스코드 복사
COPY ./ ./

# React 빌드 실행
RUN npm run build

# Production 이미지 생성 단계
FROM nginx:alpine

# 빌드 결과물을 Nginx HTML 디렉토리로 복사
COPY --from=builder /FrontEnd/build /usr/share/nginx/html

# Nginx 설정
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

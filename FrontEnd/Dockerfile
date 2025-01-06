FROM node:20-alpine as builder

WORKDIR /FrontEnd

COPY package.json package-lock.json ./

RUN npm install
RUN npm install --save-dev @babel/plugin-syntax-dynamic-import

COPY ./ ./

RUN npm run build

FROM nginx:alpine
COPY --from=builder /build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

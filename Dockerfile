
FROM nginx:alpine

COPY src/main/resources/registration.html /usr/share/nginx/html/index.html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
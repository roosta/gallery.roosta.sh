FROM nginx:latest
ADD  resources/nginx/default.conf /etc/nginx/conf.d/default.conf
ADD  prod/index.html              /usr/share/nginx/html
ADD  prod/css                     /usr/share/nginx/html/css
ADD  prod/img                     /usr/share/nginx/html/img
ADD  prod/js                      /usr/share/nginx/html/js

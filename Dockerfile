FROM node:latest AS build-deps
WORKDIR /usr/src/app
COPY . ./
RUN npm ci
RUN npm run build

FROM nginx:latest
COPY --from=build-deps /usr/src/app/dist /usr/share/nginx/html
COPY --from=build-deps /usr/src/app/nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build-deps /usr/src/app/nginx/nginx-block-ai-bots.conf /etc/nginx/nginx-block-ai-bots.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

FROM node:latest as build

WORKDIR  /usr/local/app

COPY ./ /usr/local/app/

RUN npm install 


RUN npm run build --configuration=production

FROM nginx:latest

COPY --from=build /usr/local/app/dist/managment_tournoi_frontend /usr/share/nginx/html

EXPOSE 42
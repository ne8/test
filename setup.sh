#!/bin/bash
echo Creating authorization server
cd authorization-server;
mvn clean install; docker build -t authorization-server .
cd ..

echo  Creating backoffice-ui
cd backoffice-ui 
npm install
cd ..
echo Creating beelove-backend
cd bee-love-backend
mvn clean install; docker-compose up -d;
cd ..
echo Creating frontend
cd beelove
npm install
npm start
cd ..
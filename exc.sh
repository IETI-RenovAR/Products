#!/bin/bash

sudo docker stop products
git pull
sudo docker rm products
sudo docker build -t products .
sudo docker run -d -p 8082:8080 --name products products



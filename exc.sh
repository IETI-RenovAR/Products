#!/bin/bash

sudo docker stop products
git pull
sudo docker rm products
sudo docker build -t products .
sudo docker run -d --name products products



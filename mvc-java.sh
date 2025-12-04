#!/bin/bash

set -e

NAME="webapi_container"
IMAGE="pedro007salo/webapi:latest"
PORT="4444:8081"
VOLUME_UPLOADS="/volumes/webapi/uploads:/app/uploads"
VOLUME_IMAGES="/volumes/webapi/images:/app/images"

server_up() {
    echo "Server up..."
    docker pull $IMAGE
    docker stop $NAME || true
    docker rm $NAME || true
    docker run -d --restart=always -v $VOLUME_UPLOADS --name $NAME -p $PORT $IMAGE
}

start_containers() {
    echo "Containers start..."
    docker start $NAME || echo "Container does not exist. Run Server up first."
}

stop_containers() {
    echo "Containers stop..."
    docker stop $NAME || true
}

restart_containers() {
    echo "Containers restart..."
    docker stop $NAME || true
    docker rm $NAME || true
    docker run -d --restart=always -v $VOLUME_UPLOADS --name $NAME -p $PORT $IMAGE
}

echo "Choose action:"
echo "1. Server up"
echo "2. Containers start"
echo "3. Containers stop"
echo "4. Containers restart"
read -p "Enter action number: " action

case $action in
    1) server_up ;;
    2) start_containers ;;
    3) stop_containers ;;
    4) restart_containers ;;
    *) echo "Invalid action number!" ; exit 1 ;;
esac

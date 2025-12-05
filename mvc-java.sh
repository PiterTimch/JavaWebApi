#!/bin/bash
set -e

# ---------- BACKEND ----------
BACK_NAME="webapi_container"
BACK_IMAGE="pedro007salo/webapi:latest"
BACK_PORT="4444:8081"
VOLUME_UPLOADS="/volumes/webapi/uploads:/app/uploads"

# ---------- FRONTEND ----------
FRONT_NAME="webapi_frontend"
FRONT_IMAGE="pedro007salo/webapi-react-java:latest"
FRONT_PORT="3000:80"  # порт фронта на хості

# ---------- Функції ----------
server_up() {
    echo "Backend up..."
    docker pull $BACK_IMAGE
    docker stop $BACK_NAME || true
    docker rm $BACK_NAME || true
    docker run -d --restart=always -v $VOLUME_UPLOADS --name $BACK_NAME -p $BACK_PORT $BACK_IMAGE

    echo "Frontend up..."
    docker pull $FRONT_IMAGE
    docker stop $FRONT_NAME || true
    docker rm $FRONT_NAME || true
    docker run -d --restart=always --name $FRONT_NAME -p $FRONT_PORT $FRONT_IMAGE
}

start_containers() {
    echo "Starting containers..."
    docker start $BACK_NAME || echo "Backend does not exist. Run Server up first."
    docker start $FRONT_NAME || echo "Frontend does not exist. Run Server up first."
}

stop_containers() {
    echo "Stopping containers..."
    docker stop $BACK_NAME || true
    docker stop $FRONT_NAME || true
}

restart_containers() {
    echo "Restarting containers..."
    stop_containers
    docker rm $BACK_NAME $FRONT_NAME || true
    server_up
}

# ---------- Меню ----------
echo "Choose action:"
echo "1. Server up (pull + run backend and frontend)"
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

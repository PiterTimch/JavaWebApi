# Маршрутні перевезення
```
Проект про маршрутні перевезення за кордон.

```

# web_site
```
mvn spring-boot:run

mvn clean package

java -jar target/webApi.jar
```

## Run docker
```
docker build -t webapi .

docker run -it --rm -p 4444:8081 --name webapi_container webapi

docker run -d --restart=always -p 4444:8081 --name webapi_container webapi
 
docker run -d --restart=always -p 4444:8081 --name webapi_container webapi

docker run -d --restart=always -v d:/volumes/webapi/uploads:/app/uploads -p 4444:8081 --name webapi_container webapi

docker run -d --restart=always -v /volumes/webapi/uploads:/app/uploads -p 4444:8081 --name webapi_container webapi

docker login
docker tag webapi:latest pedro007salo/webapi:latest
docker push pedro007salo/webapi:latest

ls -l mvc-java.sh
chmod +x mvc-java.sh
sh mvc-java.sh

docker ps -a

docker logs webapi_container

docker stop $(docker ps -aq) 2>/dev/null
docker rm -f $(docker ps -aq) 2>/dev/null
docker rmi -f $(docker images -aq) 2>/dev/null
docker volume rm -f $(docker volume ls -q) 2>/dev/null

server {
    server_name   javap22.itstep.click *.javap22.itstep.click;
    client_max_body_size 200M;
    location / {
       proxy_pass 	  http://localhost:4444;
       proxy_http_version 1.1;
       proxy_set_header   Upgrade $http_upgrade;
       proxy_set_header   Connection keep-alive;
       proxy_set_header   Host $host;
       proxy_cache_bypass $http_upgrade;
       proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
       proxy_set_header   X-Forwarded-Proto $scheme;
    }
}

nginx -t
systemctl restart nginx
```
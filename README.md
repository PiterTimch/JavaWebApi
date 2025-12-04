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
```
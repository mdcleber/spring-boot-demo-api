docker network create demo-net
docker run --net demo-net --name postgres-docker -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
docker build --build-arg JAR_FILE=build/libs/*.jar -t springio/demo-api . 
docker run --net demo-net --name demo-api -p 8080:8080 -t springio/demo-api 
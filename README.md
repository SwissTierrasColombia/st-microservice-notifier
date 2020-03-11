# Microservice Notifier

Microservice that allows the sending of notifications by email.

## Running Development

```sh
$ mvn spring-boot:run
```

## Configuration 

### How to disable eureka client?

Modify the **enabled** property in st-microservice-notifier/src/main/resources/**application.yml** file:

```yml
eureka:
  client:
    enabled: false
```

### How to disable config client?

Modify the **enabled** property in st-microservice-notifier/src/main/resources/**bootstrap.yml** file:

```yml
spring:
  application:
    name: st-microservice-notifier
  cloud:
    config:
      enabled: false
```

## Swagger Documentation?

See [http://localhost:8886/swagger-ui.html](http://localhost:8886/swagger-ui.html)

## Running Production

### Master Branch

Go to the master branch

```sh
$ git checkout master
```

### Generate jar

```sh
$ mvn clean package -DskipTests
```

### Create Network Docker

```sh
$ docker network create st
```

### Create image from Dockerfile

```sh
$ docker build -t st-microservice-notifier:ursus .
```

### Run Container

```sh
$ docker run -P -t --network st -d -v /opt/file-manager/files:/opt/file-manager/files  st-microservice-notifier:ursus
```

## License

[Agencia de Implementación - BSF Swissphoto - INCIGE](https://github.com/AgenciaImplementacion/st-microservice-notifier/blob/master/LICENSE)

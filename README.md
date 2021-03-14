## Structure
- `db` - database scripts
- `api` - micro service that gives backend API
- `ui` - micro front gives interface

## Technology stack
`db`: 
    - liquibase
`api`:
    - 
`ui`:
    -       

## Preparation

Install:
- [openjdk](https://openjdk.java.net)
- [node](https://nodejs.org)
- [docker](https://www.docker.com)

## Compile artifact

Compile project:
```
./gradlew clean build
OR
./gradlew clean assemble
```

## Local launch

### Start database

Set up database and use liquibase scripts ([database](db/README.md)).

### Start front
go to ui folder
use commands:
npm install
npm start

or via gradle plugin

./gradlew :ui:npm_run_build
./gradlew :ui:npm_run_start
see https://github.com/node-gradle/gradle-node-plugin

### Start modules

```
./gradlew clean assemble

docker-compose build
docker-compose up
```
Open browser http://localhost:3006/index.html

#### Launch in cluster [Minishift](https://www.okd.io/minishift/)

https://github.com/kpy3no/cd_template_front_back.git

## App parameters

Application parameters are passed through an external config file `application.yml`. 
The file location is set via the application parameter`spring.config.additional-location`.

## Logging parameters

Logging parameters are passed through an external file `logback.xml`.
The file location is set via the application parameter `logging.config`.

## useful commands
Docker:
 go to bash container: docker exec -it 2325925e93f6 /bin/bash
 run single service: docker-compose up -d ui
 restart single service: docker-compose restart ui


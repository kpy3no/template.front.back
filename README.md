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
```

## Local launch

### Start database

Set up database and use liquibase scripts ([database](db/README.md)).

### Start modules

```
docker-compose up
```
Open browser http://localhost:3005/index.html

#### Launch in cluster [Minishift](https://www.okd.io/minishift/)

https://github.com/kpy3no/cd_template_front_back.git

Clone the repository with scripts, and follow the instructions for local deployment.
```
git clone https://sbrf-bitbucket.sigma.sbrf.ru/projects/CI00751332/repos/ci02656582_sbercms_campaign/browse
```

## App parameters

Application parameters are passed through an external config file `application.yml`. 
The file location is set via the application parameter`spring.config.additional-location`.

## Logging parameters

Logging parameters are passed through an external file `logback.xml`.
The file location is set via the application parameter `logging.config`.

# API component

## Local start without docker

### PostgreSQL
При поднятии приложения, скрипты проливки БД автоматически не проливаются (см. [campaign-db](../../campaign-db/README.md)).

#### Gradle

```
SPRING_PROFILES_ACTIVE=local gradlew :backend:campaign-api:bootRun
```

#### IntelliJ IDEA
Run configuration `Application` use `Spring Boot -> Active Profiles` to `local`.

## Local launch in Docker
look at [docker-compose](../../docker-compose.yml)

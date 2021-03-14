# Database scripts

### PostgreSQL

Run instance via [docker-compose](../docker-compose.yml).
```
docker-compose up -d db
```

### Update database scheme

Update db:
```
./gradlew liquibaseUpdate
```
        
Rollback last 10 changes:
```
./gradlew liquibaseRollbackCount -PliquibaseCommandValue=10
```

Clean db:
```
./gradlew liquibaseDropAll
```
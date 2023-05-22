# Бек на java

## Описание
Бек на JAVA для справочника городов.

## Структура
ru.template.example.configuration - общая конфигурация (как перехватывать ошибки CustomRestExceptionHandler и тд)
ru.template.example.cities.controller - контроллер + дто
ru.template.example.cities.model - модель города. ORM с помощью hibernate.
ru.template.example.cities.repository - репозиторий. Общение с БД.
ru.template.example.cities.service - бизнес логика

resources/db.migration - скрипт миграции. Проливается автоматически через flyway на БД postgres
resources/application.yml - настройки приложения.

## Стек
Spring boot + hibernate + JPA + flyway
Зависимости описаны в build.gradle

## Компилирование проекта

```
../gradlew :backend:clean :backend:build
OR
../gradlew :backend:clean :backend:assemble
```

## Тесты
template.front.back/backend/src/test/java/ru/template/example

Запускаются на стадии ../gradlew :backend:clean :backend:build

## Запуск
Используется внутренняя БД H2 (см. application.yml проперти url). Файл создается в домашней директории ~/db.mv.db

```
../gradlew :backend:clean :backend:assemble
go to build folder template.front.back/backend/build/libs and execute jar
java -jar backend-1.0-SNAPSHOT.jar 
```

З.Ы. При желании можно использовать БД Postgres. Надо развернуть дистрибутив, создать БД и пользователя https://stackoverflow.com/questions/30641512/create-database-from-command-line. Или через docker как вариант. + добавить зависимость в build.gradle org.postgresql:postgresql и прописать url в application.yml.

Сервер поднимется на порту 8888.
Пропишите в браузер http://localhost:8888/cities. Должна вернуться json городов.
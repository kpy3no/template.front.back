## Описание
Фул стек приложение, которое реализует справочник городов. Можно смотреть справочник, наполнять, удалять. 

## Структура
- `backend` - Бэкенд на java.
- `back-python` - Бэкенд на питоне.
- `ui` - Фронтенд на react + redux.

Бэк на java и на питоне реализуют один и тот же функционал.
Более детально о каждом модуле читайте соответствующие Readme.md. Например backend/Readme.md.

## Подготовка

Установите:
- [node](https://nodejs.org) - front
- [openjdk](https://openjdk.java.net) 11 - java бэк
- gradle - java бэк
- python - бэк на питоне

## Запуск

### Запуск фронта
go to ui folder (см. ui/Readme.md)
use commands:
npm install
npm start

or via gradle plugin

./gradlew :ui:npm_run_build
./gradlew :ui:npm_run_start
see https://github.com/node-gradle/gradle-node-plugin

### Запуск бек на питоне

go to back-python folder (см. back-python/Readme.md)

```
python main.py 
```
Пропишите в браузер http://localhost:8889/cities. Должна вернуться json городов.


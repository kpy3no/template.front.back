# Фронт на react

## Описание
Фронт для справочника городов.

## Структура
src/api/cruds-api.js - содержит функции, которые вызывает по HTTP функционал бека. При этом пробрасывает события через redux. Например: 
1) фронт вызывает апи бека и бросает событие `GET_${suffix}_LIST_START`
2) Форма, которая подписалась на это событие (CityList.jsx) отображает progress bar пока не придет событие `GET_${suffix}_LIST`, которое говорит о том, что данные по http успешно получены и progress bar пора убирать. (где suffix = 'CITIES')

src/containers/cities/CityDetails.jsx - форма для редактирования одного города
src/containers/cities/CityList.jsx - форма таблица. Эта форма сперва показывается на фронте
src/containers/common/AbstractEditForm - общая форма для редактирования одного объекта
Файлы в структуре src/containers/common/components/table/... - это копипаста файлов из библиотеки material-ui, но с небольшими доработками.

src/reducers - конвертирует события redux в состояния, на изменение которых подписываются формы. Например, если получили `GET_${suffix}_LIST_START`, то это значит, что надо выставить флажок isLoading: true. И уже CityList добавляет на форму progress bar <Loader/>, если этот флажок = true.


public/index.html - точка входа программы. Проперти window.config говорит где расположен бек. Весь фронт код сжимается в main.js и подсовывается в index.html (более подробно см. babel, webpack)

## Зависимости
См. package.json

## Запуск
1) npm install
2) npm start

Фронт поднимется на порту 9000
http://localhost:9000/#/
(если у вас бек на питоне, то поменяйте в index.html apiUrl порт на 8889)
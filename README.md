# Job4j_url_shortcut

[![Build](https://github.com/CyberfuzZ-Apps/job4j_url_shortcut/actions/workflows/maven.yml/badge.svg)](https://github.com/CyberfuzZ-Apps/job4j_url_shortcut/actions/workflows/maven.yml)

## О проекте:

Url_shortcut - позволяет обеспечить безопасность пользователей на сайте 
заменяя обычные ссылки на преобразованные. Сервис работает через REST API.

Использованы технологии:

- Spring Boot 2:
    - Spring Web MVC
    - Spring Data JPA
    - Spring Security / JWT authorization
- Java 11
- PostgreSQL
- Maven
- Tomcat

## Подробнее:

### 1. Регистрация сайта.
Сервисом могут пользоваться разные сайты. Каждому сайту выдается пара логин и пароль.
Чтобы зарегистрировать сайт в систему нужно отправить запрос:

`POST /registration`

C телом JSON объекта:

`{"site" : "job4j.ru"}`

Ответ от сервера:

`{`

         "registration": true/false,
         "login": УНИКАЛЬНЫЙ_КОД,
         "password": УНИКАЛЬНЫЙ_КОД
`}`

Флаг `registration` указывает, что регистрация выполнена или нет,
то есть сайт уже есть в системе.

### 2. Авторизация.

Авторизация через JWT (JSON Web Token). Пользователь отправляет POST запрос
с `login` и `password` и получает `ключ`.

Этот `ключ` пользователь отправляет в запросе в блоке `HEAD`:

`Authorization: Bearer e25d31c5-...`

### 3. Регистрация URL.

Поле того, как пользователь зарегистрировал свой сайт он может
отправлять на сайт ссылки и получать преобразованные ссылки.

Пример:

Отправляем URL:

`https://job4j.ru/profile/exercise/106/task-view/532`

Получаем:

`ZRUfdD2`

Ключ `ZRUfD2` ассоциирован с URL.

Вызовы:

`POST /convert`

C телом JSON объекта:

`{"url": "https://job4j.ru/profile/exercise/106/task-view/532"}`

Ответ от сервера:

`{"code": УНИКАЛЬНЫЙ_КОД}`

### 4. Переадресация. Выполняется без авторизации.

Когда сайт отправляет ссылку с кодом в ответ возвращается
ассоциированный адрес и статус 302.

Вызовы:

`GET /redirect/УНИКАЛЬНЫЙ_КОД`

Ответ от сервера в заголовке:

`HTTP CODE - 302 REDIRECT URL`

### 5. Статистика.

В сервисе считается количество вызовов каждого адреса.
По сайту можно получить статистку всех адресов и количество вызовов этого адреса.

Вызовы:

`GET /statistic`

Ответ от сервера JSON:

`{`

        {
             "url": URL,
             "total": 0
        },
        {
             "url": "https://job4j.ru/profile/exercise/106/task-view/532",
             "total": 103
        }

`}`

## Контакты:

Если у вас есть какие-либо вопросы, не стесняйтесь обращаться ко мне:

Евгений Зайцев

[cyberfuzzapps@gmail.com](mailto:cyberfuzzapps@gmail.com)
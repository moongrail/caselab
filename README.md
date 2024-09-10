### Как запустить.

1) mvn package
2) docker compose up -d

#### Примеры тестовых запросов.

1) Запись файла + информация.

   POST: localhost:8080/api/v1/files


      {
      "title": "File title",
      "creation_date": "2022-02-01 01:01:01",
      "description": "description",
      "content": "data:...."
       }

2) Получение файла + информация.
   
    GET: localhost:8080/api/v1/files/1


3) Получение отсортированного списка по времени DESC + пагинация.
   
    GET: localhost:8080/api/v1/files?from=0&size=10

### О приложении.
Микросервис общается по REST. Есть валидация приходящих значений на эндпоинты. 
Помимо требуемого, дополнительно написаны докер компоуз c бд, метод с пагинацией и сортировкой, 
тесты(их могло быть побольше проверка негативных сценариев и пагинации).

### Стек
- Java 17
- Spring Boot
- Spring Data Jpa
- Postgresql
- h2
- Lombok
- Spring Tests
- Spring Web
- Spring Validation
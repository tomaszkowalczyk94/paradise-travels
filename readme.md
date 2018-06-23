


#### logowanie
POST /login

request body:
```json
{
  "login":"admin",
  "password": "qwerty"
}
```

response:
```json
{
"result": true, 
"msg": "zalogowano"
}
```

#### rejestracja
@todo


#### lista userów
GET /users

#### konkretny user
GET /users/ID_USERA

#### usunięcie usera
DELETE /users/ID_USERA

#### update usera
PUT /users/ID_USERA

przykładowy request body, dla updatu wieku:
```json
{
  "age":22
}
```
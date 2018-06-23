
![alt text](https://raw.githubusercontent.com/tomaszkowalczyk94/paradise-travels/master/doc/schemat.jpg)


# autoryzacja & users

### logowanie
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

### logout
GET /logout
response:
```json
{
"result": true
}
```


### rejestracja

POST /register

Request body:
~~~json
{
  "user": {
    "firstName": "ImieXXX",
    "lastName": "NazwiskoXXX",
    "email": "malami@gmail.com",
    "login": "malami19",
    "password": "password"
  },
  "passwordRepeat": "password"
}
~~~
Response failure:
~~~json
{
"result": false,
"msg": "Podany użytkownik istnieje"
}
~~~

Response OK:
~~~json
{
"result": true,
"msg": "Rejestracja pomyślna"
}
~~~



### lista userów
GET /users

### konkretny user
GET /users/ID_USERA

### usunięcie usera
DELETE /users/ID_USERA

### update usera
PUT /users/ID_USERA

przykładowy request body, dla updatu wieku:
```json
{
  "age":22
}
```
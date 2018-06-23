
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

# oferta
### lista wszystkich ofert
GET /offers

response: 
```json
[
  {
    "id": 1,
    "hotel": null,
    "dateFrom": null,
    "dateTo": null,
    "name": "testowanazwa",
    "promoted": false,
    "description": null,
    "shortDescription": null
  },
  {
    "id": 2,
    "hotel": null,
    "dateFrom": 1335205543511,
    "dateTo": null,
    "name": "testowanazwa",
    "promoted": false,
    "description": null,
    "shortDescription": null
  },
  {
    "id": 3,
    "hotel": null,
    "dateFrom": null,
    "dateTo": null,
    "name": "wycieczka jeszcze bardziej AuuUAUUUUU",
    "promoted": false,
    "description": null,
    "shortDescription": null
  }
]
```


### dodanie oferty
POST /offers

przykładowy request body:
```json
{
  "name": "wycieczka AuuUAUUUUU",
  "dateFrom" : "2012-04-23T18:25:43.511Z",
  "dateTo" : "2013-04-23T18:25:43.511Z",
  "promoted": true,
  "shortDescription": "szmitek paradise",
  "description" : "tak fajna wycieczka że ja cie, auuuu"
}
```

response:
```json
{
    "id": 3,
    "hotel": null,
    "dateFrom": 1335205543511,
    "dateTo": 1366741543511,
    "name": "wycieczka AuuUAUUUUU",
    "promoted": true,
    "description": "tak fajna wycieczka że ja cie, auuuu",
    "shortDescription": "szmitek paradise"
}
```

### edycja oferty
PUT /offers/3

body request:
```json
{
  "name": "wycieczka jeszcze bardziej AuuUAUUUUU"
}
```

response:
```json
{
    "id": 3,
    "hotel": null,
    "dateFrom": null,
    "dateTo": null,
    "name": "wycieczka jeszcze bardziej AuuUAUUUUU",
    "promoted": false,
    "description": null,
    "shortDescription": null
}
```

### usunięcie oferty
DELETE /offers/3
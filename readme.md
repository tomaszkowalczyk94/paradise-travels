
![alt text](https://raw.githubusercontent.com/tomaszkowalczyk94/paradise-travels/master/doc/schemat.jpg)

Adres: http://77.55.193.96:8080/paradiseTravels

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

### aktualnie zalogowany users
GET /users/logged

response:
```json
{
    "id": 3,
    "firstName": "xxx",
    "lastName": "xxx",
    "address": null,
    "email": "xxx",
    "login": "admin",
    "role": null
}
```

Jeśli aktualnie nie jesteśmy zalogowani, serwer zwroci nam
http status 204 No Content


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
    "password": "password",
    "address": {
        "address" : "Warszawsa 5",
        "postalCode" : "25-000",
        "region": "swietokrzyskie",
        "country": "Polska"
    }
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

### sprawdzenie czy login istnieje
GET /users/is-exist/admin

response:
```json
{
    "value": true
}
```

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

# adress
* pobranie wszystkich: GET /addresses
* pobranie jednego: GET /addresses/<ID>
* dodanie nowego: POST /addresses
* edycja: PUT /addresses/<ID>
* usunięcie: DELETE /addresses/<ID>

# hotel
* pobranie wszystkich: GET /hotels
* pobranie jednego: GET /hotels/<ID>
* dodanie nowego: POST /hotels
* edycja: PUT /hotels/<ID>
* usunięcie: DELETE /hotels/<ID>

# wycieczki lokalne
* pobranie wszystkich: GET /local-journeys
* pobranie jednego: GET /local-journeys/<ID>
* dodanie nowego: POST /local-journeys
* edycja: PUT /local-journeys/<ID>
* usunięcie: DELETE /local-journeys/<ID>

# rezerwacje
* pobranie wszystkich: GET /reservations
* pobranie jednego: GET /reservations/<ID>
* dodanie nowego: POST /reservations
* edycja: PUT /reservations/<ID>
* usunięcie: DELETE /reservations/<ID>


# recenzje
* pobranie wszystkich: GET /reviews
* pobranie jednego: GET /reviews/<ID>
* dodanie nowego: POST /reviews
* edycja: PUT /reviews/<ID>
* usunięcie: DELETE /reviews/<ID>


# pokoje
* pobranie wszystkich: GET /rooms
* pobranie jednego: GET /rooms/<ID>
* dodanie nowego: POST /rooms
* edycja: PUT /rooms/<ID>
* usunięcie: DELETE /rooms/<ID>

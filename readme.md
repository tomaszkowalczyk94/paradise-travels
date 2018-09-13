

# instrukcja instalacji
Do zainstalowania aplikacji potrzebujemy
- ubuntu 18
- git
- maven
- baza danych postgre 
- java JRE
- server wildfly 13

1. instalacja JRE, git, maven
```bash
sudo apt install openjdk-8-jdk
sudo apt install maven
sudo apt install git
```


2. instalacja postgres

```bash
sudo apt update
sudo apt install postgresql postgresql-contrib

sudo -u postgres psql postgres
\password postgres
# teraz podajemy nowe hasło do bazy danych. Dla wersji devowej najlepiej podać "qwertyqwerty". Takie hasło jest ustawione w hibernacie i nie będziemy musieli nic konfigurować.
\q
```

3. server wildfly 13
```bash
cd ~
wget http://download.jboss.org/wildfly/13.0.0.Final/wildfly-13.0.0.Final.zip
unzip wildfly-13.0.0.Final.zip
cd wildfly-13.0.0.Final/bin
```

Tworzenie konta administracyjnego (do panelu admina wildfly-1)
```bash
./add-user.sh
``` 


Uruchamianie serwera:
```bash
./standalone.sh &
```
Serwer będzie dostępny pod adresem `http://localhost:8080/`



4. Instalacja i uruchamianie aplikacji:
```bash
git clone https://github.com/tomaszkowalczyk94/paradise-travels.git
cd paradise-travels/
mvn wildfly:deploy
```

W przeglądarce otwieramy strone  http://127.0.0.1:8080/paradiseTravels
Jeśli wszystko poprawnie skonfigurowaliśmy zobaczymy:
```json
{
    "message": "hello world",
    "hibernateSession": true,
    "countOfUsers": 0
}
```

5. Tworzenie konta admina do aplikacji

```bash
sudo -u postgres psql postgres
INSERT INTO "public"."users" ("id", "email", "firstname", "lastname", "login", "password", "role", "address_id") VALUES (DEFAULT, 'admin@admin.pl', 'admin', 'admin', 'admin', 'qwerty', '', NULL);
\q
```






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
"value": true
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
GET /users/login-is-exist/admin

response:
```json
{
    "value": true
}
```

### sprawdzenie czy email istnieje
GET /users/login-is-exist/test@test.pl

response:
```json
{
    "value": false
}
```

### lista userów
GET /users ( dziala tylko jesli zalogujemy sie jako admin)

### konkretny user
GET /users/ID_USERA

### usunięcie usera
DELETE /users/ID_USERA ( dziala tylko jesli zalogujemy sie jako admin)

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

### zakupienie oferty
POST /offers/reserveAndPay

body request:
```json
{
  "offerId" : IDOFERTY,
  "from" : "2018-08-09",
  "to" : "2018-08-09",
  "numberOfCustomers": 3,
  numberOfOnePersonBed: 1,
  numberOfTwoPersonBed: 2, 
  localJourneyIds: [22, 33]
}
```

response:
```json
{
  "value": "url do strony z płatnościami"
}
```


### filtry ofert
* GET /offers/search?dateFrom=2019-03-08&dateTo=2019-03-12&location=Greece&priceFrom=150&priceTo=1190

* format daty : rrrr-mm-dd

* przykład /offers/search?dateFrom=2019-03-08&dateTo=2019-03-12


# adress
* pobranie wszystkich: GET /addresses
* pobranie jednego: GET /addresses/ID
* dodanie nowego: POST /addresses
* edycja: PUT /addresses/ID
* usunięcie: DELETE /addresses/ID

# hotel
* pobranie wszystkich: GET /hotels
* pobranie jednego: GET /hotels/ID
* dodanie nowego: POST /hotels
* edycja: PUT /hotels/ID
* usunięcie: DELETE /hotels/ID

# wycieczki lokalne
* pobranie wszystkich: GET /local-journeys
* pobranie jednego: GET /local-journeys/ID
* dodanie nowego: POST /local-journeys
* edycja: PUT /local-journeys/ID
* usunięcie: DELETE /local-journeys/ID

# rezerwacje
* pobranie wszystkich: GET /reservations
* pobranie jednego: GET /reservations/ID
* dodanie nowego: POST /reservations
* edycja: PUT /reservations/ID
* usunięcie: DELETE /reservations/ID


# recenzje
* pobranie wszystkich: GET /reviews
* pobranie jednego: GET /reviews/ID
* dodanie nowego: POST /reviews
* edycja: PUT /reviews/ID
* usunięcie: DELETE /reviews/ID


# pokoje
* pobranie wszystkich: GET /rooms
* pobranie jednego: GET /rooms/ID
* dodanie nowego: POST /rooms
* edycja: PUT /rooms/ID
* usunięcie: DELETE /rooms/ID

# faktury

* pobranie wszystkich: GET /invoices
* pobranie jednego: GET /invoices/ID
* dodanie nowego: POST /invoices
* edycja: PUT /invoices/ID
* usunięcie: DELETE /invoices/ID
* generowanie pdf po id faktury /invoices/{id}/pdf
* generowanie pdf po id rezerwacji /invoices/reservation/{id}/pdf

# platnosci

*  POST offers/buy robi rezerwacje ( najlepiej zmienić pozniej na /reserve)
*  POST offers/reserveAndPay robi rezerwacje i inicjuje platnosc payU
 jesli uda sie nawizac polaczeni z payU zwraca  jsona z redirect url na ktory trzeba przekierować uzytkownika:
 ```json
 {
  "redirectUri": "https://merch-prod.snd.payu.com/pay/?orderId=SR3MHL4QPL180909GUEST000P01&token=eyJhbGciOiJIUzI1NiJ9.eyJvcmRlcklkIjoiU1IzTUhMNFFQTDE4MDkwOUdVRVNUMDAwUDAxIiwicG9zSWQiOiJsd2plVWp2SSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQ0xJRU5UIl0sImV4cCI6MTUzNjUzNTQ0OSwiaXNzIjoiUEFZVSIsImF1ZCI6ImFwaS1nYXRld2F5Iiwic3ViIjoiUGF5VSBzdWJqZWN0IiwianRpIjoiMDgwZmFjZWMtNjIyMy00MmVmLWEwMzctMDllYjgxMzVkMTdiIn0.2EB3W1hkx9KZONoxIhIiRiaU2EWMWmmUgquOQOFrKgg",
  "orderId": "SR3MHL4QPL180909GUEST000P01",
  "status": {
  "statusCode": "SUCCESS"
  }
  }
 ```
 Status rezerwacji ustawiany jest na PAYMENT_PROCESSED.
 
 payU natomiast o transakcji powiadomi endpoint reservations/notify ktory ustawi rezerwacje na PAID razie powodzenia. 
 
 * GET offers/test  - testuje payU,

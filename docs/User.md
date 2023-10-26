# User Specification

## Register USER
Endpoint : POST /api/users

Request Body : 
```json 
{
  "username" : "esa",
  "email" : "esa@example.com" ,
  "password" : "esa123"
}
```
Response Body : 
```json
{
  "data" : "accepted"
}
```
Response Body (Failed) :
````json
{
  "errors" : "Lengkapi form dengan benar"
}
````

## Login User
Endpoint: POST /api/auth/login

Request Body : 
```json
{
  "username" : "esa" , 
  "password" : "esa123"
}
```

Response Body :

```json
{
  "data": {
    "token": "token",
    "expiredAt": 12121212122
  }
}
```
Response Body Failed :
```json
{
  "errors" : "Username atau password salah"
}
```

## Get User
Endpoint : GET /api/users/current

Request Header : 
- X-API-TOKEN : TOKEN (MANDATORY)

Response Body (Success):
```json
{
  "data": {
    "username": "esa",
    "email": "esa@gmail.com"
  }
}
```

Response Body (Failed , 401) :
```json
{
  "errors" : "Unauthorized"
}
```

## Update User
Endpoint: Patch

Request Body :
```json
{
  "username": " new esa" , // put if only want to update 
  "password" : "new esa123" , // put if only want to update
  "email" : " new esa@example.com" // put if only want to update
}
```
Response Body (Success) :

```json
{
  "data": {
    "username": " new esa" , 
    "password" : "new esa123" , 
    "email" : " new esa@example.com" 
  }
}
```
Response Body (Failed , 401) : 
```json
{
  "errors" : "Unauthorized"
}
```

## Logout User
Endpoint : DELETE /api/auth/logout

Request Header : 
- X-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) :

```json
{
  "data": "success"
}
```
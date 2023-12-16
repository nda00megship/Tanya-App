# Kredensial API Spec
## Tambah Kredensial
Endpoint : POST /api/kredensial

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Request BODY :
```json
{
  "pekerjaan" : "programmer" , // satu persatu
  "pendidikan" : "Institut Fukuoka", // satu persatu
  "lokasi" : "surabaya", // satu persatu
  "tanggal" : "23-07-2023"
}
```

Response BODY (Success) :

```json
{
  "data": {
    "id": "random String",
    "pekerjaan": "programmer",
    "lokasi" : "surabaya",
    "pendidikan": "Institut Fukuoka",
    "tanggal": "23-07-2023"
  }
}
```
Response BODY (Failed) : 
```json
{
  "errors" : "Form must be filled"
}
```

## Get Kredensial
Endpoint : GET /api/user

Request Header : 
- X-API-TOKEN : TOKEN (MANDATORY)

Response BODY (Success) :
```json
{
  "data" : "accept"
}
```

## Update Kredensial
Endpoint : PATCH /api/kredensial/{idKredensial}

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Request BODY :
```json
{
  "pekerjaan" : "new programmer" ,
  "pendidikan" : "new Institut Fukuoka",
  "lokasi" : "surabaya",
  "tanggal" : "new 23-07-2023"
}
```

Response BODY (Success):

```json
{
  "data": {
    "pekerjaan" : "new programmer" ,
    "pendidikan" : "new Institut Fukuoka",
    "lokasi" : "surabaya",
    "tanggal" : "new 23-07-2023"
  }
}
```

Response BODY (Failed) :

```json
{
  "errors": "Unauthorized"
}
```

## Delete Kredensial
Endpoint : DELETE /api/kredensial/{idKredensial}

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response BODY (Success) :
```json
{
  "data" : "accept"
}
```

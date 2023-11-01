# Pertanyaan API SPEC

## Tambah pertanyaan
Endpoint : POST /api/pertanyaan

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Request BODY : 
```json
{
  "Header" : "Apa itu programming",
  "deskripsi" : "mungkin aku baru terjun ke dunia ini",
}
```

Response BODY (Success) :

```json
{
  "data": {
    "id" : "random String",
    "Header" : "Apa itu programming",
    "deskripsi" : "mungkin aku baru terjun ke dunia ini",
    "like" : 0 
  }
}
```

Response BODY (Failed) :
```json
{
  "errors" : "Format invalid"
}
```

## Get Pertanyaan
Endpoint : GET /api/pertanyaan/{idPertanyaan}

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response Body (Success) :

```json
{
  "data": {
    "id" : "random String",
    "Header" : "Apa itu programming",
    "deskripsi" : "mungkin aku baru terjun ke dunia ini",
    "like" : 0 
  }
}
```

Response Body (Failed , 404) :
```json
{
  "errors" : "pertanyaan is not found"
}
```

## Update Pertanyaan 
Endpoint : PUT /api/pertanyaan/{idPertanyaan}

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Request BODY :
```json
{
  "Header" : "Apa itu programming",
  "deskripsi" : "mungkin aku baru terjun ke dunia ini",
}
```

Response Body (Success) :

```json
{
  "data": {
    "id" : "random String",
    "Header" : "Apa itu programming",
    "deskripsi" : "mungkin aku baru terjun ke dunia ini",
    "like" : 0 
  }
}
```

Response BODY (Failed) :
```json
{
  "errors" : "Format invalid"
}
```

## Remove Pertanyaan
Endpoint : DELETE /api/pertanyaan/{idPertanyaan}

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response Body (Success) :

```json
{
  "data": "accept"
}
```
Response Body (Failed) :
```json
{
  "errors" : "pertanyaan is not found"
}
```


## Search Pertanyaan
Endpoint : GET /api/pertanyaan

Query Param :
 - header : String, pertanyaan header, using like query, optional
 - deskripsi : String , pertanyaan deskripsi, using like query, optional 
 - like : Integer Using like query 
 - komentar : Integer Using like query 
 - page : Integer, start from 0
 - size : Integer , default 0

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response Body (Success) :

```json
{
  "data": {
    "id": "random String",
    "Header": "Apa itu programming",
    "deskripsi": "mungkin aku baru terjun ke dunia ini",
    "like": 2,
    "komentar": 3
  },
  "paging": {
    "currentPage": 0,
    "totalPage" : 10 , 
    "size" : 10
  }
}
```

Response Body (Failed) :

```json
{
  "data": "Unauthorized"
}
```
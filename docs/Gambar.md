# Gambar API Spec

## tambah gambar

Endpoint : POST /api/gambar

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Request Body :
```json
{
  "gambar" : "nama gambar",
  "path" : "/storage/",
  "ext" : "png",
  "tanggal" : "12/30/2023 19:00:12"
}
```
Response Body (Success) :

```json
{
  "data": {
    "gambar" : "nama_gambar",
    "path" : "/storage/",
    "ext" : "png",
    "tanggal" : "12/30/2023 19:00:12"
  }
}
```
Response Body (Failed) :

```json
{
  "errors":"Format invalid"
}
```

## Get gambar
Endpoint : /api/gambar/{idGambar}

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response Body (Success) :
```json
{
  "data": {
    "gambar" : "nama_gambar",
    "path" : "/storage/",
    "ext" : "png",
    "tanggal" : "12/30/2023 19:00:12"
  }
}
```

Response Body (Failed) :

```json
{
  "errors":"NOT FOUND"
}
```

## delete gambar

Endpoint : /api/gambar/{idGambar}

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response Body (Success) :

```json
{
  "data": "data sudah terhapus"
}
```

Response Body (Failed) :

```json
{
  "errors": "format tidak valid"
}
```

# Komentar API Spec

## tambah komentar

Endpoint : POST /api/komentar

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Request Body :
```json
{
  "deskripsi" : "ini adalah isi komentar"
}
```

Response Body (Success) :
```json
{
  "data" : "accept"
}
```

Response Body (Failed) :
```json
{
  "errors" : "lengkapi form dengan benar"
}
```

## Delete komentar

Endpoint : DELETE /api/komentar/{idKomentar}

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response Body (Success) :
```json
{
  "data" : "terhapus"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Komentar tidak diketahui"
}
```
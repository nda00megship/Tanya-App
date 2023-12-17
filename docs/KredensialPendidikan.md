# Kredensial Pendidikan API Spec
## Tambah Kredensial Pendidikan
Endpoint : POST /api/pendidikan

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Request BODY :
```json
{
  "posisi" : "general manager",
  "perusahaan" : "MTA CO",
  "tahunMulai" : "2018-01-01",
  "tahunSelesai": "2025-01-01"
}
```

Response BODY (Success) :

```json
{
  "data": {
      "posisi" : "general manager",
      "perusahaan" : "MTA CO",
      "tahunMulai" : "2018-01-01",
      "tahunSelesai": "2025-01-01"
  }
}
```
Response BODY (Failed) :
```json
{
  "errors" : "Form must be filled"
}
```

## Get Kredensial Pendidikan
Endpoint : GET /api/pendidikan/{idKredPendidikan}

RRequest PATH Variable :
- idKredensialPendidikan

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response BODY (Success) :
```json
{
  "data": {
    "posisi" : "general manager",
    "perusahaan" : "MTA CO",
    "tahunMulai" : "2018-01-01",
    "tahunSelesai": "2025-01-01"
  }
}
```

## Update Kredensial
Endpoint : PUT /api/pendidikan/{idKredensialPendidikan}

Request PATH Variable :
- idKredensialPendidikan

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Request BODY :
```json
{
  "data": {
    "posisi" : "general manager",
    "perusahaan" : "MTA CO",
    "tahunMulai" : "2018-01-01",
    "tahunSelesai": "2025-01-01"
  }
}
```

Response BODY (Success):

```json
{
  "data": {
    "posisi" : "new general manager",
    "perusahaan" : "new MTA CO",
    "tahunMulai" : "new 2018-01-01",
    "tahunSelesai": "new 2025-01-01"
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
Endpoint : DELETE /api/kredensial/{idKredensialPendidikan}

Request PATH Variable :
- idKredensialPendidikan

Request Header :
- X-API-TOKEN : TOKEN (MANDATORY)

Response BODY (Success) :
```json
{
  "data" : "accept"
}
```

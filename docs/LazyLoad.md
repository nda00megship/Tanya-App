# Lazy Load Page
## Table pertanyaan
Endpoint : GET /api/pertanyaan

Contoh request URI : /api/pertanyaan?page=1&size=10

Query Param :
- page : Integer , start from 0 // paging ketika kirim page
- size : Integer , default 10 // ukuran per page

Request Header :
- X-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "id": "Encrypt String",
      "header" : "apakah pertanyaan 1 , ini adalah header",
      "deskripsi": "Pertanyaan 1 , ini adalah deskripsi"
    },
    {
      "id": "Encrypt String",
      "header" : "apakah pertanyaan 1 , ini adalah header",
      "deskripsi": "Pertanyaan 2 adalah deskripsi"
    }
    // lanjut
  ],
  // Ini adalah nomor halaman yang diminta oleh pengguna.
  "page": 1,
  //  jumlah elemen (item) yang akan ditampilkan dalam satu halaman.
  "size": 4, 
  "totalElements": 100,
  // Jumlah total elemen
  "totalPages": 10
  // Jumlah total halaman
}
```

Response Body (Failed) : 
```json
{
  "error": "Unauthorized"
}
```
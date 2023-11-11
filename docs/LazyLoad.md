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
      "id": "random String",
      "Header": "Apa itu programming",
      "deskripsi": "mungkin aku baru terjun ke dunia ini",
      "tanggal" : "12/30/2023 19:00:12",
      "gambar" : [
        "gambar1" , "gambar2"
      ],
      "komentar": [
        {
          "idKomentar1" : "ini ada komentar 1",
          "idKomentar2" : "ini ada komentar 2",
          "idKomentar3" : "ini ada komentar 3"
        }
      ]
    },
    {
      "id": "random String",
      "Header": "Apa itu programming",
      "deskripsi": "mungkin aku baru terjun ke dunia ini",
      "tanggal" : "12/30/2023 19:00:12",
      "gambar" : [
        "gambar1" , "gambar2"
      ],
      "komentar": [
        {
          "idKomentar1" : "ini ada komentar 1",
          "idKomentar2" : "ini ada komentar 2",
          "idKomentar3" : "ini ada komentar 3"
        }
      ]
      }
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
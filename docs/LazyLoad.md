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
      "id": "pertanyaan_1",
      "Header": "Apa itu programming",
      "deskripsi": "mungkin aku baru terjun ke dunia ini",
      "tanggal" : "1 menit yang lalu",
      "gambar" : [
        "gambar1" , "gambar2"
      ],
      "totalKomentar" : 2,
      "komentar": [
        {
          "idKomentar1" : "komentar_1",
          "nama" :  "Maulana",
          "deskripsi": "ini ada komentar ke sekian"
        } ,
        {
          "idKomentar1" : "komentar_2",
          "nama" :  "Akbar",
          "deskripsi": "ini ada komentar ke sekian"
        }
      ]
    },
    {
      "id": "pertanyaan_1",
      "Header": "Apa itu programming",
      "deskripsi": "mungkin aku baru terjun ke dunia ini",
      "tanggal" : "2 menit yang lalu",
      "gambar" : [
        "gambar1" , "gambar2"
      ],
      "totalKomentar" : 2,
      "komentar": [
        {
          "idKomentar1" : "komentar_1",
          "nama" :  "Maulana",
          "deskripsi": "ini ada komentar ke sekian"
        } ,
        {
          "idKomentar1" : "komentar_2",
          "nama" :  "Akbar",
          "deskripsi": "ini ada komentar ke sekian"
        }
      ]
    },
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
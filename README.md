# responsi PBO nim 123240024
---
## TW btw mas di pc ini maven nya error udah ditambahin dependencies di pom nya gabisa jadi semoga aja jalan yh programnya aamiin
### cara running program
1. pastikan sudah konek xampp
2. buat database dengan nama cart_db
3. download zip folder diatas dan extract file
4. buka IDE netbeans
5. buka project responsi yang sudah di extract
6. running program berhasil, silahkan mencoba program

### yang dilakukan selama responsi
1. menggunakan pola arsitektur MVC
2. membuat COnnectionDb sebagai koneksi dari file ke database supaya file bisa menyimpan data
3. membuat class CartRepositoryMySQL di model untuk menggantikan fake repository (data lokal)
4. membuat class Event1212 untuk menhitung disscount (implementasi dari discountStrategy)
5. membuat class CartService untuk menghitung sub dan total discount supaya controller dan view tidak perlu tau rumus discount nya
6. membuat package controller dan class CartController untuk handling view nya
7. menghubungkan komponen baru di main (Responsi.java)


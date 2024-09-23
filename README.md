# Nijat Bank Layihəsi

Bu layihə, bank sistemləri üçün Java və Spring framework ilə yaradılmışdır.

## Məlumatlar

### User Table

- `id`: İstifadəçi id-si, integer.
- `name`: İstifadəçinin adı, varchar.
- `surname`: İstifadəçinin soyadı, varchar.
- `age`: İstifadəçinin yaşı, integer.
- `birth_date`: İstifadəçinin doğum tarixi, date.
- `fin_code`: İstifadəçinin FİN kodu, varchar.

### Account Table

- `id`: Hesab id-si, integer.
- `acc_number`: Hesab nömrəsi, varchar.
- `currency`: Hesabın valyutası, varchar.
- `amount`: Hesabın məbləği, double.

### Payment Table

- `id`: Ödəniş id-si, integer.
- `total_amount`: Ödənilən məbləğ, double.
- `account_id`: Hesab id-si, integer.

## İstifadəçi və Hesab Əlaqəsi

- `User` və `Account` arasında One-to-Many əlaqəsi var. Bir istifadəçinin çoxlu hesabı ola bilər.

## Funksionallıqlar

### User Service

- `create`: Yeni istifadəçi əlavə etmək.
- `get`: İstifadəçi məlumatlarını oxumaq.
- `delete`: İstifadəçi silmək.

### Account Service

- `post`: Yeni hesab əlavə etmək.
- `get(optional)`: Hesab məlumatlarını oxumaq (optional).
- `post(payment)`: Ödəniş etmək.

### Payment Service

- `processPayment`: Ödənişi yerinə yetirmək.
- `getAllPayments`: Bütün ödənişlərin siyahısına baxmaq.
- `getPayment`: İd-ə uyğun ödənişi oxumaq.

#### Hesab Yaratma (post)

- Hesab yaradanda, `currency` və `userid`-dən istifadə edərək `acc_number` yaradılır.
- Bir müştərinin eyni valyutada birdən çox hesabı olmamalıdır.

#### Ödəniş Et (post(payment))

- Ödəniş zamanı, hansı kartdan çıxılacağı və hansı karta gələcəyi qeyd edilir.
- Ödənişdən əgər eyni valyutada hesablardan ödəniş edilsə, 0.1% komissiya tutulur.
- Fərqli valyutalar arasında ödəniş zamanı isə, 1% komissiya tutulur.
- Eyni istifadəçi arasında eyni valyutada hesablardan ödəniş zamanı, heç bir komissiya tutulmur.

## Quraşdırma və İstifadə

Layihəni yerli məşğuliyətə qurmaq üçün aşağıdakı addımları izləyin:

 ```bash
git clone https://github.com/nijataghayev/ms-nijat-bank.git
cd ms-nijat-bank
gradle build
java -jar build/libs/ms-nijat-bank.jar

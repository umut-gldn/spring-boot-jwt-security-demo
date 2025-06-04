# Spring Boot JWT Authentication Projesi
###  Kullanım Akışı

1. Kullanıcı `/register` ile kayıt olur
2. `/authenticate` ile giriş yapar ve iki token alır
3. Access token ile API'lere erişir
4. Access token süresi dolduğunda `/refreshToken` ile yeni token alır
5. Refresh token süresi dolduğunda tekrar giriş yapması gerekir

Bu proje, JWT tabanlı kimlik doğrulama sisteminin temel özelliklerini içeren, eğitim amaçlı geliştirilmiş bir örnek uygulamadır. Spring Security ve JWT kullanarak güvenli bir kimlik doğrulama sistemi oluşturulmuştur.
## 🔐 Özellikler ve Kullanım

### 1. Kimlik Doğrulama Sistemi

- JWT (JSON Web Token) tabanlı güvenlik sistemi
- Access Token ve Refresh Token mekanizması
- Şifreler BCrypt ile güvenli bir şekilde hashleniyor

### 2. API Endpoint'leri

#### Kullanıcı Kaydı

```http
POST /register
```

- Yeni kullanıcı oluşturma
- Gerekli alanlar:
  ```json
  {
    "username": "kullanici_adi",
    "password": "sifre"
  }
  ```

#### Kullanıcı Girişi

```http
POST /authenticate
```

- Kullanıcı girişi yapma
- Başarılı girişte iki token döner:
  - Access Token (2 saat geçerli)
  - Refresh Token (4 saat geçerli)
- Gerekli alanlar:
  ```json
  {
    "username": "kullanici_adi",
    "password": "sifre"
  }
  ```

#### Token Yenileme

```http
POST /refreshToken
```

- Access Token süresi dolduğunda yeni token alma
- Gerekli alan:
  ```json
  {
    "refreshToken": "refresh_token_degeri"
  }
  ```



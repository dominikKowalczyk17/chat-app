# Real-time Chat Application Backend

## Opis projektu
Backend aplikacji czatowej w czasie rzeczywistym, zbudowany przy użyciu Spring Boot. Umożliwia komunikację między użytkownikami, zarządzanie kontami oraz przechowywanie historii wiadomości.

## Technologie
- **Java 17**
- **Spring Boot**
- **Spring Security** (z JWT)
- **Spring Data JPA**
- **PostgreSQL**
- **WebSocket**
- **Maven**

## Wymagania systemowe
- Java 17 lub nowsza
- PostgreSQL
- Maven

## Konfiguracja lokalna

1. **Sklonuj repozytorium**:

  ```bash
  git clone <https://github.com/dominikKowalczyk17/chat-app.git>
  ```
2. **Skonfiguruj bazę danych w application.properties**:

  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/chat_db
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  ```
3. **Skonfiguruj klucz JWT w application.properties**:

  ```properties
  jwt.secret=your_secret_key
  jwt.expiration=3600000
  ```
4. **Zbuduj projekt**:

  ```bash
  mvn clean install
  ```

6. **Uruchom aplikację**:

  ```bash
  mvn spring-boot:run
  ```
## Endpointy API
### Autoryzacja
* POST `/api/auth/register` - Rejestracja nowego użytkownika
* POST `/api/auth/login` - Logowanie użytkownika
* POST `/api/auth/login` - Wylogowanie użytkownika

## Bezpieczeństwo

- **Autentykacja**: Za pomocą JSON Web Token (JWT).
- **Hashowanie haseł**: Realizowane z wykorzystaniem algorytmu BCrypt.
- **Walidacja danych wejściowych**: Mechanizmy sprawdzające poprawność danych od użytkownika.
- **CORS configuration**: Skonfigurowane w celu umożliwienia współpracy z frontendem.

## Development vs Production

- **Development**:
  - Używa protokołu HTTP.
  - Logowanie na poziomie `debug`.
  
- **Production**:
  - Wymusza protokół HTTPS.
  - Logowanie na poziomie `info`.

## Uruchamianie z różnymi profilami

```bash
# Development
./mvnw spring-boot:run -Dspring.profiles.active=dev

# Production
./mvnw spring-boot:run -Dspring.profiles.active=prod   

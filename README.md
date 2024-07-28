# 🏦 실전 배당금 프로젝트

> 크롤링을 통해 주식 데이터를 가져와 배당금을 알아보는 프로젝트입니다.

## 🛠️ Development Environment
- 🖥️ **Intellij IDEA Ultimate**
- ☕ **Java 17**
- 🔧 **Gradle 8.8**
- 🌱 **Spring Boot 3.3.2**

## 🧰 Tech Stack
- **Frameworks & Libraries**: Spring Boot, Spring Security, Spring Data JPA
- **Databases**: H2, Redis
- **Web Scraping**: Jsoup
- **Authentication**: JWT
- **Utilities**: Lombok, Sl4J, Logback, Swagger

## 📖 API Documentation 
### [Swagger로 API 테스트](http://localhost:8080/swagger-ui/index.html)


### 🔐 /auth
<details>
<summary>회원가입 API</summary>
  
- **POST** /signup
  - 중복 ID는 허용하지 않음
  - 패스워드는 암호화된 형태로 저장됨
</details>

<details>
<summary>로그인 API</summary>

- **POST** /signin
  - 로그인 API
  - 회원가입이 되어있고, 아이디/패스워드가 일치하는 경우 JWT 발급
</details>

### 🏢 /company
<details>
<summary>회사명 검색 API</summary>

- **GET** /autocomplete
  - 검색하고자 하는 prefix를 입력값으로 받고, 해당 prefix로 검색되는 회사명 리스트 중 10개 반환
  - `keyword` 파라미터로 배당금 이름을 검색하면 `{result:["O","OAS",...]}` 와 같이 해당 글이 들어간 배당금 키워드를 반환
</details>

<details>
<summary>회사 목록 확인 API</summary>

- **GET**
  - 서비스에서 관리하고 있는 모든 회사 목록을 반환
  - 반환 결과는 Page 인터페이스 형태
  - `{result : [{companyName: "좋은회사", ticker : "GOOD"}, {companyName:"a", ticker:"b"}, ...]}`
</details>

<details>
<summary>회사 정보 추가 API</summary>

- **POST**
  - 추가하고자 하는 회사의 `ticker`를 입력받아 해당 회사 정보를 스크래핑, 저장
  - 이미 보유하고 있는 회사의 경우 400 status 코드와 에러메세지 반환
  - 존재하지 않는 회사 `ticker`일 경우 400 status 코드와 에러메세지 반환
  - `{ticker : "GOOD"}` ticker 파라미터로 받아주세요
  - DB에 `{ticker : "GOOD", companyName : "좋은회사"}` 이렇게 저장합니다
</details>

<details>
<summary>회사 정보 삭제 API</summary>

- **DELETE** /{ticker}
  - `ticker`에 해당하는 회사 정보 삭제
  - 삭제 시 회사의 배당금 정보와 캐시도 모두 삭제
</details>

### 💰 /finance
<details>
<summary>배당금 정보 확인 API</summary>

- **GET** /dividend/{companyName}
  - 회사명을 받아 회사 메타 정보와 배당금 정보를 반환
  - 잘못된 회사명이 입력으로 들어온 경우 400 status 코드와 에러메세지 반환
  - `{companyName : "좋은회사", dividend :[{date:"2023.10.29", price:"2.00", ...}]}`
</details>

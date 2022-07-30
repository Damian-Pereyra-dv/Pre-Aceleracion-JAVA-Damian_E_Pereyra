# disney-challenge-Alkemy
Pre-aceleración-Java-Challenge

### Objetive


>The objective of this challenge is to develop an API using Java SpringBoot and SpringSecurity,
following the REST pattern, that allows to know and modify the catalog of characters, movies and genres of Disney movies.
### Requirements

- Java 11.

- Maven 3.

- MySQL/ DBeaver.

- SpringBoot.



***

### Setup Steps


**1. Clone repository.**
```bash
  git clone https://github.com/Damian-Pereyra-dv/Pre-Aceleracion-JAVA-Damian_E_Pereyra.git
```

**2. Create DataBase in MySQL**

```mysql
    CREATE DATABASE disney;
```

**3. Change MySQL server username and password in application.properties file.**

+ Open `Pre-Aceleracion-JAVA-Damian_E_Pereyra\src\main\resources\application.properties`


+ Change the username and password as you have configured your MySQL
    + `spring.datasource.username=root`
    + `spring.datasource.password=root`

**4. Setup Environment Variables for SendGrid email service.**

+ Create an account on https://app.sendgrid.com and get your API Key.

+ Create an EMAIL_API_KEY in your IDEs environment variables.

+ Input your SendGrid API KEY:
  > EMAIL_API_KEY=yourkey

+ Open `Pre-Aceleracion-JAVA-Damian_E_Pereyra\src\main\resources\application.properties` file
+ Change to your SendGrid registered email in `alkemy.disney.disney.email.sender=yourmail@mail.com `

**5. Build and Run.**

The app will run at: http://localhost:8080/

***

**Postman Workspace:**
> `Pre-Aceleracion-JAVA-Damian_E_Pereyra\doc\postman`
or

> https://www.postman.com/bold-equinox-852207/workspace/damian-pereyra-workspace/collection/20774953-eb16d0cb-0d08-47f1-b1b6-9d6dcc6487ca?ctx=documentation
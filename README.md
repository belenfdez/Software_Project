# API & UI Testing Automation

Testing automation project for university course. Includes API tests and UI tests with Allure reporting.

## What you need

- Java 21
- Gradle 8.0+
- Firefox
- Git

## How to run

### First time setup

```bash
git clone https://github.com/belenfdez/Software_Project.git
cd Software_Project/api-testing-demo-java_starter
./gradlew clean build -x test
```

### Run the tests

```bash
# All tests
./gradlew test

# Only API tests
./gradlew test -Ptag=api

# Only UI tests  
./gradlew test -Ptag=ui
```

### View the report

```bash
allure serve build/allure-results/
```

## What's tested

**API tests:** 17 tests against electronics-store backend (http://localhost:8080/api)
- Basic GET requests
- CRUD operations on orders
- Error handling
- End-to-end flows

**UI tests:** 9 tests on https://www.saucedemo.com
- Login functionality
- Shopping flows
- Cart operations

## Project structure

```
src/test/java/com/testautomation/
├── config/          
├── helpers/         
├── pojos/           
├── services/        
├── tests/           
└── ui/pages/        
```

## Configuration

API configuration is in `src/test/resources/env/dev.properties`

```properties
baseUri=http://localhost:8080/api
connectTimeout=5000
readTimeout=5000
```

## Running on Jenkins

- Create a Pipeline job pointing to this repo
- Script path: `Jenkinsfile`
- Builds, runs tests, generates Allure report

## Notes

- Backend must be running for API tests: `java -jar electronics-store-0.0.1-SNAPSHOT.jar`
- UI tests use Firefox in headless mode
- Allure generates HTML reports in `build/allure-results/`

---

**Belén Fernández** -   Software Project
https://github.com/belenfdez/Software_Project


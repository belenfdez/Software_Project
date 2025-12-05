# API & UI Testing Automation Suite

Proyecto de automatización de testing completo con cobertura API y UI, reportes Allure e integración Jenkins CI/CD.

**Status:** ✅ 22 tests pasando (17 API + 9 UI - 4 skipped) | Allure reporting activo

---

## 📋 Requisitos

- **Java 21+** (JDK)
- **Gradle 8.0+** (incluido como wrapper)
- **Firefox** (para tests UI con Selenium)
- **Git** (para control de versiones)
- **Jenkins** (opcional, para CI/CD)

### Verificar requisitos

```bash
java -version          # Debe ser Java 21
./gradlew -version    # Verifica Gradle
firefox --version     # Verifica Firefox
```

---

## 🚀 Inicio Rápido

### 1. Clonar repositorio

```bash
git clone https://github.com/belenfdez/Software_Project.git
cd Software_Project/api-testing-demo-java_starter
```

### 2. Compilar proyecto

```bash
./gradlew clean build -x test
```

### 3. Ejecutar tests

```bash
# Todos los tests
./gradlew test

# Solo API tests
./gradlew test -Ptag=api

# Solo UI tests
./gradlew test -Ptag=ui
```

### 4. Ver reportes Allure

```bash
allure serve build/allure-results/
```

Se abrirá en http://localhost:4040 automáticamente.

---

## 📁 Estructura del Proyecto

```
src/test/java/com/testautomation/
├── config/
│   └── Config.java                 # Gestión centralizada de configuración
├── helpers/
│   ├── RequestSpecFactory.java     # Especificación REST con Allure
│   ├── DriverFactory.java          # Gestor de WebDriver Firefox
│   └── AllureTestWatcher.java      # Captura de screenshots en fallos
├── pojos/
│   └── *.java                      # Modelos de datos (User, Post, etc.)
├── services/
│   └── ProductService.java         # Orquestación de llamadas API
├── ui/
│   └── pages/
│       ├── BasePage.java           # Clase base con helpers Selenium
│       ├── LoginPage.java          # POM para página de login
│       ├── InventoryPage.java      # POM para inventario
│       ├── CartPage.java           # POM para carrito
│       └── CheckoutPage.java       # POM para checkout
└── tests/
    ├── BasicApiTest.java           # Tests GET básicos
    ├── CrudApiTest.java            # Tests CRUD completos
    ├── NegativeApiTest.java        # Tests de casos negativos
    ├── E2EApiTest.java             # Tests end-to-end API
    └── ui/
        ├── SauceDemoLoginUITest.java      # Tests login UI
        └── SauceDemoE2ETest.java          # Tests E2E UI (purchase flow)
```

---

## 🧪 Cobertura de Tests

### Tests API (17 tests - JSONPlaceholder)

| Suite | Tests | Descripción |
|-------|-------|-------------|
| **BasicApiTest** | 3 | GET list, GET by ID, 404 handling |
| **CrudApiTest** | 6 | Create, Read, Update, Delete, Filter |
| **NegativeApiTest** | 5 | 404s, invalid payloads, malformed JSON |
| **E2EApiTest** | 3 | User-posts flow, complete CRUD chain |

**Base URL:** https://jsonplaceholder.typicode.com

### Tests UI (9 tests - SauceDemo)

| Suite | Tests | Descripción |
|-------|-------|-------------|
| **SauceDemoLoginUITest** | 6 | Login válido/inválido, credenciales vacías |
| **SauceDemoE2ETest** | 3 | Flujo completo compra, múltiples productos |

**Base URL:** https://www.saucedemo.com

---

## 🔧 Configuración

### Cambiar entorno

Editar `src/test/resources/env/dev.properties` o `qa.properties`:

```properties
baseUri=https://jsonplaceholder.typicode.com
connectTimeout=5000
readTimeout=5000
```

Ejecutar con env específico:

```bash
./gradlew test -Denv=qa
```

---

## 📊 Allure Reporting

### Características

- ✅ Captura de pantallas en fallos UI
- ✅ Logs de requests/responses API
- ✅ Pasos detallados (@Step annotations)
- ✅ Categorización por tags (API/UI)
- ✅ Timeline de ejecución

### Generar reporte

```bash
./gradlew test
allure serve build/allure-results/
```

### Limpiar reportes anteriores

```bash
./gradlew clean
```

---

## 🔌 Integración Jenkins

### Requisitos Jenkins

1. Instalar plugins:
   - Allure Plugin
   - JUnit Plugin
   - Pipeline

2. Crear pipeline declarativo:
   - Source: GitHub (Software_Project)
   - Script path: `Jenkinsfile`

### Ejecutar pipeline

```groovy
// En Jenkins UI:
// 1. New Job → Pipeline
// 2. Pipeline → Pipeline script from SCM
// 3. Git → https://github.com/belenfdez/Software_Project.git
// 4. Build
```

### Acceso a reportes Jenkins

- Test Results: `Job → Test Result Trend`
- Allure Report: `Job → Allure Report`

---

## 📝 Casos de Uso

### API Testing (JSONPlaceholder)

```bash
# Verificar todos los posts
./gradlew test -Ptag=api

# Flujo esperado: GET /posts → POST /posts → PUT /posts/1 → DELETE /posts/1
```

### UI Testing (SauceDemo)

```bash
# Verificar flujo completo de compra
./gradlew test -Ptag=ui

# Credenciales de prueba:
# - Usuario: standard_user
# - Contraseña: secret_sauce
```

---

## 🛠️ Troubleshooting

### Tests UI lentos

Firefox se ejecuta en modo headless (sin interfaz gráfica). Para debug:
- Editar `DriverFactory.java` y comentar `options.setHeadless(true);`

### Allure report no genera

```bash
# Limpiar caché y regenerar
./gradlew clean test
allure generate build/allure-results/ -o build/allure-report/
allure open build/allure-report/
```

### Conflicto de puertos Jenkins

Por defecto Jenkins corre en puerto 8080. Si está ocupado:

```bash
sudo systemctl stop jenkins
# O ejecutar en puerto diferente:
java -jar jenkins.war --httpPort=8888
```

---

## 📈 Métricas

| Métrica | Valor |
|---------|-------|
| Tests totales | 22 |
| Tasa de cobertura | ~80% código crítico |
| Tiempo ejecución | ~45 segundos |
| Broken links | 0 |

---

## 🔄 Git Workflow

```bash
# Crear rama feature
git checkout -b feature/new-test-suite

# Hacer cambios y commit
git add .
git commit -m "feat: add new test suite"

# Push a GitHub
git push origin feature/new-test-suite

# Crear Pull Request en GitHub UI
```

---

## 📚 Tecnologías

| Stack | Herramienta | Versión |
|-------|------------|---------|
| **Java** | OpenJDK | 21+ |
| **Build** | Gradle | 8.14 |
| **Test Framework** | JUnit 5 | 5.11.3 |
| **API Testing** | RestAssured | 5.5.0 |
| **UI Testing** | Selenium | 4.25.0 |
| **WebDriver** | WebDriverManager | 5.9.2 |
| **Reporting** | Allure | 2.29.0 |
| **Assertions** | AssertJ | 3.26.3 |
| **Mocking** | Mockito | 5.14.2 |
| **Logging** | SLF4J | 2.0.16 |

---

## 👤 Autor

**Belén Fernández**  
Proyecto ERASMUS - Software Testing Automation  
[GitHub](https://github.com/belenfdez/Software_Project)

---

## 📄 Licencia

Proyecto académico - 2024

---

## 🎯 Roadmap Futuro

- [ ] Integración con base de datos real
- [ ] Tests de performance (JMeter)
- [ ] Cobertura de código (JaCoCo)
- [ ] Docker integration
- [ ] API mocking con Wiremock
- [ ] Mobile testing (Appium)

# SPRING BOOT + SQL SERVER (DOCKER)

## 💻 STACK TECNOLÓGICO
- Java: JDK 25
- IDE: IntelliJ IDEA | Visual Studio Code | Codespace
- Maven: Apache Maven
- Frameworks: Spring Boot
- Instalar la extensión en Visual de: `Extension Pack for Java`
- Instalar la extensión en Visual de: `Database Client`

## 📁 Estructura de Carpetas

```text
🍃 PROJECT/
├── 📁 .github/
├── 📁 .idea/
├── 📁 .mvn/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/pe/edu/vallegrande/app
│   │   │   ├── 📁 model/
│   │   │   ├── 📁 repository/
│   │   │   ├── 📁 rest/
│   │   │   └── 📁 service/
│   │   ├── 📄 AppApplication.java
│   │   └── 📁 resources/
│   │       ├── 📁 sql/db.sql
│   │       └── 📄 application.yml
│   └── 📁 test/
├── 📁 target/
├── 📄 .gitattributes
├── 📄 .gitignore
├── 📄 mvnw
├── 📄 mvnw.cmd
├── 📄 pom.xml
└── 📄 README.md
````

---

## 🧪 Ejecución del Proyecto Spring Boot


* **Opción 1:** `ProjectApplication.java`, archivo principal para ejecutar e iniciar el proyecto en la ruta:

```bash
🍃 PROJECT/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/pe/edu/vallegrande/project
│   │   │   ├── 📁 model/
│   │   │   ├── 📁 repository/
│   │   │   ├── 📁 rest/
│   │   │   └── 📁 service/
│   │   ├── 📄 ProjectApplication.java
````

* **Opción 2:** a nivel de terminal con Maven, ubicandose dentro del directorio del proyecto:

```bash
mvn spring-boot:run
````

# Endpoint del proyecto Spring Boot

__[http://localhost:8085/v1/api/customer](http://localhost:8085/v1/api/customer)__

__[http://localhost:8085/swagger-ui.html](http://localhost:8085/swagger-ui.html)__


* **Resultado:**
```bash
[
  {
    "id": 1,
    "dni": "74564578",
    "cellPhone": "987654321",
    "firstName": "Juan",
    "lastName": "Sanchez",
    "state": "A",
    "createdAt": "21-ago.-2026 17:24:01",
    "updatedAt": null,
    "deletedAt": null,
    "restoredAt": null
  }
]
````

---

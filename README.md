# 🦫 Castores Inventario

> Sistema de gestión de inventario.

---

## 🎬 Demo en video

📹 [Ver demostración completa del sistema](https://drive.google.com/drive/folders/1SIIgH-sI9zzPF55tXPzof4Tbh2sCgFBo?usp=sharing)

---

## 🛠️ Stack Tecnológico

### 🖥️ IDE
| Herramienta | Detalle |
|-------------|---------|
| **Visual Studio Code** | Editor principal utilizado durante el desarrollo |

### ☕ Backend

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Java** | `21` | Lenguaje de programación principal.|
| **Spring Boot** | `3.3.0` | Framework principal |
| **Spring Security** | `6.x` | Autenticación de usuarios y control de acceso por roles |
| **Spring Data JPA** | `3.3.0` | Simplifica el acceso a la base de datos con repositorios automáticos |
| **Hibernate** | `6.x` | ORM que mapea las entidades Java a tablas SQL |
| **Lombok** | `1.18+` | Librería que genera getters, setters, constructores mediante anotaciones |

### 🎨 Frontend

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Thymeleaf** | `3.x` | Motor de plantillas del lado del servidor |
| **Bootstrap** | `5.3` | Framework CSS  |

### 🗄️ Base de datos

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Microsoft SQL Server** | `2019+` | Sistema gestor de base de datos relacional |
| **SSMS** | `21.4.8+5.36301.6` | SQL Server Management Studio |

### 🔧 Herramientas de construcción

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Apache Maven** | `3.9+` | Gestor de dependencias y herramienta de construcción del proyecto |

---

## 📋 Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- ✅ **Java 21** — [Descargar aquí](https://www.oracle.com/java/technologies/downloads/#java21)
- ✅ **Apache Maven** — [Descargar aquí](https://maven.apache.org/download.cgi)
- ✅ **SQL Server** con SSMS `21.4.8+5.36301.6`
- ✅ **Driver de autenticación de SQL Server** (ver paso 1)

---

## 🚀 Pasos para ejecutar la aplicación

### Paso 1 — Instalar el driver de autenticación de SQL Server

Copia el archivo `mssql-jdbc_auth-12.6.1.x64.dll` (disponible en la carpeta `Recursos/` del repositorio) a la siguiente ruta del sistema:

```
C:\Windows\System32\mssql-jdbc_auth-12.6.1.x64.dll
```

> ⚠️ Este paso es **obligatorio** para que Spring Boot pueda conectarse a SQL Server con autenticación integrada de Windows.

---

### Paso 2 — Restaurar la base de datos

Ejecuta el script de esquema ubicado en la carpeta `SCRIPTS/` del repositorio:

```
SCRIPTS/schema.sql
```

Puedes correrlo directamente desde SSMS (SQL Server Management Studio).  
El script crea la base de datos `castores_inventario` con **datos semilla** ya incluidos, incluyendo usuario y contraseña de prueba.

---

### Paso 3 — Configurar tu perfil de conexión en Maven

El proyecto incluye dos perfiles de Maven para facilitar la conexión dependiendo de tu entorno:

#### 🔓 Perfil `samuelpc` — Autenticación de Windows (sin contraseña)

> Activo por defecto. Ideal si tu SQL Server usa autenticación integrada de Windows.

```xml
<profile>
    <id>samuelpc</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
        <active.profile>samuelpc</active.profile>
        <db.url>jdbc:sqlserver://localhost:1433;databaseName=castores_inventario;integratedSecurity=true;encrypt=true;trustServerCertificate=true</db.url>
        <hibernate.dialect>org.hibernate.dialect.SQLServerDialect</hibernate.dialect>
    </properties>
</profile>
```

#### 🔐 Perfil `reclutadorpc` — Autenticación con usuario y contraseña

> Usa este perfil si tu SQL Server requiere credenciales explícitas. Puedes cambiar `activeByDefault` a `true` en este perfil si lo necesitas como predeterminado.

```xml
<profile>
    <id>reclutadorpc</id>
    <properties>
        <active.profile>reclutadorpc</active.profile>
        <db.url>jdbc:sqlserver://localhost:1433;databaseName=castores_inventario;encrypt=true;trustServerCertificate=true</db.url>
        <db.user>sa</db.user>
        <db.pass>PasswordReclutador123</db.pass>
    </properties>
</profile>
```

---

### Paso 4 — Descargar dependencias y compilar

Desde la raíz del proyecto, ejecuta los siguientes comandos en orden:

```bash
# 1. Limpiar y descargar dependencias
mvn clean install

# 2. Compilar y empaquetar el proyecto
mvn clean package

# 3. Levantar el servidor con Spring Boot
mvn spring-boot:run
```

> 💡 Si deseas usar el perfil `reclutadorpc` explícitamente:
> ```bash
> mvn spring-boot:run -P reclutadorpc
> ```

---

### Paso 5 — Acceder al sistema

Una vez levantado el servidor, abre tu navegador y accede a:

```
http://localhost:8080
```

En la pantalla de login encontrarás **las credenciales visibles** para facilitar el acceso durante la evaluación. No necesitas recordar nada de memoria.

---

## 📁 Estructura del repositorio

```
EvaluacionTecnica/
├── src/                                        # Código fuente de la aplicación
├── SCRIPTS/
│   ├── schema.sql                              # Esquema completo de la BD + datos semilla
│   ├── diagrama.png                            # Diagrama relacional de la base de datos
│   ├── CONSULTA15.sql                          # Productos que tienen al menos una venta
│   ├── CONSULTA16.sql                          # Productos con ventas y cantidad total vendida
│   └── CONSULTA17.sql                          # Todos los productos con suma total ($) vendida
├── Recursos/
│   ├── mssql-jdbc_auth-12.6.1.x64.dll         # Driver de autenticación SQL Server
│   └── Definiciones SQL.pdf                    # Definiciones teóricas: JOIN, TRIGGER, SP
├── pom.xml                                     # Configuración de Maven y perfiles de conexión
└── README.md                                   # Este archivo
```
---
 
## 🗂️ Recursos adicionales

### 📊 Diagrama relacional
 
El diagrama de la base de datos se encuentra en:
 
📎 [`SCRIPTS/diagrama.png`](./SCRIPTS/diagrama.png)
 
---
 
### 📄 Definiciones SQL
 
Las respuestas teóricas sobre **JOIN**, **TRIGGER** y **Stored Procedures** se encuentran en:
 
📎 [`Recursos/Definiciones SQL.pdf`](./Recursos/Definiciones%20SQL.pdf)
 
---
 
### 🔍 Consultas SQL (Ejercicio 1)
 
Las consultas requeridas en la evaluación se encuentran en la carpeta `SCRIPTS/`:
 
| Archivo | Descripción |
|---------|-------------|
| [`CONSULTA15.sql`](./SCRIPTS/CONSULTA15.sql) | Traer todos los productos que tengan al menos una venta |
| [`CONSULTA16.sql`](./SCRIPTS/CONSULTA16.sql) | Productos con ventas y la cantidad total vendida por producto |
| [`CONSULTA17.sql`](./SCRIPTS/CONSULTA17.sql) | Todos los productos (con o sin ventas) y la suma total en $ vendida |
 
---


---

## 🧩 Notas adicionales

- El sistema está diseñado para correr **completamente en local** sin necesidad de contenedores ni servicios externos.
- Los datos semilla incluidos permiten explorar todas las funcionalidades del sistema desde el primer arranque.
- Si encuentras algún problema de conexión, verifica que el servicio de **SQL Server Browser** esté activo en tu máquina.

---

<div align="center">

Desarrollado con ☕ Java 21 · 🍃 Spring Boot 3.3 · 🔐 Spring Security · 🗄️ SQL Server · 🎨 Thymeleaf · 💅 Bootstrap 5

</div>
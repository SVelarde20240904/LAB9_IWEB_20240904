# IWEB · Validaciones con Java, JDBC, Maven, Servlet y JSP

Proyecto independiente para IntelliJ IDEA. Java 17+; Tomcat 10.1; MySQL 8; Maven.
**No utiliza Spring Boot, JPA ni la tabla de usuarios del proyecto de login.**

## 1. Base de datos
Usa la BD `operadora_movil` existente. Necesita la tabla `cliente` con:
`idCliente`, `dni`, `nombres`, `apellidos`, `correo`, `fechaNacimiento`.
**No ejecutes el SQL original completo sobre tu BD actual:** contiene DROP DATABASE.
No requiere crear tablas nuevas.

## 2. IntelliJ
- File > Open > carpeta del proyecto (la que contiene `pom.xml`).
- Selecciona JDK 17 o 21 (Java 22 también debería compilar; recomendado 17).
- Maven > Reload All Maven Projects.
- Run > Edit Configurations > Tomcat 10.1 Local.
- Configura variables de entorno para TOMCAT (no solo para una clase `main`):
  `DB_URL=jdbc:mysql://localhost:3306/operadora_movil?connectionTimeZone=UTC`
  `DB_USER=root`
  `DB_PASSWORD=TU_CLAVE`
- Deployment > agrega artefacto `iweb-validaciones-operadora:war exploded`.
- Context path sugerido: `/iweb-validaciones`.
- Build > Build Artifacts > Rebuild; inicia Tomcat.
- Abre `http://localhost:8080/iweb-validaciones/clientes`.

## 3. Si aparece No suitable driver found
`pom.xml` incluye MySQL Connector/J sin scope provided.
Ejecuta `mvn clean package` y comprueba que el WAR contiene
`WEB-INF/lib/mysql-connector-j-8.4.0.jar`. En WAR exploded revisa
Project Structure > Artifacts > WEB-INF/lib. Reconstruye y redepliega.
`Class.forName` está en `Db.java` para facilitar diagnóstico, pero no instala el driver.

## 4. Pruebas en clase
1. Registrar DNI `123`: validación de formato (desactiva temporalmente HTML5 o usa DevTools para demostrar servidor).
2. Nombre vacío: obligatorio.
3. Correo `malformado`: formato.
4. Fecha futura: regla de negocio.
5. DNI ya registrado: consulta JDBC `existeDni`.
6. Datos válidos: INSERT JDBC, redirect, mensaje flash.
7. Refrescar `/clientes`: el flash desaparece.
8. Detener MySQL en entorno de pruebas: captura SQLException y mensaje amigable.

## 5. Arquitectura
`registrar.jsp` → `RegistrarClienteServlet` → `ClienteDao` → `Db` → MySQL
`ClientesServlet` → `ClienteDao` → `clientes.jsp`
Se usa PreparedStatement, try-with-resources, POST/Redirect/GET y JSTL.

## 6. Limitaciones didácticas
Sin autenticación; usar solamente en entorno local o controlado.
El chequeo de DNI duplicado es informativo; la restricción UNIQUE de MySQL
es la garantía definitiva ante solicitudes concurrentes.
No se muestran detalles SQL ni contraseñas al usuario.
# LAB9_IWEB_20240904
# LAB9_IWEB_20240904

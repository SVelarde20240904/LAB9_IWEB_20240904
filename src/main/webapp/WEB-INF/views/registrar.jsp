<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="es"><head><meta charset="UTF-8"><title>Registrar cliente</title>
<style>body{font:16px Arial;max-width:760px;margin:35px auto;color:#233}label{display:block;margin-top:12px}
input{padding:9px;width:95%;max-width:500px}.error{background:#ffe7e7;padding:14px;border-radius:6px}
button{margin-top:18px;padding:10px 20px;background:#0b548c;color:white;border:0;cursor:pointer}
a{color:#0b548c}</style></head><body>
<h1>Registrar cliente · Operadora Móvil</h1>
<p><a href="${pageContext.request.contextPath}/clientes">← Ver clientes</a></p>
<c:if test="${not empty errores}"><div class="error"><strong>Corrige los siguientes datos:</strong><ul>
<c:forEach var="error" items="${errores}"><li><c:out value="${error}"/></li></c:forEach>
</ul></div></c:if>
<form action="${pageContext.request.contextPath}/registrarCliente" method="post">
<label>DNI (8 dígitos)</label><input name="dni" value="<c:out value='${dni}'/>" maxlength="8" required pattern="[0-9]{8}">
<label>Nombres</label><input name="nombres" value="<c:out value='${nombres}'/>" maxlength="50" required>
<label>Apellidos</label><input name="apellidos" value="<c:out value='${apellidos}'/>" maxlength="50" required>
<label>Correo (opcional)</label><input name="correo" type="email" value="<c:out value='${correo}'/>" maxlength="100">
<label>Fecha de nacimiento</label><input name="fechaNacimiento" type="date" value="<c:out value='${fechaNacimiento}'/>" required>
<button type="submit">Registrar cliente</button></form>
<p><small>Validación HTML5 + validación en Servlet + restricciones MySQL. Ejemplo académico.</small></p>
</body></html>

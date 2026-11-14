<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="es"><head><meta charset="UTF-8"><title>Clientes</title>
<style>body{font:16px Arial;max-width:1000px;margin:35px auto;color:#233}
table{border-collapse:collapse;width:100%}th,td{border:1px solid #ddd;padding:9px;text-align:left}
th{background:#eaf1f7}.ok{background:#e3f8e7;padding:12px}a{color:#0b548c}</style></head><body>
<h1>Clientes · Operadora Móvil</h1>
<p><a href="${pageContext.request.contextPath}/registrarCliente">+ Registrar nuevo cliente</a></p>
<c:if test="${not empty flash}"><p class="ok"><c:out value="${flash}"/></p></c:if>
<table><thead><tr><th>ID</th><th>DNI</th><th>Nombres</th><th>Apellidos</th><th>Correo</th><th>Nacimiento</th></tr></thead>
<tbody><c:forEach var="c" items="${clientes}"><tr>
<td><c:out value="${c.idCliente()}"/></td><td><c:out value="${c.dni()}"/></td>
<td><c:out value="${c.nombres()}"/></td><td><c:out value="${c.apellidos()}"/></td>
<td><c:out value="${c.correo()}"/></td><td><c:out value="${c.fechaNacimiento()}"/></td>
</tr></c:forEach></tbody></table>
</body></html>

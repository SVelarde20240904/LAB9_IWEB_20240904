# Guía docente · IWEB · Validaciones

## Secuencia sugerida (75–90 min)
1. **10 min**: Mostrar formulario y tabla `cliente`. Distinguir validación HTML5, Servlet y BD.
2. **15 min**: `request.getParameter` y validaciones `if`, `isBlank`, `matches`, `LocalDate.parse`.
3. **10 min**: `List<String> errores`, `request.setAttribute`, `forward` (se preserva request).
4. **15 min**: `ClienteDao.existeDni` con PreparedStatement; `try-with-resources`.
5. **15 min**: `registrar`, `SQLException`, `SQLIntegrityConstraintViolationException`.
6. **10 min**: `sendRedirect` y flash en `HttpSession` (una sola visualización).
7. **15 min**: reto: validar edad mínima 18 años o agregar validación de correo duplicado (solo si el negocio lo exige).

## Puntos para explicar
- `try-catch` maneja excepciones, no sustituye reglas de validación.
- El HTML puede saltarse: las validaciones del servidor son obligatorias.
- `forward` devuelve al JSP con errores y valores del formulario.
- `redirect` crea una nueva solicitud; el mensaje flash viaja en sesión.
- MySQL `UNIQUE(dni)` es la defensa definitiva ante duplicados.
- `SQLException` se registra en logs, sin exponer detalles internos al navegador.

## Actividad evaluable
Agregar `LocalDate` y verificar que la edad sea >=18;
mostrar error en `registrar.jsp` sin insertar.
Pedir explicación de por qué `NumberFormatException` y `SQLException`
son errores distintos.

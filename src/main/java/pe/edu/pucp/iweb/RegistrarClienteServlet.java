package pe.edu.pucp.iweb;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.time.*;
import java.util.*;
@WebServlet("/registrarCliente")
public class RegistrarClienteServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  req.getRequestDispatcher("/WEB-INF/views/registrar.jsp").forward(req,resp);
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  req.setCharacterEncoding("UTF-8");
  String dni=trim(req.getParameter("dni")), nombres=trim(req.getParameter("nombres"));
  String apellidos=trim(req.getParameter("apellidos")),correo=trim(req.getParameter("correo"));
  String fechaTexto=trim(req.getParameter("fechaNacimiento"));
  List<String> errores=new ArrayList<>();
  if(!dni.matches("\\d{8}")) errores.add("El DNI debe contener exactamente 8 dígitos.");
  if(nombres.isBlank()||nombres.length()>50) errores.add("Nombres: obligatorio y máximo 50 caracteres.");
  if(apellidos.isBlank()||apellidos.length()>50) errores.add("Apellidos: obligatorio y máximo 50 caracteres.");
  if(!correo.isBlank() && (correo.length()>100 || !correo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")))
   errores.add("El correo no tiene un formato válido.");
  LocalDate fecha=null;
  try {fecha=LocalDate.parse(fechaTexto);
   if(fecha.isAfter(LocalDate.now())) errores.add("La fecha de nacimiento no puede ser futura.");
  } catch(DateTimeException ex){errores.add("La fecha de nacimiento es obligatoria y debe ser válida.");}
  req.setAttribute("dni",dni);req.setAttribute("nombres",nombres);req.setAttribute("apellidos",apellidos);
  req.setAttribute("correo",correo);req.setAttribute("fechaNacimiento",fechaTexto);
  if(!errores.isEmpty()){volver(req,resp,errores);return;}
  ClienteDao dao=new ClienteDao();
  try {
   if(dao.existeDni(dni)){errores.add("El DNI ya está registrado.");volver(req,resp,errores);return;}
   dao.registrar(dni,nombres,apellidos,correo.isBlank()?null:correo,fecha);
   req.getSession().setAttribute("flash","Cliente registrado correctamente.");
   resp.sendRedirect(req.getContextPath()+"/clientes");
  }catch(SQLIntegrityConstraintViolationException ex){
   errores.add("No se pudo registrar: DNI duplicado u otra restricción de la BD.");
   volver(req,resp,errores);
  }catch(SQLException ex){
   getServletContext().log("Error JDBC al registrar cliente",ex);
   errores.add("No se pudo guardar el cliente por un error de base de datos.");
   volver(req,resp,errores);
  }
 }
 private static String trim(String s){return s==null?"":s.trim();}
 private void volver(HttpServletRequest req,HttpServletResponse resp,List<String> errores)throws ServletException,IOException{
  req.setAttribute("errores",errores);
  req.getRequestDispatcher("/WEB-INF/views/registrar.jsp").forward(req,resp);
 }
}

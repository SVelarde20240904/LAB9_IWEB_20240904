package pe.edu.pucp.iweb;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/clientes")
public class ClientesServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
  try {
   req.setAttribute("clientes",new ClienteDao().listar());
   HttpSession session=req.getSession(false);
   if(session!=null){
    Object flash=session.getAttribute("flash");
    if(flash!=null){req.setAttribute("flash",flash);session.removeAttribute("flash");}
   }
   req.getRequestDispatcher("/WEB-INF/views/clientes.jsp").forward(req,resp);
  }catch(SQLException e){getServletContext().log("Error consultando clientes",e);
   resp.sendError(500,"No se pudo consultar la base de datos");}
 }
}

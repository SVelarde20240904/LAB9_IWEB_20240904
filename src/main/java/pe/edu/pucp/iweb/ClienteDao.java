package pe.edu.pucp.iweb;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
 public boolean existeDni(String dni) throws SQLException {
  String sql="SELECT 1 FROM cliente WHERE dni=? LIMIT 1";
  try(Connection c=Db.open(); PreparedStatement ps=c.prepareStatement(sql)){
   ps.setString(1,dni);
   try(ResultSet rs=ps.executeQuery()){return rs.next();}
  }
 }
 public void registrar(String dni,String nombres,String apellidos,String correo,LocalDate fecha) throws SQLException {
  String sql="INSERT INTO cliente (dni,nombres,apellidos,correo,fechaNacimiento) VALUES (?,?,?,?,?)";
  try(Connection c=Db.open();PreparedStatement ps=c.prepareStatement(sql)){
   ps.setString(1,dni);ps.setString(2,nombres);ps.setString(3,apellidos);
   ps.setString(4,correo);ps.setDate(5,Date.valueOf(fecha));ps.executeUpdate();
  }
 }
 public List<Cliente> listar() throws SQLException {
  List<Cliente> lista=new ArrayList<>();
  try(Connection c=Db.open();PreparedStatement ps=c.prepareStatement(
   "SELECT idCliente,dni,nombres,apellidos,correo,fechaNacimiento FROM cliente ORDER BY idCliente DESC");
   ResultSet rs=ps.executeQuery()){
   while(rs.next()) lista.add(new Cliente(rs.getInt("idCliente"),rs.getString("dni"),
    rs.getString("nombres"),rs.getString("apellidos"),rs.getString("correo"),
    rs.getDate("fechaNacimiento").toLocalDate()));
  }
  return lista;
 }
}

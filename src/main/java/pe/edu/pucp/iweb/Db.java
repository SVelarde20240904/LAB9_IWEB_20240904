package pe.edu.pucp.iweb;
import java.sql.*;
public final class Db {
 private Db(){}
 public static Connection open() throws SQLException {
  try { Class.forName("com.mysql.cj.jdbc.Driver"); }
  catch(ClassNotFoundException ex) { throw new SQLException("Driver MySQL ausente del WAR",ex); }
  String url=System.getenv().getOrDefault("DB_URL","jdbc:mysql://localhost:3306/lab9_biblioteca?connectionTimeZone=UTC");
  String user=System.getenv().getOrDefault("DB_USER","root");
  String pass=System.getenv().getOrDefault("DB_PASSWORD","");
  return DriverManager.getConnection(url,user,pass);
 }
}

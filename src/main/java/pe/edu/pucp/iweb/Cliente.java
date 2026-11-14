package pe.edu.pucp.iweb;
import java.time.LocalDate;
public record Cliente(int idCliente,String dni,String nombres,String apellidos,String correo,LocalDate fechaNacimiento){}

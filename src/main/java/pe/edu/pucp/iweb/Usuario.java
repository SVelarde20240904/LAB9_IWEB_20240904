package pe.edu.pucp.iweb;

public class Usuario {
    int idUsuario;
    String nombres;
    String apellidos;
    String email;
    enum rol {
        BIBLIOTECARIO, LECTOR;
    };
    enum estado {
        ACTIVO, BLOQUEADO;
    };


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


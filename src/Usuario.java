import java.io.*;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String contraseya;

    private String privilegios;

    public Usuario(int id, String nombre, String contraseya, String privilegios) {
        this.id = id;
        this.nombre = nombre;
        this.contraseya = contraseya;
        this.privilegios = privilegios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseya() {
        return contraseya;
    }

    public void setContraseya(String contraseya) {
        this.contraseya = contraseya;
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(String privilegios) {
        this.privilegios = privilegios;
    }

    @Override
    public String toString() {
        return "Usuario: \n" +
                "id: " + id + "\n" +
                "Nombre: " + nombre + '\n' +
                "Contraseña: " + contraseya + '\n' +
                "Privilegios: " + privilegios;
    }




}

import java.io.*;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private static final long serialVersionUID = -7550656432867949133L;
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

    public static void escribirUsuario(int id, String nombre, String contra, String privilegios) throws IOException {
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileOutputStream fileout = new FileOutputStream(fichero, true);
        MyObjectOutputStream dataOs = new MyObjectOutputStream(fileout);
        Usuario usuario = new Usuario(id, nombre, contra, privilegios);
        dataOs.writeObject(usuario);
        dataOs.close();
        System.out.println("Se escribio el usuario correctamente.");
    }


    public static String leerListaUsuariosPriv(String nombreU, String contraseya) throws IOException, ClassNotFoundException {
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        Usuario usuario;
        MyInputObjectStream dataIn = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {

            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream((fileIn));
                usuario = (Usuario) dataIn.readObject();
                usuarios.add(usuario);
            }
        } catch (EOFException e) {
            System.out.println("Se ha leido la lista");
        }
        dataIn.close();

        for (Usuario usu : usuarios) {
            if (usu.getNombre().equals(nombreU)) {
                if (usu.getContraseya().equals(contraseya)) {
                    return usu.getPrivilegios();
                }
            }
        }

        return "";
    }

    public static boolean leerListaUsuarios(String nombreU, String contraseya) throws IOException, ClassNotFoundException {
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        Usuario usuario;
        MyInputObjectStream dataIn = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {

            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream((fileIn));
                usuario = (Usuario) dataIn.readObject();
                usuarios.add(usuario);
            }
        } catch (EOFException e) {
            System.out.println("Se ha leido la lista");
        }
        dataIn.close();

        for (Usuario usu : usuarios) {
            if (usu.getNombre().equals(nombreU)) {
                if (usu.getContraseya().equals(contraseya)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void leerUsuarios() throws IOException, ClassNotFoundException {
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        Usuario usuario;
        MyInputObjectStream dataIn = null;
        try {

            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream((fileIn));
                usuario = (Usuario) dataIn.readObject();
                System.out.println(usuario);
            }
        } catch (EOFException e) {
            System.out.println("Se ha leido la lista");
        }
        dataIn.close();
    }
}

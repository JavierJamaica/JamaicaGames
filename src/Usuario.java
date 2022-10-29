import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public static void modificarUsuarioNombre(int opcionModificar, String nuevoNombre) throws IOException, ClassNotFoundException {
        Usuario usuarioMod;
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int usuarioExiste = 0;

        File ficheroAux = new File(".//Ficheros/UsuariosAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                usuarioMod = (Usuario) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (usuarioMod.getId() == opcionModificar) {
                usuarioMod.setNombre(nuevoNombre);
                usuarioExiste = 1;
            }
            Usuario usuarioMod2 = new Usuario(usuarioMod.getId(), usuarioMod.getNombre(), usuarioMod.getContraseya(), usuarioMod.getPrivilegios());
            dataOsAux.writeObject(usuarioMod2);

        }
        if (usuarioExiste > 0) {
            crearNuevoUsuarioMod();
            System.out.println("Se ha modificado el usuario pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    public static void modificarContra(int opcionModificar, String nuevaContra) throws IOException, ClassNotFoundException {
        Usuario usuarioMod;
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int usuarioExiste = 0;

        File ficheroAux = new File(".//Ficheros/UsuariosAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                usuarioMod = (Usuario) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (usuarioMod.getId() == opcionModificar) {
                usuarioMod.setContraseya(nuevaContra);
                usuarioExiste = 1;
            }
            Usuario usuarioMod2 = new Usuario(usuarioMod.getId(), usuarioMod.getNombre(), usuarioMod.getContraseya(), usuarioMod.getPrivilegios());
            dataOsAux.writeObject(usuarioMod2);

        }
        if (usuarioExiste > 0) {
            crearNuevoUsuarioMod();
            System.out.println("Se ha modificado el usuario pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    public static void modificarPrivilegios(int opcionModificar, String nuevoPriv) throws IOException, ClassNotFoundException {
        Usuario usuarioMod;
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int usuarioExiste = 0;

        File ficheroAux = new File(".//Ficheros/UsuariosAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                usuarioMod = (Usuario) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (usuarioMod.getId() == opcionModificar) {
                usuarioMod.setPrivilegios(nuevoPriv);
                usuarioExiste = 1;
            }
            Usuario usuarioMod2 = new Usuario(usuarioMod.getId(), usuarioMod.getNombre(), usuarioMod.getContraseya(), usuarioMod.getPrivilegios());
            dataOsAux.writeObject(usuarioMod2);

        }
        if (usuarioExiste > 0) {
            crearNuevoUsuarioMod();
            System.out.println("Se ha modificado el usuario pai");
            dataOsAux.close();
            dataIn.close();
        }

    }

    public static void borrarUsuario(int usuarioBorrar) throws IOException, ClassNotFoundException {
        Usuario usuarioB;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;

        File ficheroAux = new File(".//Ficheros/UsuariosAuxBorrar.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = new MyObjectOutputStream(fileOutAux);
        try {
            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream(fileIn);

                usuarioB = (Usuario) dataIn.readObject();
                usuarios.add(usuarioB);
            }


            for (Usuario usuario : usuarios) {
                if (usuario.getId() != usuarioBorrar) {
                    dataOsAux.writeObject(usuario);
                }
            }
            crearNuevoUsuarioBorrar();
            System.out.println("Se ha borrado de la lista");
            dataOsAux.close();
        } catch (EOFException e) {
            usuarios.clear();
            System.out.println("Eeror eofe");
        }
        dataIn.close();
        dataOsAux.close();


    }

    public static void crearNuevoUsuarioBorrar() throws IOException {
        Usuario usuarioB;

        File ficheroAux = new File(".//Ficheros/UsuariosAuxBorrar.dat");
        FileInputStream fileInAux = new FileInputStream(ficheroAux);
        MyInputObjectStream dataInAux = new MyInputObjectStream(fileInAux);

        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);


        try {
            while (fileInAux.available() != 0) {
                MyObjectOutputStream dataOut = new MyObjectOutputStream(fileOut);
                usuarioB = (Usuario) dataInAux.readObject();
                Usuario usuarioMod2 = new Usuario(usuarioB.getId(), usuarioB.getNombre(), usuarioB.getContraseya(), usuarioB.getPrivilegios());
                dataOut.writeObject(usuarioMod2);

            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e) {
            System.out.println("Se ha leido el archivo");
        }
        fileOut.close();
        fileInAux.close();
    }

    public static void crearNuevoUsuarioMod() throws IOException {
        Usuario usuarioMod;

        File ficheroAux = new File(".//Ficheros/UsuariosAux.dat");
        FileInputStream fileInAux = new FileInputStream(ficheroAux);

        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);

        try {
            while (fileInAux.available() != 0) {
                MyObjectOutputStream dataOut = new MyObjectOutputStream(fileOut);
                MyInputObjectStream dataInAux = new MyInputObjectStream(fileInAux);
                usuarioMod = (Usuario) dataInAux.readObject();
                Usuario usuarioMod2 = new Usuario(usuarioMod.getId(), usuarioMod.getNombre(), usuarioMod.getContraseya(), usuarioMod.getPrivilegios());
                dataOut.writeObject(usuarioMod2);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        fileOut.close();
        fileInAux.close();

    }

    public static void crearXmlUsuario() throws IOException {
        File fichero = new File(".//Ficheros/Usuarios.dat");
        FileInputStream fileIn = new FileInputStream(fichero);

        MyInputObjectStream dataIS = new MyInputObjectStream(fileIn);
        System.out.println("Comienza el proceso de creacion a XML..");
        ListaUsuarios listaP = new ListaUsuarios();
        try {
            while (true) {
                Usuario usuario = (Usuario) dataIS.readObject();
                listaP.add(usuario);
            }
        } catch (EOFException e) {
            System.out.println("Fichero leido");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        dataIS.close();
        try {
            XStream xStream = new XStream();
            xStream.alias("ListaUsuarios", ListaUsuarios.class);
            xStream.alias("DatosUsuario", Usuario.class);
            xStream.addImplicitCollection(ListaUsuarios.class, "lista");
            xStream.toXML(listaP, new FileOutputStream(".//FicherosXML/Usuarios.xml"));
            System.out.println("Creado fichero xml...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leerXmlUsuario() throws FileNotFoundException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("ListaUsuarios", ListaUsuarios.class);

        xStream.alias("DatosUsuario", Usuario.class);
        xStream.addImplicitCollection(ListaUsuarios.class, "lista");

        FileInputStream fichero = new FileInputStream(".//FicherosXML/Usuarios.xml");
        ListaUsuarios lista = (ListaUsuarios) xStream.fromXML(fichero);
        System.out.println("Numero de usuarios: " + lista.getListaUsuarios().size());
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = lista.getListaUsuarios();
        for (Usuario usuario : listaUsuarios) {
            System.out.printf("Id: %s, Nombre: %s, Contraseña: %s, Privilegios: %s %n", usuario.getId(), usuario.getNombre(), usuario.getContraseya(), usuario.getPrivilegios());
        }
        System.out.println("Fin del listado....");
    }

}

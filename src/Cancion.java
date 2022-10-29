import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Javier Jamaica
 * 28/10/2022
 */
public class Cancion implements Serializable {
    // Serial UID para evitar el error de serializacion
    private static final long serialVersionUID = -8291835848771634014L;

    // Atributos de la clase cancion
    private int id;
    private String nombre;
    private String autor;
    private String genero;
    private int anyo;

    // Constructor de la clase
    public Cancion(int id, String nombre, String autor, String genero, int anyo) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.genero = genero;
        this.anyo = anyo;
    }

    // Getter y Setter de la clase

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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }


    @Override
    public String toString() {
        return "-----------------------\n" + "Cancion: \n" + "Id: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Autor: " + autor + "\n" +
                "Genero: " + genero + "\n" + "Año: " + anyo + "\n" + "-----------------------";
    }

    // Funcion que usamos para escribir el fichero con la clase cancion
    public static void escribirCancion(int id, String nombre, String autor, String genero, int anyo) throws IOException {
        File fichero = new File(".//Ficheros/Canciones.dat");
        FileOutputStream fileout = new FileOutputStream(fichero, true);
        MyObjectOutputStream dataOs = new MyObjectOutputStream(fileout); // Cree una subclase de ObjectOutPutStream para evitar cabeceras de bytes
        Cancion cancion = new Cancion(id, nombre, autor, genero, anyo);
        dataOs.writeObject(cancion);
        dataOs.close();
        System.out.println("Se escribio la cancion correctamente.");
    }

    // Funcion para leer el archivo .dat con los objetos de tipo cancion
    public static void leerListaCancion() throws IOException, ClassNotFoundException {
        File fichero = new File(".//Ficheros/Canciones.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        Cancion cancion;
        MyInputObjectStream dataIn = null;
        try {

            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream((fileIn));
                cancion = (Cancion) dataIn.readObject();
                System.out.println(cancion);
            }
        } catch (EOFException e) {
            System.out.println("Se ha leido la lista");
        }
        dataIn.close();
    }

    // Funciones para modificar cada atributo de la clase cancion del fichero .dat
    public static void modificarCancionNombre(int opcionModificar, String nuevoNombre) throws IOException, ClassNotFoundException {
        Cancion cancionMod;
        File fichero = new File(".//Ficheros/Canciones.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int cancionExiste = 0;

        File ficheroAux = new File(".//Ficheros/CancionesAux.dat"); // Usamos un fichero auxiliar para escribir la modificacion
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                cancionMod = (Cancion) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }

            if (cancionMod.getId() == opcionModificar) {
                cancionMod.setNombre(nuevoNombre);
                cancionExiste = 1;
            }
            Cancion cancionMod2 = new Cancion(cancionMod.getId(), cancionMod.getNombre(), cancionMod.getAutor(),
                    cancionMod.getGenero(), cancionMod.getAnyo());
            dataOsAux.writeObject(cancionMod2);

        }
        if (cancionExiste > 0) {
            crearNuevaCancionMod(); // Con esta funcion reescribimos el fichero original con el fichero aux
            System.out.println("Se ha modificado la cancion pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    // Funcion para modificar el atributo cancion
    public static void modificarCancionGenero(int opcionModificar, String nuevoGenero) throws IOException, ClassNotFoundException {
        Cancion cancionMod;
        File fichero = new File(".//Ficheros/Canciones.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int cancionExiste = 0;

        File ficheroAux = new File(".//Ficheros/CancionesAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                cancionMod = (Cancion) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (cancionMod.getId() == opcionModificar) {
                cancionMod.setGenero(nuevoGenero);
                cancionExiste = 1;
            }
            Cancion cancionMod2 = new Cancion(cancionMod.getId(), cancionMod.getNombre(), cancionMod.getAutor(),
                    cancionMod.getGenero(), cancionMod.getAnyo());
            dataOsAux.writeObject(cancionMod2);

        }
        if (cancionExiste > 0) {
            crearNuevaCancionMod();
            System.out.println("Se ha modificado la cancion pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    // Funcion para modificar el atributo anyo
    public static void modificarCancionAnyo(int opcionModificar, int nuevoAnyo) throws IOException, ClassNotFoundException {
        Cancion cancionMod;
        File fichero = new File(".//Ficheros/Canciones.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int cancionExiste = 0;

        File ficheroAux = new File(".//Ficheros/CancionesAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                cancionMod = (Cancion) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (cancionMod.getId() == opcionModificar) {
                cancionMod.setAnyo(nuevoAnyo);
                cancionExiste = 1;
            }
            Cancion cancionMod2 = new Cancion(cancionMod.getId(), cancionMod.getNombre(), cancionMod.getAutor(),
                    cancionMod.getGenero(), cancionMod.getAnyo());
            dataOsAux.writeObject(cancionMod2);

        }
        if (cancionExiste > 0) {
            crearNuevaCancionMod();
            System.out.println("Se ha la cancion pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    // Funcion para modificar el atributo autor
    public static void modificarCancionAutor(int opcionModificar, String nuevoAutor) throws IOException, ClassNotFoundException {
        Cancion cancionMod;
        File fichero = new File(".//Ficheros/Canciones.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int cancionExiste = 0;

        File ficheroAux = new File(".//Ficheros/CancionesAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                cancionMod = (Cancion) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (cancionMod.getId() == opcionModificar) {
                cancionMod.setAutor(nuevoAutor);
                cancionExiste = 1;
            }
            Cancion cancionMod2 = new Cancion(cancionMod.getId(), cancionMod.getNombre(), cancionMod.getAutor(), cancionMod.getGenero(), cancionMod.getAnyo());
            dataOsAux.writeObject(cancionMod2);

        }
        if (cancionExiste > 0) {
            crearNuevaCancionMod();
            System.out.println("Se ha modificado la cancion pai");
            dataOsAux.close();
            dataIn.close();
        }

    }

    // Funcion para borrar una entrada de tipo cancion en el .dat
    public static void borrarCancion(int cancionBorrar) throws IOException, ClassNotFoundException {
        Cancion cancionB;
        ArrayList<Cancion> canciones = new ArrayList<>();
        File fichero = new File(".//Ficheros/Canciones.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;

        File ficheroAux = new File(".//Ficheros/CancionesAuxBorrar.dat"); // Creamos un fichero auxiliar
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = new MyObjectOutputStream(fileOutAux);
        try {
            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream(fileIn);

                cancionB = (Cancion) dataIn.readObject();
                canciones.add(cancionB);
            }

            for (Cancion cancion : canciones) {
                if (cancion.getId() != cancionBorrar) {
                    dataOsAux.writeObject(cancion);
                }
            }
            crearNuevaCancionBorrar(); // Usamos el auxiloar para reescribir dl original
            System.out.println("Se ha borrado de la lista");
            dataOsAux.close();
        } catch (EOFException e) {
            System.out.println("Se ha leido el fichero");
        }
        dataIn.close();
        dataOsAux.close();


    }

    // Funcion que usmaos para escribir el fichero original con el auxiliar
    public static void crearNuevaCancionBorrar() throws IOException {
        Cancion cancionB;

        File ficheroAux = new File(".//Ficheros/CancionesAuxBorrar.dat");
        FileInputStream fileInAux = new FileInputStream(ficheroAux);
        MyInputObjectStream dataInAux = new MyInputObjectStream(fileInAux);

        File fichero = new File(".//Ficheros/Canciones.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);


        try {
            while (fileInAux.available() != 0) {
                MyObjectOutputStream dataOut = new MyObjectOutputStream(fileOut);
                cancionB = (Cancion) dataInAux.readObject();
                Cancion cancionB2 = new Cancion(cancionB.getId(), cancionB.getNombre(), cancionB.getAutor(),
                        cancionB.getGenero(), cancionB.getAnyo());
                dataOut.writeObject(cancionB2);

            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e) {
            System.out.println("Se ha leido el archivo");
        }
        fileOut.close();
        fileInAux.close();
    }

    // Funcion que usamos para escribir el fichero original con el auxiliar
    public static void crearNuevaCancionMod() throws IOException {
        Cancion cancionMod;

        File ficheroAux = new File(".//Ficheros/CancionesAux.dat");
        FileInputStream fileInAux = new FileInputStream(ficheroAux);

        File fichero = new File(".//Ficheros/Canciones.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);

        try {
            while (fileInAux.available() != 0) {
                MyObjectOutputStream dataOut = new MyObjectOutputStream(fileOut);
                MyInputObjectStream dataInAux = new MyInputObjectStream(fileInAux);
                cancionMod = (Cancion) dataInAux.readObject();
                Cancion cancionMod2 = new Cancion(cancionMod.getId(), cancionMod.getNombre(), cancionMod.getAutor(), cancionMod.getGenero(), cancionMod.getAnyo());
                dataOut.writeObject(cancionMod2);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        fileOut.close();
        fileInAux.close();

    }

    // Funcion para crear un xml apartir del fichero canciones.dat
    public static void crearXmlCancion() throws IOException {
        File fichero = new File(".//Ficheros/Canciones.dat");
        FileInputStream fileIn = new FileInputStream(fichero);

        MyInputObjectStream dataIS = new MyInputObjectStream(fileIn);
        System.out.println("Comienza el proceso de creacion a XML..");
        ListaCanciones listaP = new ListaCanciones();
        try {
            while (true) {
                Cancion cancion = (Cancion) dataIS.readObject();
                listaP.add(cancion);
            }
        } catch (EOFException e) {
            System.out.println("Fichero leido");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        dataIS.close();
        try {
            XStream xStream = new XStream();
            xStream.alias("ListaCanciones", ListaCanciones.class);
            xStream.alias("DatosCanciones", Cancion.class);
            xStream.addImplicitCollection(ListaCanciones.class, "lista");
            xStream.toXML(listaP, new FileOutputStream(".//FicherosXML/Canciones.xml"));
            System.out.println("Creado fichero xml...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funcion para leer el archivo xml y luego mostrarlo como una lista de objetos de tipo cancion
    public static void leerXmlCancion() throws FileNotFoundException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("ListaCanciones", ListaCanciones.class);

        xStream.alias("DatosCanciones", Cancion.class);
        xStream.addImplicitCollection(ListaCanciones.class, "lista");

        FileInputStream fichero = new FileInputStream(".//FicherosXML/Canciones.xml");
        ListaCanciones lista = (ListaCanciones) xStream.fromXML(fichero);
        System.out.println("Numero de canciones: " + lista.getListaCanciones().size());
        List<Cancion> listaCanciones = new ArrayList<>();
        listaCanciones = lista.getListaCanciones();
        for (Cancion cancion : listaCanciones) {
            System.out.printf("Id: %s, Nombre: %s, Autor: %s, Genero: %s, Año: %s %n", cancion.getId(), cancion.getNombre(), cancion.getAutor(), cancion.getGenero(), cancion.getAnyo());
        }
        System.out.println("Fin del listado....");
    }


}

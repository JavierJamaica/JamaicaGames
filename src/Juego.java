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
public class Juego implements Serializable {
    // Serial UID para evitar el error de serializacion

    private static final long serialVersionUID = 1235495732780724037L;

    // Atributos de la clase juego
    private int id;
    private String nombre;
    private int anyo;
    private int puntuacion;

    // Constructor de la clase

    public Juego(int id, String nombre, int anyo, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.anyo = anyo;
        this.puntuacion = puntuacion;
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

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "-----------------------\n" + "Juego: \n" + "Id: " + id + "\n" + "Nombre: " + nombre + "\n" + "Año: " + anyo + "\n" + "Puntuacion: " + puntuacion + "\n" + "-----------------------";
    }

    // Funcion que usamos para escribir el fichero con la clase juego

    public static void escribirJuego(int id, String nombre, int anyo, int puntuacion) throws IOException {
        File fichero = new File(".//Ficheros/Juegos.dat");
        FileOutputStream fileout = new FileOutputStream(fichero, true);
        MyObjectOutputStream dataOs = new MyObjectOutputStream(fileout);
        Juego juego = new Juego(id, nombre, anyo, puntuacion);
        dataOs.writeObject(juego);
        dataOs.close();
        System.out.println("Se escribio el juego correctamente.");
    }

    // Funcion para leer el archivo .dat con los objetos de tipo juego

    public static void leerLista() throws IOException, ClassNotFoundException {
        File fichero = new File(".//Ficheros/Juegos.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        Juego juego;
        MyInputObjectStream dataIn = null;
        try {

            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream((fileIn));
                juego = (Juego) dataIn.readObject();
                System.out.println(juego);
            }
        } catch (EOFException e) {
            System.out.println("Se ha leido la lista");
        }
        dataIn.close();
    }

    // Funcion para modificar el atributo nombre de la clase juego

    public static void modificarJuegoNombre(int opcionModificar, String nuevoNombre) throws IOException, ClassNotFoundException {
        Juego juegoMod;
        File fichero = new File(".//Ficheros/Juegos.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int juegoExiste = 0;

        File ficheroAux = new File(".//Ficheros/JuegosAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                juegoMod = (Juego) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (juegoMod.getId() == opcionModificar) {
                juegoMod.setNombre(nuevoNombre);
                juegoExiste = 1;
            }
            Juego juegoMod2 = new Juego(juegoMod.getId(), juegoMod.getNombre(), juegoMod.getAnyo(), juegoMod.getPuntuacion());
            dataOsAux.writeObject(juegoMod2);

        }
        if (juegoExiste > 0) {
            crearNuevoJuegoMod();
            System.out.println("Se ha modificado el juego pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    // Funcion para modificar el atributo anyo de la clase juego
    public static void modificarJuegoAnyo(int opcionModificar, int nuevoAnyo) throws IOException, ClassNotFoundException {
        Juego juegoMod;
        File fichero = new File(".//Ficheros/Juegos.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int juegoExiste = 0;

        File ficheroAux = new File(".//Ficheros/JuegosAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                juegoMod = (Juego) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (juegoMod.getId() == opcionModificar) {
                juegoMod.setAnyo(nuevoAnyo);
                juegoExiste = 1;
            }
            Juego juegoMod2 = new Juego(juegoMod.getId(), juegoMod.getNombre(), juegoMod.getAnyo(), juegoMod.getPuntuacion());
            dataOsAux.writeObject(juegoMod2);

        }
        if (juegoExiste > 0) {
            crearNuevoJuegoMod();
            System.out.println("Se ha modificado el juego pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    // Funcion para modificar el atributo puntuacion
    public static void modificarPuntuacion(int opcionModificar, int nuevaPuntuacion) throws IOException, ClassNotFoundException {
        Juego juegoMod;
        File fichero = new File(".//Ficheros/Juegos.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int juegoExiste = 0;

        File ficheroAux = new File(".//Ficheros/JuegosAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                juegoMod = (Juego) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (juegoMod.getId() == opcionModificar) {
                juegoMod.setPuntuacion(nuevaPuntuacion);
                juegoExiste = 1;
            }
            Juego juegoMod2 = new Juego(juegoMod.getId(), juegoMod.getNombre(), juegoMod.getAnyo(), juegoMod.getPuntuacion());
            dataOsAux.writeObject(juegoMod2);

        }
        if (juegoExiste > 0) {
            crearNuevoJuegoMod();
            System.out.println("Se ha modificado el juego pai");
            dataOsAux.close();
            dataIn.close();
        }

    }

    // Funcion para borrar una entrada de tipo juego en el .dat
    public static void borrarJuego(int juegoBorrar) throws IOException, ClassNotFoundException {
        Juego juegoB;
        ArrayList<Juego> juegos = new ArrayList<>();
        File fichero = new File(".//Ficheros/Juegos.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;

        File ficheroAux = new File(".//Ficheros/JuegosAuxBorrar.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = new MyObjectOutputStream(fileOutAux);
        try {
            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream(fileIn);

                juegoB = (Juego) dataIn.readObject();
                juegos.add(juegoB);
            }


            for (Juego juego : juegos) {
                if (juego.getId() != juegoBorrar) {
                    dataOsAux.writeObject(juego);
                }
            }
            crearNuevoJuegoBorrar();
            System.out.println("Se ha borrado de la lista");
            dataOsAux.close();
        } catch (EOFException e) {
            juegos.clear();
            System.out.println("Eeror eofe");
        }
        dataIn.close();
        dataOsAux.close();


    }

    // Funcion que usamos para escribir el fichero original con el auxiliar
    public static void crearNuevoJuegoBorrar() throws IOException {
        Juego juegoB;

        File ficheroAux = new File(".//Ficheros/JuegosAuxBorrar.dat");
        FileInputStream fileInAux = new FileInputStream(ficheroAux);
        MyInputObjectStream dataInAux = new MyInputObjectStream(fileInAux);

        File fichero = new File(".//Ficheros/Juegos.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);


        try {
            while (fileInAux.available() != 0) {
                MyObjectOutputStream dataOut = new MyObjectOutputStream(fileOut);
                juegoB = (Juego) dataInAux.readObject();
                Juego juegoMod2 = new Juego(juegoB.getId(), juegoB.getNombre(), juegoB.getAnyo(), juegoB.getPuntuacion());
                dataOut.writeObject(juegoMod2);

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
    public static void crearNuevoJuegoMod() throws IOException {
        Juego juegoMod;

        File ficheroAux = new File(".//Ficheros/JuegosAux.dat");
        FileInputStream fileInAux = new FileInputStream(ficheroAux);

        File fichero = new File(".//Ficheros/Juegos.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);

        try {
            while (fileInAux.available() != 0) {
                MyObjectOutputStream dataOut = new MyObjectOutputStream(fileOut);
                MyInputObjectStream dataInAux = new MyInputObjectStream(fileInAux);
                juegoMod = (Juego) dataInAux.readObject();
                Juego juegoMod2 = new Juego(juegoMod.getId(), juegoMod.getNombre(), juegoMod.getAnyo(), juegoMod.getPuntuacion());
                dataOut.writeObject(juegoMod2);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        fileOut.close();
        fileInAux.close();

    }

    // Funcion para crear un xml apartir del fichero juegoes.dat
    public static void crearXmlJuego() throws IOException {
        File fichero = new File(".//Ficheros/Juegos.dat");
        FileInputStream fileIn = new FileInputStream(fichero);

        MyInputObjectStream dataIS = new MyInputObjectStream(fileIn);
        System.out.println("Comienza el proceso de creacion a XML..");
        ListaJuegos listaP = new ListaJuegos();
        try {
            while (true) {
                Juego juego = (Juego) dataIS.readObject();
                listaP.add(juego);
            }
        } catch (EOFException e) {
            System.out.println("Fichero leido");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        dataIS.close();
        try {
            XStream xStream = new XStream();
            xStream.alias("ListaJuegos", ListaJuegos.class);
            xStream.alias("DatosJuego", Juego.class);
            xStream.addImplicitCollection(ListaJuegos.class, "lista");
            xStream.toXML(listaP, new FileOutputStream(".//FicherosXML/Juegos.xml"));
            System.out.println("Creado fichero xml...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funcion para leer el archivo xml y luego mostrarlo como una lista de objetos de tipo juego
    public static void leerXmlJuego() throws FileNotFoundException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("ListaJuegos", ListaJuegos.class);

        xStream.alias("DatosJuego", Juego.class);
        xStream.addImplicitCollection(ListaJuegos.class, "lista");

        FileInputStream fichero = new FileInputStream(".//FicherosXML/Juegos.xml");
        ListaJuegos lista = (ListaJuegos) xStream.fromXML(fichero);
        System.out.println("Numero de juegos: " + lista.getListaJuegos().size());
        List<Juego> listaJuegos = new ArrayList<>();
        listaJuegos = lista.getListaJuegos();
        for (Juego juego : listaJuegos) {
            System.out.printf("Id: %s, Nombre: %s, Año: %s, Puntuacion: %s %n", juego.getId(), juego.getNombre(), juego.getAnyo(), juego.getPuntuacion());
        }
        System.out.println("Fin del listado....");
    }
}

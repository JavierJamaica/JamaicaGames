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
public class Pelicula implements Serializable {
    // Serial UID para evitar el error de serializacion
    private static final long serialVersionUID = -2115749393533822256L;

    // Atributos de la clase pelicula
    private int id;
    private String nombre;
    private String director;
    private String genero;
    private int anyo;

    // Constructor de la clase

    public Pelicula(int id, String nombre, String director, String genero, int anyo) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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
        return "-----------------------\n" + "Pelicula: \n" + "Id: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Director: " + director + "\n" +
                "Genero: " + genero + "\n" + "Año: " + anyo + "\n" + "-----------------------";
    }

    // Funcion que usamos para escribir el fichero con la clase pelicula
    public static void escribirPelicula(int id, String nombre, String director, String genero, int anyo) throws IOException {
        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileOutputStream fileout = new FileOutputStream(fichero, true);
        MyObjectOutputStream dataOs = new MyObjectOutputStream(fileout);
        Pelicula pelicula = new Pelicula(id, nombre, director, genero, anyo);
        dataOs.writeObject(pelicula);
        dataOs.close();
        System.out.println("Se escribio la pelicula correctamente.");
    }

    // Funcion para leer el archivo .dat con los objetos de tipo pelicula
    public static void leerListaPelicula() throws IOException, ClassNotFoundException {
        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        Pelicula pelicula;
        MyInputObjectStream dataIn = null;
        try {

            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream((fileIn));
                pelicula = (Pelicula) dataIn.readObject();
                System.out.println(pelicula);
            }
        } catch (EOFException e) {
            System.out.println("Se ha leido la lista");
        }
        dataIn.close();
    }

    // Funcion para modificar el atributo nombre de la clase pelicula
    public static void modificarPeliculaNombre(int opcionModificar, String nuevoNombre) throws IOException, ClassNotFoundException {
        Pelicula peliculaMod;
        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int peliculaExiste = 0;

        File ficheroAux = new File(".//Ficheros/PeliculasAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                peliculaMod = (Pelicula) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (peliculaMod.getId() == opcionModificar) {
                peliculaMod.setNombre(nuevoNombre);
                peliculaExiste = 1;
            }
            Pelicula peliculaMod2 = new Pelicula(peliculaMod.getId(), peliculaMod.getNombre(), peliculaMod.getDirector(),
                    peliculaMod.getGenero(), peliculaMod.getAnyo());
            dataOsAux.writeObject(peliculaMod2);

        }
        if (peliculaExiste > 0) {
            crearNuevaPeliculaMod();
            System.out.println("Se ha modificado la pelicula pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    // Funcion para modificar el atributo genero de la clase pelicula
    public static void modificarPeliculaGenero(int opcionModificar, String nuevoGenero) throws IOException, ClassNotFoundException {
        Pelicula peliculaMod;
        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int peliculaExiste = 0;

        File ficheroAux = new File(".//Ficheros/PeliculasAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                peliculaMod = (Pelicula) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (peliculaMod.getId() == opcionModificar) {
                peliculaMod.setGenero(nuevoGenero);
                peliculaExiste = 1;
            }
            Pelicula peliculaMod2 = new Pelicula(peliculaMod.getId(), peliculaMod.getNombre(), peliculaMod.getDirector(),
                    peliculaMod.getGenero(), peliculaMod.getAnyo());
            dataOsAux.writeObject(peliculaMod2);

        }
        if (peliculaExiste > 0) {
            crearNuevaPeliculaMod();
            System.out.println("Se ha modificado la pelicula pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    // Funcion para modificar el atributo anyo de la clase pelicula
    public static void modificarPeliculaAnyo(int opcionModificar, int nuevoAnyo) throws IOException, ClassNotFoundException {
        Pelicula peliculaMod;
        File fichero = new File(".//Ficheros/Peliculaes.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int peliculaExiste = 0;

        File ficheroAux = new File(".//Ficheros/PeliculaesAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                peliculaMod = (Pelicula) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (peliculaMod.getId() == opcionModificar) {
                peliculaMod.setAnyo(nuevoAnyo);
                peliculaExiste = 1;
            }
            Pelicula peliculaMod2 = new Pelicula(peliculaMod.getId(), peliculaMod.getNombre(), peliculaMod.getDirector(),
                    peliculaMod.getGenero(), peliculaMod.getAnyo());
            dataOsAux.writeObject(peliculaMod2);

        }
        if (peliculaExiste > 0) {
            crearNuevaPeliculaMod();
            System.out.println("Se ha la pelicula pai");
        }
        dataIn.close();
        dataOsAux.close();
    }

    // Funcion para modificar el atributo director de la clase pelicula
    public static void modificarPeliculaDirector(int opcionModificar, String nuevoDirector) throws IOException, ClassNotFoundException {
        Pelicula peliculaMod;
        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;
        int peliculaExiste = 0;

        File ficheroAux = new File(".//Ficheros/PeliculasAux.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = null;

        while (fileIn.available() != 0) {
            dataIn = new MyInputObjectStream((fileIn));
            dataOsAux = new MyObjectOutputStream(fileOutAux);
            try {
                peliculaMod = (Pelicula) dataIn.readObject();
            } catch (EOFException e) {
                System.out.println("No hay datos");
                break;
            }
            if (peliculaMod.getId() == opcionModificar) {
                peliculaMod.setDirector(nuevoDirector);
                peliculaExiste = 1;
            }
            Pelicula peliculaMod2 = new Pelicula(peliculaMod.getId(), peliculaMod.getNombre(), peliculaMod.getDirector()
                    , peliculaMod.getGenero(), peliculaMod.getAnyo());
            dataOsAux.writeObject(peliculaMod2);

        }
        if (peliculaExiste > 0) {
            crearNuevaPeliculaMod();
            System.out.println("Se ha modificado la pelicula pai");
            dataOsAux.close();
            dataIn.close();
        }

    }

    // Funcion para borrar una entrada de tipo pelicula en el .dat
    public static void borrarPelicula(int peliculaBorrar) throws IOException, ClassNotFoundException {
        Pelicula peliculaB;
        ArrayList<Pelicula> peliculaes = new ArrayList<>();
        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileInputStream fileIn = new FileInputStream(fichero);
        MyInputObjectStream dataIn = null;

        File ficheroAux = new File(".//Ficheros/PeliculasAuxBorrar.dat");
        FileOutputStream fileOutAux = new FileOutputStream(ficheroAux);
        MyObjectOutputStream dataOsAux = new MyObjectOutputStream(fileOutAux);
        try {
            while (fileIn.available() != 0) {
                dataIn = new MyInputObjectStream(fileIn);

                peliculaB = (Pelicula) dataIn.readObject();
                peliculaes.add(peliculaB);
            }


            for (Pelicula pelicula : peliculaes) {
                if (pelicula.getId() != peliculaBorrar) {
                    dataOsAux.writeObject(pelicula);
                }
            }
            crearNuevaPeliculaBorrar();
            System.out.println("Se ha borrado de la lista");
            dataOsAux.close();
        } catch (EOFException e) {
            System.out.println("Se ha leido el fichero");
        }
        dataIn.close();
        dataOsAux.close();


    }

    // Funcion que usamos para escribir el fichero original con el auxiliar
    public static void crearNuevaPeliculaBorrar() throws IOException {
        Pelicula peliculaB;

        File ficheroAux = new File(".//Ficheros/PeliculasAuxBorrar.dat");
        FileInputStream fileInAux = new FileInputStream(ficheroAux);
        MyInputObjectStream dataInAux = new MyInputObjectStream(fileInAux);

        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);


        try {
            while (fileInAux.available() != 0) {
                MyObjectOutputStream dataOut = new MyObjectOutputStream(fileOut);
                peliculaB = (Pelicula) dataInAux.readObject();
                Pelicula peliculaB2 = new Pelicula(peliculaB.getId(), peliculaB.getNombre(), peliculaB.getDirector(),
                        peliculaB.getGenero(), peliculaB.getAnyo());
                dataOut.writeObject(peliculaB2);

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
    public static void crearNuevaPeliculaMod() throws IOException {
        Pelicula peliculaMod;

        File ficheroAux = new File(".//Ficheros/PeliculasAux.dat");
        FileInputStream fileInAux = new FileInputStream(ficheroAux);

        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);

        try {
            while (fileInAux.available() != 0) {
                MyObjectOutputStream dataOut = new MyObjectOutputStream(fileOut);
                MyInputObjectStream dataInAux = new MyInputObjectStream(fileInAux);
                peliculaMod = (Pelicula) dataInAux.readObject();
                Pelicula peliculaMod2 = new Pelicula(peliculaMod.getId(), peliculaMod.getNombre(), peliculaMod.getDirector(), peliculaMod.getGenero(), peliculaMod.getAnyo());
                dataOut.writeObject(peliculaMod2);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        fileOut.close();
        fileInAux.close();

    }

    // Funcion para crear un xml apartir del fichero peliculas.dat
    public static void crearXmlPelicula() throws IOException {
        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileInputStream fileIn = new FileInputStream(fichero);

        MyInputObjectStream dataIS = new MyInputObjectStream(fileIn);
        System.out.println("Comienza el proceso de creacion a XML..");
        ListaPeliculas listaP = new ListaPeliculas();
        try {
            while (true) {
                Pelicula pelicula = (Pelicula) dataIS.readObject();
                listaP.add(pelicula);
            }
        } catch (EOFException e) {
            System.out.println("Fichero leido");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        dataIS.close();
        try {
            XStream xStream = new XStream();
            xStream.alias("ListaPeliculas", ListaPeliculas.class);
            xStream.alias("DatosPelicula", Pelicula.class);
            xStream.addImplicitCollection(ListaPeliculas.class, "lista");
            xStream.toXML(listaP, new FileOutputStream(".//FicherosXML/Peliculas.xml"));
            System.out.println("Creado fichero xml...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funcion para leer el archivo xml y luego mostrarlo como una lista de objetos de tipo pelicula
    public static void leerXmlPelicula() throws FileNotFoundException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("ListaPeliculas", ListaPeliculas.class);

        xStream.alias("DatosPelicula", Pelicula.class);
        xStream.addImplicitCollection(ListaPeliculas.class, "lista");

        FileInputStream fichero = new FileInputStream(".//FicherosXML/Peliculas.xml");
        ListaPeliculas lista = (ListaPeliculas) xStream.fromXML(fichero);
        System.out.println("Numero de peliculas: " + lista.getListaPeliculas().size());
        List<Pelicula> listaPeliculas = new ArrayList<>();
        listaPeliculas = lista.getListaPeliculas();
        for (Pelicula pelicula : listaPeliculas) {
            System.out.printf("Id: %s, Nombre: %s, Director: %s, Genero: %s, Año: %s %n", pelicula.getId(), pelicula.getNombre(), pelicula.getDirector(), pelicula.getGenero(), pelicula.getAnyo());
        }
        System.out.println("Fin del listado....");
    }

}



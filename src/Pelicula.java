import java.io.*;
import java.util.ArrayList;

/**
 * @author Javier Jamaica
 * 28/10/2022
 */
public class Pelicula implements Serializable {
    private int id;
    private String nombre;
    private String director;
    private String genero;
    private int anyo;

    public Pelicula(int id, String nombre, String director, String genero, int anyo) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.genero = genero;
        this.anyo = anyo;
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


    public static void escribirPelicula(int id, String nombre, String director, String genero, int anyo) throws IOException {
        File fichero = new File(".//Ficheros/Peliculas.dat");
        FileOutputStream fileout = new FileOutputStream(fichero, true);
        MyObjectOutputStream dataOs = new MyObjectOutputStream(fileout);
        Pelicula pelicula = new Pelicula(id, nombre, director, genero, anyo);
        dataOs.writeObject(pelicula);
        dataOs.close();
        System.out.println("Se escribio la pelicula correctamente.");
    }

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

}



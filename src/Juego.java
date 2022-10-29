import java.io.*;
import java.util.ArrayList;

public class Juego implements Serializable {
    private static final long serialVersionUID = 1235495732780724037L;

    private int id;
    private String nombre;
    private int anyo;
    private int puntuacion;

    public Juego(int id, String nombre, int anyo, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.anyo = anyo;
        this.puntuacion = puntuacion;
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

    public static void escribirJuego(int id, String nombre, int anyo, int puntuacion) throws IOException {
        File fichero = new File(".//Ficheros/Juegos.dat");
        FileOutputStream fileout = new FileOutputStream(fichero, true);
        MyObjectOutputStream dataOs = new MyObjectOutputStream(fileout);
        Juego juego = new Juego(id, nombre, anyo, puntuacion);
        dataOs.writeObject(juego);
        dataOs.close();
        System.out.println("Se escribio el juego correctamente.");
    }

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
}

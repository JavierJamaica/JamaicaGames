import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final JPasswordField jpf = new JPasswordField();
        int opcion = 0;
        int opcionJuego = 0;
        int opcionXml = 0;
        int opcionModificar2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Binevenido! inicia sesion para continuar:");

        System.out.print("Usuario: ");
        String usuarioSesion = br.readLine();
        System.out.print("Contraseña: ");
        JOptionPane.showConfirmDialog(null, jpf, "Contraseña: ", JOptionPane.OK_CANCEL_OPTION);
        String contra = new String(jpf.getPassword());

        if (Usuario.leerListaUsuarios(usuarioSesion, contra)) {

            if (Usuario.leerListaUsuariosPriv(usuarioSesion, contra).equalsIgnoreCase("admin")) {
                do {
                    try {
                        System.out.print("""
                                Bienvenido admin escoge una opcion:\s
                                1. Juegos\s
                                2. Peliculas\s
                                3. Musica\s
                                4. Xml\s
                                5. Salir\s
                                """);
                        System.out.print("Opcion: ");
                        opcion = Integer.parseInt(br.readLine());

                        switch (opcion) {
                            case 1:
                                do {
                                    System.out.print("""
                                            Bienvenido al menu de juegos, que deseas hacer?\s
                                            1. Ver lista de juegos\s
                                            2. Agregar juego\s
                                            3. Modificar juego\s
                                            4. Borrar juego\s
                                            5. Atras\s
                                            """);
                                    try {
                                        System.out.print("Opcion: ");
                                        opcionJuego = Integer.parseInt(br.readLine());
                                        switch (opcionJuego) {
                                            case 1:
                                                try {
                                                    Juego.leerLista();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                    break;
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Escribe el id del juego: ");
                                                int id = Integer.parseInt(br.readLine());
                                                System.out.println("Escribe el nombre del juego: ");
                                                String nombre = br.readLine();
                                                System.out.println("Escribe el año del juego: ");
                                                int anyo = Integer.parseInt(br.readLine());
                                                System.out.println("Escribe una puntuacion: ");
                                                int puntuacion = Integer.parseInt(br.readLine());
                                                Juego.escribirJuego(id, nombre, anyo, puntuacion);
                                                break;
                                            case 3:
                                                do {
                                                    try {
                                                        Juego.leerLista();
                                                    } catch (FileNotFoundException e) {
                                                        System.out.println("No existe el fichero o no hay datos");
                                                        break;
                                                    }
                                                    System.out.println("Cual juego quieres modificar ");
                                                    System.out.print("Opcion: ");
                                                    int opcionModificar = Integer.parseInt(br.readLine());


                                                    System.out.print("""
                                                            Que deseas modificar?\s
                                                             1. Nombre\s
                                                             2. Año\s
                                                             3. Puntuacion
                                                             4. Atras\s
                                                            """);
                                                    System.out.println("Opcion: ");
                                                    opcionModificar2 = Integer.parseInt(br.readLine());
                                                    switch (opcionModificar2) {
                                                        case 1 -> {
                                                            System.out.println("Escribe el nuevo nombre: ");
                                                            String nuevoNom = br.readLine();
                                                            Juego.modificarJuegoNombre(opcionModificar, nuevoNom);
                                                        }
                                                        case 2 -> {
                                                            System.out.println("Escribe el nuevo año: ");
                                                            int nuevoAnyo = Integer.parseInt(br.readLine());
                                                            Juego.modificarJuegoAnyo(opcionModificar, nuevoAnyo);
                                                        }
                                                        case 3 -> {
                                                            System.out.println("Escribe la nueva puntuacion: ");
                                                            int nuevaPuntuacion = Integer.parseInt(br.readLine());
                                                            Juego.modificarPuntuacion(opcionModificar, nuevaPuntuacion);
                                                        }
                                                        default -> {
                                                        }
                                                    }

                                                } while (opcionModificar2 != 4);

                                                break;
                                            case 4:

                                                try {
                                                    Juego.leerLista();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                    break;
                                                }
                                                System.out.println("Cual deseas eliminar?");
                                                System.out.print("Opcion: ");
                                                int juegoBorrar = Integer.parseInt(br.readLine());
                                                Juego.borrarJuego(juegoBorrar);
                                                break;
                                            default:
                                                System.out.println("Tiene que ser una opcion valida");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error tiene que ser un numero!");
                                    } catch (ClassNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                } while (opcionJuego != 5);
                                break;
                            case 2:
                                do {
                                    System.out.print("""
                                            Bienvenido al menu de peliculas, que deseas hacer?\s
                                            1. Ver lista de peliculas\s
                                            2. Agregar pelicula\s
                                            3. Modificar pelicula\s
                                            4. Borrar pelicula\s
                                            5. Atras\s
                                            """);
                                    try {
                                        System.out.print("Opcion: ");
                                        opcionJuego = Integer.parseInt(br.readLine());
                                        switch (opcionJuego) {
                                            case 1:
                                                try {
                                                    Pelicula.leerListaPelicula();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                    break;
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Escribe el id de la pelicula: ");
                                                int id = Integer.parseInt(br.readLine());
                                                System.out.println("Escribe el nombre de la pelicula: ");
                                                String nombre = br.readLine();
                                                System.out.println("Escribe el nombre del director de la pelicula: ");
                                                String director = br.readLine();
                                                System.out.println("Escribe el genero de la pelicula: ");
                                                String genero = br.readLine();
                                                System.out.println("Escribe el año de la pelicula: ");
                                                int anyo = Integer.parseInt(br.readLine());
                                                Pelicula.escribirPelicula(id, nombre, director, genero, anyo);
                                                break;
                                            case 3:
                                                do {
                                                    try {
                                                        Pelicula.leerListaPelicula();
                                                    } catch (FileNotFoundException e) {
                                                        System.out.println("No existe el fichero o no hay datos");
                                                        break;
                                                    }
                                                    System.out.println("Cual pelicula quieres modificar ");
                                                    System.out.print("Opcion: ");
                                                    int opcionModificar = Integer.parseInt(br.readLine());


                                                    System.out.print("""
                                                            Que deseas modificar?\s
                                                             1. Nombre\s
                                                             2. Director\s
                                                             3. Genero\s
                                                             4. Año\s
                                                             5. Atras\s
                                                            """);
                                                    System.out.println("Opcion: ");
                                                    opcionModificar2 = Integer.parseInt(br.readLine());
                                                    switch (opcionModificar2) {
                                                        case 1 -> {
                                                            System.out.println("Escribe el nuevo nombre: ");
                                                            String nuevoNom = br.readLine();
                                                            Pelicula.modificarPeliculaNombre(opcionModificar, nuevoNom);
                                                        }
                                                        case 2 -> {
                                                            System.out.println("Escribe el nuevo director: ");
                                                            String nuevoDirector = br.readLine();
                                                            Pelicula.modificarPeliculaDirector(opcionModificar, nuevoDirector);
                                                        }
                                                        case 3 -> {
                                                            System.out.println("Escribe el nuevo genero: ");
                                                            String nuevoGenero = br.readLine();
                                                            Pelicula.modificarPeliculaGenero(opcionModificar, nuevoGenero);
                                                        }
                                                        case 4 -> {
                                                            System.out.println("Escribe el nuevo año de la cancion: ");
                                                            int nuevoAnyo = Integer.parseInt(br.readLine());
                                                            Pelicula.modificarPeliculaAnyo(opcionModificar, nuevoAnyo);
                                                        }
                                                        case 5 -> {

                                                        }
                                                        default -> {
                                                            System.out.println("Tiene que ser una opcion valida");
                                                        }
                                                    }

                                                } while (opcionModificar2 != 5);

                                                break;
                                            case 4:

                                                try {
                                                    Pelicula.leerListaPelicula();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                    break;
                                                }
                                                System.out.println("Cual deseas eliminar?");
                                                System.out.print("Opcion: ");
                                                int peliculaBorrar = Integer.parseInt(br.readLine());
                                                Pelicula.borrarPelicula(peliculaBorrar);
                                                break;
                                            default:
                                                System.out.println("Tiene que ser una opcion valida");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error tiene que ser un numero!");
                                    } catch (ClassNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                } while (opcionJuego != 5);
                                break;
                            case 3:
                                do {
                                    System.out.print("""
                                            Bienvenido al menu de musica, que deseas hacer?\s
                                            1. Ver lista de canciones\s
                                            2. Agregar cancion\s
                                            3. Modificar cancion\s
                                            4. Borrar cancion\s
                                            5. Atras\s
                                            """);
                                    try {
                                        System.out.print("Opcion: ");
                                        opcionJuego = Integer.parseInt(br.readLine());
                                        switch (opcionJuego) {
                                            case 1:
                                                try {
                                                    Cancion.leerListaCancion();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Escribe el id de la cancion: ");
                                                int id = Integer.parseInt(br.readLine());
                                                System.out.println("Escribe el nombre de la cancion: ");
                                                String nombre = br.readLine();
                                                System.out.println("Escribe el nombre del autor de la cancion: ");
                                                String autor = br.readLine();
                                                System.out.println("Escribe el genero de la cancion: ");
                                                String genero = br.readLine();
                                                System.out.println("Escribe el año de la cancion: ");
                                                int anyo = Integer.parseInt(br.readLine());
                                                Cancion.escribirCancion(id, nombre, autor, genero, anyo);
                                                break;
                                            case 3:
                                                do {
                                                    try {
                                                        Cancion.leerListaCancion();
                                                    } catch (FileNotFoundException e) {
                                                        System.out.println("No existe el fichero o no hay datos");
                                                    }
                                                    System.out.println("Cual cancion quieres modificar ");
                                                    System.out.print("Opcion: ");
                                                    int opcionModificar = Integer.parseInt(br.readLine());


                                                    System.out.print("""
                                                            Que deseas modificar?\s
                                                             1. Nombre\s
                                                             2. Autor\s
                                                             3. Genero\s
                                                             4. Año\s
                                                             5. Atras\s
                                                            """);
                                                    System.out.println("Opcion: ");
                                                    opcionModificar2 = Integer.parseInt(br.readLine());
                                                    switch (opcionModificar2) {
                                                        case 1 -> {
                                                            System.out.println("Escribe el nuevo nombre: ");
                                                            String nuevoNom = br.readLine();
                                                            Cancion.modificarCancionNombre(opcionModificar, nuevoNom);
                                                        }
                                                        case 2 -> {
                                                            System.out.println("Escribe el nuevo autor: ");
                                                            String nuevoAutor = br.readLine();
                                                            Cancion.modificarCancionAutor(opcionModificar, nuevoAutor);
                                                        }
                                                        case 3 -> {
                                                            System.out.println("Escribe el nuevo genero: ");
                                                            String nuevoGenero = br.readLine();
                                                            Cancion.modificarCancionGenero(opcionModificar, nuevoGenero);
                                                        }
                                                        case 4 -> {
                                                            System.out.println("Escribe el nuevo año de la cancion: ");
                                                            int nuevoAnyo = Integer.parseInt(br.readLine());
                                                            Cancion.modificarCancionAnyo(opcionModificar, nuevoAnyo);
                                                        }
                                                        case 5 -> {

                                                        }
                                                        default -> {
                                                            System.out.println("Tiene que ser una opcion valida");
                                                        }
                                                    }

                                                } while (opcionModificar2 != 5);

                                                break;
                                            case 4:

                                                try {
                                                    Cancion.leerListaCancion();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                }
                                                System.out.println("Cual deseas eliminar?");
                                                System.out.print("Opcion: ");
                                                int cancionBorrar = Integer.parseInt(br.readLine());
                                                Cancion.borrarCancion(cancionBorrar);
                                                break;
                                            default:
                                                System.out.println("Tiene que ser una opcion valida");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error tiene que ser un numero!");
                                    } catch (ClassNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                } while (opcionJuego != 5);
                                break;

                            case 4:
                                do {
                                    System.out.print("""
                                            Bienvenido al menu XML, que deseas hacer?\s
                                            1. Crear XML\s
                                            2. Ver XMl\s
                                            3. Atras\s
                                            """);
                                    try {
                                        System.out.print("Opcion: ");
                                        opcionXml = Integer.parseInt(br.readLine());
                                        switch (opcionXml) {
                                            case 1:
                                                int opcionXmlJuegos = 0;
                                                do {
                                                    System.out.print("""
                                                            De que lo quieres crear?
                                                            1. Juegos
                                                            2. Peliculas
                                                            3. Musica
                                                            4. Atras
                                                            """);
                                                    System.out.print("Opcion: ");
                                                    opcionXmlJuegos = Integer.parseInt(br.readLine());

                                                    switch (opcionXmlJuegos) {
                                                        case 1:
                                                            Juego.crearXmlJuego();
                                                            break;
                                                        case 2:
                                                            System.out.println("FuncionXmlPeliculas");
                                                            break;
                                                        case 3:
                                                            System.out.println("FuncionXmlMusica");
                                                            break;
                                                        case 4:
                                                            break;
                                                        default:
                                                            break;
                                                    }


                                                } while (opcionXmlJuegos != 4);
                                                break;
                                            case 2:
                                                int opcionXmlLeerJuegos = 0;
                                                do {
                                                    System.out.print("""
                                                            Cual quieres ver?
                                                            1. Juegos
                                                            2. Peliculas
                                                            3. Musica
                                                            4. Atras
                                                            """);
                                                    System.out.print("Opcion: ");
                                                    opcionXmlLeerJuegos = Integer.parseInt(br.readLine());

                                                    switch (opcionXmlLeerJuegos) {
                                                        case 1:
                                                            Juego.leerXmlJuego();
                                                            break;
                                                        case 2:
                                                            System.out.println("FuncionXmlPeliculas");
                                                            break;
                                                        case 3:
                                                            System.out.println("FuncionXmlMusica");
                                                            break;
                                                        case 4:
                                                            break;
                                                        default:
                                                            break;
                                                    }


                                                } while (opcionXmlLeerJuegos != 4);
                                                break;
                                            case 3:
                                                break;

                                            default:
                                                System.out.println("Tiene que ser una opcion valida");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error tiene que ser un numero!");
                                    }
                                } while (opcionXml != 3);

                            case 5:

                            default:
                                break;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Tiene que ser un numero!");
                    }
                } while (opcion != 5);
            } else {
                do {
                    try {
                        System.out.print("""
                                Bienvenido escoge una opcion:\s
                                1. Juegos\s
                                2. Peliculas\s
                                3. Musica\s
                                4. Xml\s
                                5. Salir\s
                                """);
                        System.out.print("Opcion: ");
                        opcion = Integer.parseInt(br.readLine());

                        switch (opcion) {
                            case 1:
                                do {
                                    System.out.print("""
                                            Bienvenido al menu de juegos, que deseas hacer?\s
                                            1. Ver lista de juegos\s
                                            2. Agregar juego\s
                                            3. Modificar juego\s
                                            4. Borrar juego\s
                                            5. Atras\s
                                            """);
                                    try {
                                        System.out.print("Opcion: ");
                                        opcionJuego = Integer.parseInt(br.readLine());
                                        switch (opcionJuego) {
                                            case 1:
                                                try {
                                                    Juego.leerLista();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                    break;
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Escribe el id del juego: ");
                                                int id = Integer.parseInt(br.readLine());
                                                System.out.println("Escribe el nombre del juego: ");
                                                String nombre = br.readLine();
                                                System.out.println("Escribe el año del juego: ");
                                                int anyo = Integer.parseInt(br.readLine());
                                                System.out.println("Escribe una puntuacion: ");
                                                int puntuacion = Integer.parseInt(br.readLine());
                                                Juego.escribirJuego(id, nombre, anyo, puntuacion);
                                                break;
                                            case 3:
                                                do {
                                                    try {
                                                        Juego.leerLista();
                                                    } catch (FileNotFoundException e) {
                                                        System.out.println("No existe el fichero o no hay datos");
                                                        break;
                                                    }
                                                    System.out.println("Cual juego quieres modificar ");
                                                    System.out.print("Opcion: ");
                                                    int opcionModificar = Integer.parseInt(br.readLine());


                                                    System.out.print("""
                                                            Que deseas modificar?\s
                                                             1. Nombre\s
                                                             2. Año\s
                                                             3. Puntuacion
                                                             4. Atras\s
                                                            """);
                                                    System.out.println("Opcion: ");
                                                    opcionModificar2 = Integer.parseInt(br.readLine());
                                                    switch (opcionModificar2) {
                                                        case 1 -> {
                                                            System.out.println("Escribe el nuevo nombre: ");
                                                            String nuevoNom = br.readLine();
                                                            Juego.modificarJuegoNombre(opcionModificar, nuevoNom);
                                                        }
                                                        case 2 -> {
                                                            System.out.println("Escribe el nuevo año: ");
                                                            int nuevoAnyo = Integer.parseInt(br.readLine());
                                                            Juego.modificarJuegoAnyo(opcionModificar, nuevoAnyo);
                                                        }
                                                        case 3 -> {
                                                            System.out.println("Escribe la nueva puntuacion: ");
                                                            int nuevaPuntuacion = Integer.parseInt(br.readLine());
                                                            Juego.modificarPuntuacion(opcionModificar, nuevaPuntuacion);
                                                        }
                                                        default -> {
                                                        }
                                                    }

                                                } while (opcionModificar2 != 4);

                                                break;
                                            case 4:

                                                try {
                                                    Juego.leerLista();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                    break;
                                                }
                                                System.out.println("Cual deseas eliminar?");
                                                System.out.print("Opcion: ");
                                                int juegoBorrar = Integer.parseInt(br.readLine());
                                                Juego.borrarJuego(juegoBorrar);
                                                break;
                                            default:
                                                System.out.println("Tiene que ser una opcion valida");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error tiene que ser un numero!");
                                    } catch (ClassNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                } while (opcionJuego != 5);
                                break;
                            case 2:
                                do {
                                    System.out.print("""
                                            Bienvenido al menu de peliculas, que deseas hacer?\s
                                            1. Ver lista de peliculas\s
                                            2. Agregar pelicula\s
                                            3. Modificar pelicula\s
                                            4. Borrar pelicula\s
                                            5. Atras\s
                                            """);
                                    try {
                                        System.out.print("Opcion: ");
                                        opcionJuego = Integer.parseInt(br.readLine());
                                        switch (opcionJuego) {
                                            case 1:
                                                try {
                                                    Pelicula.leerListaPelicula();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                    break;
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Escribe el id de la pelicula: ");
                                                int id = Integer.parseInt(br.readLine());
                                                System.out.println("Escribe el nombre de la pelicula: ");
                                                String nombre = br.readLine();
                                                System.out.println("Escribe el nombre del director de la pelicula: ");
                                                String director = br.readLine();
                                                System.out.println("Escribe el genero de la pelicula: ");
                                                String genero = br.readLine();
                                                System.out.println("Escribe el año de la pelicula: ");
                                                int anyo = Integer.parseInt(br.readLine());
                                                Pelicula.escribirPelicula(id, nombre, director, genero, anyo);
                                                break;
                                            case 3:
                                                do {
                                                    try {
                                                        Pelicula.leerListaPelicula();
                                                    } catch (FileNotFoundException e) {
                                                        System.out.println("No existe el fichero o no hay datos");
                                                        break;
                                                    }
                                                    System.out.println("Cual pelicula quieres modificar ");
                                                    System.out.print("Opcion: ");
                                                    int opcionModificar = Integer.parseInt(br.readLine());


                                                    System.out.print("""
                                                            Que deseas modificar?\s
                                                             1. Nombre\s
                                                             2. Director\s
                                                             3. Genero\s
                                                             4. Año\s
                                                             5. Atras\s
                                                            """);
                                                    System.out.println("Opcion: ");
                                                    opcionModificar2 = Integer.parseInt(br.readLine());
                                                    switch (opcionModificar2) {
                                                        case 1 -> {
                                                            System.out.println("Escribe el nuevo nombre: ");
                                                            String nuevoNom = br.readLine();
                                                            Pelicula.modificarPeliculaNombre(opcionModificar, nuevoNom);
                                                        }
                                                        case 2 -> {
                                                            System.out.println("Escribe el nuevo director: ");
                                                            String nuevoDirector = br.readLine();
                                                            Pelicula.modificarPeliculaDirector(opcionModificar, nuevoDirector);
                                                        }
                                                        case 3 -> {
                                                            System.out.println("Escribe el nuevo genero: ");
                                                            String nuevoGenero = br.readLine();
                                                            Pelicula.modificarPeliculaGenero(opcionModificar, nuevoGenero);
                                                        }
                                                        case 4 -> {
                                                            System.out.println("Escribe el nuevo año de la cancion: ");
                                                            int nuevoAnyo = Integer.parseInt(br.readLine());
                                                            Pelicula.modificarPeliculaAnyo(opcionModificar, nuevoAnyo);
                                                        }
                                                        case 5 -> {

                                                        }
                                                        default -> {
                                                            System.out.println("Tiene que ser una opcion valida");
                                                        }
                                                    }

                                                } while (opcionModificar2 != 5);

                                                break;
                                            case 4:

                                                try {
                                                    Pelicula.leerListaPelicula();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                    break;
                                                }
                                                System.out.println("Cual deseas eliminar?");
                                                System.out.print("Opcion: ");
                                                int peliculaBorrar = Integer.parseInt(br.readLine());
                                                Pelicula.borrarPelicula(peliculaBorrar);
                                                break;
                                            default:
                                                System.out.println("Tiene que ser una opcion valida");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error tiene que ser un numero!");
                                    } catch (ClassNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                } while (opcionJuego != 5);
                                break;
                            case 3:
                                do {
                                    System.out.print("""
                                            Bienvenido al menu de musica, que deseas hacer?\s
                                            1. Ver lista de canciones\s
                                            2. Agregar cancion\s
                                            3. Modificar cancion\s
                                            4. Borrar cancion\s
                                            5. Atras\s
                                            """);
                                    try {
                                        System.out.print("Opcion: ");
                                        opcionJuego = Integer.parseInt(br.readLine());
                                        switch (opcionJuego) {
                                            case 1:
                                                try {
                                                    Cancion.leerListaCancion();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Escribe el id de la cancion: ");
                                                int id = Integer.parseInt(br.readLine());
                                                System.out.println("Escribe el nombre de la cancion: ");
                                                String nombre = br.readLine();
                                                System.out.println("Escribe el nombre del autor de la cancion: ");
                                                String autor = br.readLine();
                                                System.out.println("Escribe el genero de la cancion: ");
                                                String genero = br.readLine();
                                                System.out.println("Escribe el año de la cancion: ");
                                                int anyo = Integer.parseInt(br.readLine());
                                                Cancion.escribirCancion(id, nombre, autor, genero, anyo);
                                                break;
                                            case 3:
                                                do {
                                                    try {
                                                        Cancion.leerListaCancion();
                                                    } catch (FileNotFoundException e) {
                                                        System.out.println("No existe el fichero o no hay datos");
                                                    }
                                                    System.out.println("Cual cancion quieres modificar ");
                                                    System.out.print("Opcion: ");
                                                    int opcionModificar = Integer.parseInt(br.readLine());


                                                    System.out.print("""
                                                            Que deseas modificar?\s
                                                             1. Nombre\s
                                                             2. Autor\s
                                                             3. Genero\s
                                                             4. Año\s
                                                             5. Atras\s
                                                            """);
                                                    System.out.println("Opcion: ");
                                                    opcionModificar2 = Integer.parseInt(br.readLine());
                                                    switch (opcionModificar2) {
                                                        case 1 -> {
                                                            System.out.println("Escribe el nuevo nombre: ");
                                                            String nuevoNom = br.readLine();
                                                            Cancion.modificarCancionNombre(opcionModificar, nuevoNom);
                                                        }
                                                        case 2 -> {
                                                            System.out.println("Escribe el nuevo autor: ");
                                                            String nuevoAutor = br.readLine();
                                                            Cancion.modificarCancionAutor(opcionModificar, nuevoAutor);
                                                        }
                                                        case 3 -> {
                                                            System.out.println("Escribe el nuevo genero: ");
                                                            String nuevoGenero = br.readLine();
                                                            Cancion.modificarCancionGenero(opcionModificar, nuevoGenero);
                                                        }
                                                        case 4 -> {
                                                            System.out.println("Escribe el nuevo año de la cancion: ");
                                                            int nuevoAnyo = Integer.parseInt(br.readLine());
                                                            Cancion.modificarCancionAnyo(opcionModificar, nuevoAnyo);
                                                        }
                                                        case 5 -> {

                                                        }
                                                        default -> {
                                                            System.out.println("Tiene que ser una opcion valida");
                                                        }
                                                    }

                                                } while (opcionModificar2 != 5);

                                                break;
                                            case 4:

                                                try {
                                                    Cancion.leerListaCancion();
                                                } catch (FileNotFoundException e) {
                                                    System.out.println("No existe el fichero o no hay datos");
                                                }
                                                System.out.println("Cual deseas eliminar?");
                                                System.out.print("Opcion: ");
                                                int cancionBorrar = Integer.parseInt(br.readLine());
                                                Cancion.borrarCancion(cancionBorrar);
                                                break;
                                            default:
                                                System.out.println("Tiene que ser una opcion valida");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error tiene que ser un numero!");
                                    } catch (ClassNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                } while (opcionJuego != 5);
                                break;

                            case 4:
                                do {
                                    System.out.print("""
                                            Bienvenido al menu XML, que deseas hacer?\s
                                            1. Crear XML\s
                                            2. Ver XMl\s
                                            3. Atras\s
                                            """);
                                    try {
                                        System.out.print("Opcion: ");
                                        opcionXml = Integer.parseInt(br.readLine());
                                        switch (opcionXml) {
                                            case 1:
                                                int opcionXmlJuegos = 0;
                                                do {
                                                    System.out.print("""
                                                            De que lo quieres crear?
                                                            1. Juegos
                                                            2. Peliculas
                                                            3. Musica
                                                            4. Atras
                                                            """);
                                                    System.out.print("Opcion: ");
                                                    opcionXmlJuegos = Integer.parseInt(br.readLine());

                                                    switch (opcionXmlJuegos) {
                                                        case 1:
                                                            Juego.crearXmlJuego();
                                                            break;
                                                        case 2:
                                                            System.out.println("FuncionXmlPeliculas");
                                                            break;
                                                        case 3:
                                                            System.out.println("FuncionXmlMusica");
                                                            break;
                                                        case 4:
                                                            break;
                                                        default:
                                                            break;
                                                    }


                                                } while (opcionXmlJuegos != 4);
                                                break;
                                            case 2:
                                                int opcionXmlLeerJuegos = 0;
                                                do {
                                                    System.out.print("""
                                                            Cual quieres ver?
                                                            1. Juegos
                                                            2. Peliculas
                                                            3. Musica
                                                            4. Atras
                                                            """);
                                                    System.out.print("Opcion: ");
                                                    opcionXmlLeerJuegos = Integer.parseInt(br.readLine());

                                                    switch (opcionXmlLeerJuegos) {
                                                        case 1:
                                                            Juego.leerXmlJuego();
                                                            break;
                                                        case 2:
                                                            System.out.println("FuncionXmlPeliculas");
                                                            break;
                                                        case 3:
                                                            System.out.println("FuncionXmlMusica");
                                                            break;
                                                        case 4:
                                                            break;
                                                        default:
                                                            break;
                                                    }


                                                } while (opcionXmlLeerJuegos != 4);
                                                break;
                                            case 3:
                                                break;

                                            default:
                                                System.out.println("Tiene que ser una opcion valida");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error tiene que ser un numero!");
                                    }
                                } while (opcionXml != 3);

                            case 5:

                            default:
                                break;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Tiene que ser un numero!");
                    }
                } while (opcion != 5);
            }


            System.out.println("Adios " + "\uD83D\uDE01");
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }


    }
}
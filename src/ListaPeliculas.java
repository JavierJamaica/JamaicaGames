import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Jamaica
 * 29/10/2022
 */
// Clase que usamos para la creacion del xml de peliculas
public class ListaPeliculas {
    private List<Pelicula> lista = new ArrayList<>();

    public ListaPeliculas() {
    }

    public void add(Pelicula peliculas) {
        lista.add(peliculas);
    }

    public List<Pelicula> getListaPeliculas() {
        return lista;
    }
}

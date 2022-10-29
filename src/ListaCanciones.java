import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Jamaica
 * 29/10/2022
 */

// Clase que usamos para la creacion del xml de canciones
public class ListaCanciones {
    private List<Cancion> lista = new ArrayList<>();

    public ListaCanciones() {
    }

    public void add(Cancion canciones) {
        lista.add(canciones);
    }

    public List<Cancion> getListaCanciones() {
        return lista;
    }
}

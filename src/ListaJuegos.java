import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Jamaica
 * 29/10/2022
 */
public class ListaJuegos {

    private List<Juego> lista = new ArrayList<>();

    public ListaJuegos() {
    }

    public void add(Juego juego) {
        lista.add(juego);
    }

    public List<Juego> getListaJuegos() {
        return lista;
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Jamaica
 * 29/10/2022
 */
public class ListaUsuarios {
    private List<Usuario> lista = new ArrayList<>();

    public ListaUsuarios() {
    }

    public void add(Usuario usuario) {
        lista.add(usuario);
    }

    public List<Usuario> getListaUsuarios() {
        return lista;
    }
}

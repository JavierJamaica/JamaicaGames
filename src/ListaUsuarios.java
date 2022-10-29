import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Jamaica
 * 29/10/2022
 */
// Clase que usamos para la creacion del xml de usuarios
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

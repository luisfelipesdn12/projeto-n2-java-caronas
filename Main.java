import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Repositorio repositorio = new Repositorio();
        Menu menu = new Menu(repositorio);
        Optional<Usuario> usuarioLogado = Optional.ofNullable(null);

        while (usuarioLogado.isEmpty()) {
            usuarioLogado = menu.login();
        }

        System.out.println("horray");
    }
}

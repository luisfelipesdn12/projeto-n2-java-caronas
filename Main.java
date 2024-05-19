public class Main {
    public static void main(String[] args) {
        Repositorio repositorio = new Repositorio();
        Menu menu = new Menu(repositorio);

        System.out.println(repositorio.getUsuarioPeloLogin("anasilva").isPresent());

        while (menu.usuarioLogado.isEmpty()) {
            menu.mostrarInicio();
        }

        menu.mostrarInicioLogado();
    }
}

public class Main {
    public static void main(String[] args) {
        // Cria um repositorio para acesso aos dados
        Repositorio repositorio = new Repositorio();

        // Instancia uma classe utilitária de menu
        Menu menu = new Menu(repositorio);

        while (menu.estaRodando) {
            // Se não há um usuário logado, mostra o início
            if (menu.usuarioLogado.isEmpty()) {
                menu.mostrarInicio();
            } else {
                // Se estiver, mostra as opções do usuário logado
                menu.mostrarInicioLogado();
            }
        }
    }
}

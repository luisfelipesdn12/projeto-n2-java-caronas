import java.util.Optional;
import java.util.Scanner;

public class Menu {
    Scanner scanner;
    Repositorio repositorio;
    Optional<Usuario> usuarioLogado;

    public Menu(Repositorio repositorio) {
        scanner = new Scanner(System.in);
        this.repositorio = repositorio;
        this.usuarioLogado = Optional.ofNullable(null);
    }

    private void mostrarCabecalho() {
        System.out.println(
            "\n CarONE-M: Viagens compartilhadas\n\n" +
            "           ______\n" +
            "           /|_||_\\`.__\n" +
            "          (   _    _ _\\\n" +
            "          =`-(_)--(_)-'\n\n"
        );
    }

    private void limparTerminal() {
        System.out.println(System.lineSeparator().repeat(50));
    }

    private void mostrarErro(String mensagem) {
        System.err.println("\nERRO: " + mensagem);
        System.out.println("Pressione qualquer tecla para prosseguir...\n");
        scanner.nextLine();
    }

    public void mostrarInicio() {
        limparTerminal();
        mostrarCabecalho();
        System.out.println("SELECIONE UM \n");
    }

    public void login() {
        limparTerminal();
        mostrarCabecalho();
        System.out.println("ENTRE NA SUA CONTA\n");
        System.out.print("login: ");
        String login = scanner.nextLine();

        Optional<Usuario> usuario = repositorio.getUsuarioPeloLogin(login);

        if (usuario.isPresent()) {
            System.out.print("senha: ");
            String senha = scanner.nextLine();

            if (usuario.get().validarSenha(senha)) {
                this.usuarioLogado = usuario;
            } else {
                mostrarErro("Senha incorreta.");
            }
        } else {
            mostrarErro("Login não existe.");
        }
    }

    public void mostrarInicioLogado() {
        limparTerminal();
        mostrarCabecalho();

        switch (usuarioLogado.get().getTipoUsuario()) {
            case MOTORISTA:
                System.out.println(
                    "LOGADO COMO: " + usuarioLogado.get().getNome() +
                    " (motorista)\n\n" +
                    "1) Cadastrar um novo usuário\n" +
                    "2) Cadastrar uma viagem\n" +
                    "3) Buscar por carona\n" +
                    "4) Avaliar uma viagem\n" +
                    "5) Sair\n" +
                    "Selecione uma opção:"
                );
                break;

            case PASSAGEIRO:
                System.out.println(
                    "LOGADO COMO: " + usuarioLogado.get().getNome() +
                    " (passageiro)\n"
                );
                break;
        
            default:
                break;
        }
    }
}

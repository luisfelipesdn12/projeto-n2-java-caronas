import java.util.ArrayList;
import java.util.Arrays;
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

    private int escolhaDeOpcao(ArrayList<String> opcoes) {        
        for (int i = 0; i < opcoes.size(); i++) {
            System.out.println((i + 1) + ". " + opcoes.get(i));
        }

        int opcao = 0;
        
        while (!(opcao >= 1 && opcao <= opcoes.size())) {
            System.out.print("\nSELECIONE UMA OPÇÃO: ");
            opcao = scanner.nextInt();
        }

        return opcao;
    }

    public void mostrarInicio() {
        limparTerminal();
        mostrarCabecalho();
        int opcao = escolhaDeOpcao(new ArrayList<>(Arrays.asList("Login", "Registro")));

        if (opcao == 1) {
            login();
        } else {
            registro();
        }
    }

    public void registro() {
        limparTerminal();
        mostrarCabecalho();
        System.out.println("REGISTRE UMA NOVA CONTA\n");
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    Scanner scanner;
    Validador validador;
    Repositorio repositorio;
    Optional<Usuario> usuarioLogado;

    public Menu(Repositorio repositorio) {
        this.scanner = new Scanner(System.in);
        this.validador = new Validador();
        this.repositorio = repositorio;
        this.usuarioLogado = Optional.ofNullable(null);
    }

    private void mostrarCabecalho() {
        System.out.println(
                "\n CarONE-M: Viagens compartilhadas\n\n" +
                        "           ______\n" +
                        "           /|_||_\\`.__\n" +
                        "          (   _    _ _\\\n" +
                        "          =`-(_)--(_)-'\n\n");
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

        scanner.nextLine();
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

        int tipo = escolhaDeOpcao(new ArrayList<>(Arrays.asList("Passageiro", "Motorista")));

        TipoUsuario tipoUsuario = tipo == 1 ? TipoUsuario.PASSAGEIRO : TipoUsuario.MOTORISTA;

        String nome;
        do {
            System.out.print("Digite seu nome: ");
            nome = scanner.nextLine();
        } while (!validador.validarNome(nome));

        String telefone;
        do {
            System.out.print("Digite seu telefone: ");
            telefone = scanner.nextLine();
        } while (!validador.validarTelefone(telefone));

        String email;
        do {
            System.out.print("Digite seu email: ");
            email = scanner.nextLine();
        } while (!validador.validarEmail(email));

        String endereco;
        do {
            System.out.print("Digite seu endereco: ");
            endereco = scanner.nextLine();
        } while (!validador.validarEndereco(endereco));

        String login;
        do {
            System.out.print("Digite seu login: ");
            login = scanner.nextLine();

            if (repositorio.getUsuarioPeloLogin(login).isPresent()) {
                login = "";
            }
        } while (!validador.validarLogin(login));

        String senha;
        do {
            System.out.print("Digite seu senha: ");
            senha = scanner.nextLine();
        } while (!validador.validarSenha(senha));

        if (tipoUsuario == TipoUsuario.MOTORISTA) {
            repositorio.incluirMotorista(
                    new Motorista(nome, endereco, email, telefone, login, senha));
        } else if (tipoUsuario == TipoUsuario.PASSAGEIRO) {
            repositorio.incluirPassageiro(
                    new Passageiro(nome, endereco, email, telefone, login, senha));
        }
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

        int opcao;

        switch (usuarioLogado.get().getTipoUsuario()) {
            case MOTORISTA:
                // TODO: Adicionar opções apenas de motorista
                System.out.println(
                        "LOGADO COMO: " + usuarioLogado.get().getNome() +
                                " (motorista)\n");

                opcao = escolhaDeOpcao(new ArrayList<>(Arrays.asList(
                    "Cadastrar viagem",
                    "Confirmar",
                    "Sair"
                )));
                break;

            case PASSAGEIRO:
                // TODO: Adicionar opções apenas de passageiro
                System.out.println(
                        "LOGADO COMO: " + usuarioLogado.get().getNome() +
                                " (passageiro)\n");
                opcao = escolhaDeOpcao(new ArrayList<>(Arrays.asList(
                    "Buscar carona",
                    "Listar viagens",
                    "Sair"
                )));
                break;

            default:
                break;
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    Scanner scanner;
    Validador validador;
    Repositorio repositorio;
    Optional<Usuario> usuarioLogado;
    boolean estaRodando;

    public Menu(Repositorio repositorio) {
        this.scanner = new Scanner(System.in);
        this.validador = new Validador();
        this.repositorio = repositorio;
        this.estaRodando = true;
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

    private int escolhaDeOpcao(ArrayList<? extends Object> opcoes, boolean opcaoSair) {
        for (int i = 0; i < opcoes.size(); i++) {
            System.out.println((i + 1) + ". " + opcoes.get(i).toString());
        }

        if (opcaoSair) {
            System.out.println((opcoes.size() + 1) + ". Sair");
        }

        int opcao = 0;

        while (!(opcao >= 1 && (opcaoSair ? opcao <= opcoes.size() + 1 : opcao <= opcoes.size()))) {
            System.out.print("\nSELECIONE UMA OPÇÃO: ");
            opcao = scanner.nextInt();
        }

        scanner.nextLine();
        return opcao;
    }

    public void mostrarInicio() {
        limparTerminal();
        mostrarCabecalho();
        int opcao = escolhaDeOpcao(new ArrayList<>(Arrays.asList("Login", "Registro")), true);

        if (opcao == 1) {
            login();
        } else if (opcao == 2) {
            registro();
        } else {
            this.estaRodando = false;
        }
    }

    public void registro() {
        limparTerminal();
        mostrarCabecalho();
        System.out.println("REGISTRE UMA NOVA CONTA\n");

        int tipo = escolhaDeOpcao(new ArrayList<>(Arrays.asList("Passageiro", "Motorista")), false);

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

    public ArrayList<Local> cadastrarTrajeto(){
        limparTerminal();
        mostrarCabecalho();
        ArrayList<Local> locais = new ArrayList<Local>();
        System.out.print("Digite quantos pontos de locais o trajeto terá: ");
        int quantidadeDeLocais = scanner.nextInt();
        for (int i = 1; i <= quantidadeDeLocais; i++){
            System.out.printf("Digite o ponto x do local %d: ", i);
            int xLocal = scanner.nextInt();
            System.out.printf("Digite o ponto y do local %d: ", i);
            int yLocal = scanner.nextInt();
            Local local = new Local(xLocal, yLocal);
            locais.add(local);
        }
        return locais;
    }

    public void cadastrarViagem() {
        limparTerminal();
        mostrarCabecalho();
        System.out.print("Digite o ponto x do local de partida: ");
        int xPartida = scanner.nextInt();
        System.out.print("Digite o ponto y do local de partida: ");
        int yPartida = scanner.nextInt();
        System.out.print("Digite o ponto x do local de destino: ");
        int xDestino = scanner.nextInt();
        System.out.print("Digite o ponto y do local de destino: ");
        int yDestino = scanner.nextInt();
        ArrayList<Local> trajeto = cadastrarTrajeto();
        System.out.print("Digite a quantidade disponivel de lugares no veículo: ");
        int lugares = scanner.nextInt();
        Local partida = new Local(xPartida, yPartida);
        Local destino = new Local(xDestino, yDestino);
        Viagem viagem = new Viagem(partida, destino, trajeto, lugares, (Motorista)usuarioLogado.get());
        repositorio.incluirViagem(viagem);
        System.out.println("Viagem cadastrada com sucesso!");
    }

    public void buscarCarona() {
        limparTerminal();
        mostrarCabecalho();
        System.out.print("Digite o ponto x do local de partida: ");
        int xPartida = scanner.nextInt();
        System.out.print("Digite o ponto y do local de partida: ");
        int yPartida = scanner.nextInt();
        System.out.print("Digite o ponto x do local de destino: ");
        int xDestino = scanner.nextInt();
        System.out.print("Digite o ponto y do local de destino: ");
        int yDestino = scanner.nextInt();
        
        Local partida = new Local(xPartida, yPartida);
        Local destino = new Local(xDestino, yDestino);

        ArrayList<Viagem> viagensCompativeis = repositorio.getTodasAsViagensCompativeis(partida, destino);

        if (viagensCompativeis.isEmpty()) {
            System.out.println("Nenhuma viagem encontrada...");
        } else {
            listarViagens(viagensCompativeis);
            int viagemEscolhida = escolhaDeOpcao(viagensCompativeis, true);

            // Se a opção não for sair
            if (viagemEscolhida != viagensCompativeis.size() + 1) {
                viagensCompativeis.get(viagemEscolhida - 1).embarque((Passageiro)usuarioLogado.get());
            }

        }
    }

    public void listarViagens(ArrayList<Viagem> viagens) {
        System.out.println();
        int viagemAtual = 1;
        for (Viagem viagem : viagens){
            int parada = 1;
            System.out.printf("========== Viagem %d ==========\n", viagemAtual);
            System.out.printf("Local de partida: %s\n", viagem.getPartida().toString());
            System.out.printf("Local de destino: %s\n", viagem.getDestino().toString());
            for (Local local : viagem.getTrajeto()){
                System.out.printf("Parada %d do trajeto: %s\n", parada, local.toString());
                parada += 1;
            }
            System.out.printf("Lugares no carro: %d\n", viagem.getLugares());
            System.out.println("==============================");
            System.out.println("");
            viagemAtual += 1;
        }
    }

    public void listarViagensAnteriores() {
        ArrayList<Viagem> viagensAnteriores = repositorio.getTodasAsViagensDoPassageiro(usuarioLogado.get());

        if (viagensAnteriores.isEmpty()) {
            System.out.println("Nenhuma viagem encontrada...");
        } else {
            listarViagens(viagensAnteriores);
        }
        
        System.out.println("Pressione qualquer tecla para prosseguir...\n");
        scanner.nextLine();
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
                    "Confirmar"
                )), true);
                if (opcao == 1){
                    cadastrarViagem();
                } else if (opcao == 2){
                    System.out.println("Teste opcao 2");
                } else if (opcao == 3) {
                    this.usuarioLogado = Optional.ofNullable(null);
                }

            case PASSAGEIRO:
                // TODO: Adicionar opções apenas de passageiro
                System.out.println(
                        "LOGADO COMO: " + usuarioLogado.get().getNome() +
                                " (passageiro)\n");
                opcao = escolhaDeOpcao(new ArrayList<>(Arrays.asList(
                    "Buscar carona",
                    "Listar viagens anteriores"
                )), true);
                if (opcao == 1){
                    buscarCarona();
                } else if (opcao == 2 ){
                    listarViagensAnteriores();
                } else if (opcao == 3) {
                    this.usuarioLogado = Optional.ofNullable(null);
                }

            default:
                break;
        }
    }
}

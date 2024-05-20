import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Servirá para leitura e escrita de dados.
 * Pode ser fácilmente substituido por chamadas à um banco de dados ou API.
 */
public class Repositorio {
    private ArrayList<Motorista> motoristas;
    private ArrayList<Passageiro> passageiros;
    private ArrayList<Viagem> viagens;

    public Repositorio() {
        this.motoristas = new ArrayList<Motorista>();
        this.motoristas.add(new Motorista("Carlos Dias", "Rua XV de Novembro, 30", "carlos.dias@profissao.com.br", "(32) 2345-6789", "carlosdiasz", "supersecreto"));
        this.motoristas.add(new Motorista("Gabriela Pereira", "Beco do Sossego, 1", "gabriela.pereira@outlook.com", "(71) 8901-2345", "gabipereira", "mudar123!"));
        this.motoristas.add(new Motorista("Felipe Costa", "Travessa da Amizade, 456", "felipe.costa@ig.com.br", "(54) 4567-8901", "felipecostabr", "senhadificil"));
        this.motoristas.add(new Motorista("Isabela Vargas", "Rua da Harmonia, 90", "isabela.vargas@yahoo.com.br", "(92) 2109-3456", "isabelavargas", "1q2w3e4r"));
        this.motoristas.add(new Motorista("Daniel Mendes", "Avenida Atlântica, 1000", "daniel.mendes@uol.com.br", "(13) 7890-1234", "danielmendes", "senhasegura2024"));

        this.passageiros = new ArrayList<Passageiro>();
        this.passageiros.add(new Passageiro("Ana Silva", "Rua das Flores, 123", "ana.silva@email.com", "(11) 98765-4321", "anasilva", "123456"));
        this.passageiros.add(new Passageiro("Pedro Santos", "Avenida Paulista, 500", "pedro.santos@empresa.com.br", "(21) 3210-9876", "pedrosantos", "senhaforte"));
        this.passageiros.add(new Passageiro("Maria Oliveira", "Praça da Liberdade, s/n", "maria.oliveira@gmail.com", "(41) 5432-1098", "mariazinha", "abc123"));
        this.passageiros.add(new Passageiro("João Souza", "Rua da Paz, 789", "joao.souza@hotmail.com", "(81) 9123-4567", "joaosouza123", "minha123$"));
        this.passageiros.add(new Passageiro("Beatriz Campos", "Avenida Brasil, 2000", "bia.campos@provedor.com", "(65) 6789-0123", "biazinha", "qwerty"));

        this.viagens = new ArrayList<Viagem>();
        this.viagens.add(new Viagem(
            new Local(-2, -2),
            new Local(8, 8),
            new ArrayList<Local>(Arrays.asList(new Local(0, 2), new Local(4, 4))),
            4,
            this.motoristas.get(2)
        ));
        this.viagens.get(0).solicitar(this.passageiros.get(0));
    }

    public ArrayList<Usuario> getTodosOsUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        for (Usuario motorista : motoristas) {
            usuarios.add(motorista);
        }

        for (Usuario passageiro : passageiros) {
            usuarios.add(passageiro);
        }

        return usuarios;
    }

    public ArrayList<Viagem> getTodasAsViagens(Usuario usuario) {
        ArrayList<Viagem> viagens = new ArrayList<>();

        // Para cada viagem
        for (Viagem viagem : this.viagens) {
            if (usuario.tipoUsuario == TipoUsuario.PASSAGEIRO) {
                // Pegue todos os passageiros
                for (Passageiro p : viagem.getPassageiros()) {
                    // Se o passageiro procurado estiver incluido
                    if (p.getLogin() == usuario.getLogin()) {
                        // Adciona na lista
                        viagens.add(viagem);
                    }
                }
            } else if (usuario.tipoUsuario == TipoUsuario.MOTORISTA) {
                if (viagem.getMotorista().getLogin() == usuario.getLogin()) {
                    viagens.add(viagem);
                }
            }
        }

        return viagens;
    }

    public ArrayList<Viagem> getTodasAsViagensComSolicitacao(Motorista motorista) {
        ArrayList<Viagem> viagensComSolicitacao = new ArrayList<>();
        ArrayList<Viagem> viagensDoMotorista = getTodasAsViagens(motorista);

        for (Viagem viagem : viagensDoMotorista) {
            if (viagem.getSolicitacoes().size() > 0) {
                viagensComSolicitacao.add(viagem);
            }
        }

        return viagensComSolicitacao;
    }

    public ArrayList<Viagem> getTodasAsSolicitacoesDoPassageiro(Usuario passageiro) {
        ArrayList<Viagem> viagensSolicitadas = new ArrayList<>();

        // Para cada viagem
        for (Viagem viagem : this.viagens) {
            // Pegue todos os passageiros
            for (Passageiro p : viagem.getSolicitacoes()) {
                // Se o passageiro procurado estiver incluido
                if (p.getLogin() == passageiro.getLogin()) {
                    // Adciona na lista
                    viagensSolicitadas.add(viagem);
                }
            }
        }

        return viagensSolicitadas;
    }

    public ArrayList<Viagem> getTodasAsViagensCompativeis(Local partida, Local destino) {
        ArrayList<Viagem> viagensCompativeis = new ArrayList<>();

        // Para cada viagem
        for (Viagem viagem : this.viagens) {
            /**
             * Tem qualquer ponto perto da partida
             */
            boolean pertoDaPartida = false;
            /**
             * Tem qualquer ponto perto do destino
             */
            boolean pertoDoDestino = false;

            /**
             * Todos os pontos da viagem, incluindo partida, trajeto e destino.
             */
            ArrayList<Local> locaisViagemCompleta  = new ArrayList<>();

            // Adiciona partida
            locaisViagemCompleta.add(viagem.getPartida());

            // Adiciona cada ponto to trajeto
            for (Local local : viagem.getTrajeto()) {
                locaisViagemCompleta.add(local);
            }

            // Adiciona destino
            locaisViagemCompleta.add(viagem.getDestino());

            // Pegue todos os locais da viagem
            for (Local local : locaisViagemCompleta) {
                // Verifique se o local é perto da partida
                if (local.ePerto(partida)) {
                    pertoDaPartida = true;
                }

                // Verifique se o local é perto do destino
                if (local.ePerto(destino)) {
                    pertoDoDestino = true;
                }
            }

            // Se a viagem tem locais que são perto da partida
            // e do destino e tem lugares adicione nas viagens.
            if (pertoDaPartida && pertoDoDestino && viagem.temLugaresDisponiveis()) {
                viagensCompativeis.add(viagem);
            }
        }

        return viagensCompativeis;
    }

    public Optional<Usuario> getUsuarioPeloLogin(String login) {
        return Stream.of(getTodosOsUsuarios())
            .flatMap(usuario -> usuario.stream())
            .filter(usuario -> usuario.getLogin().equals(login))
            .findFirst();
    }

    public void incluirMotorista(Motorista motorista) {
        motoristas.add(motorista);
    }

    public void incluirPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }

    public void incluirViagem(Viagem viagem) {
        viagens.add(viagem);
    }
}

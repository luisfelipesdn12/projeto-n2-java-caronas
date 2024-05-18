import java.util.ArrayList;

/**
 * Servirá para leitura e escrita de dados.
 * Pode ser fácilmente substituido por chamadas à um banco de dados ou API.
 */
public class Repositorio {
    private ArrayList<Motorista> motoristas;
    private ArrayList<Passageiro> passageiros;

    public Repositorio() {
        this.motoristas = new ArrayList<Motorista>() {{
            new Usuario("Carlos Dias", "Rua XV de Novembro, 30", "carlos.dias@profissao.com.br", "(32) 2345-6789", "carlosdiasz", "supersecreto");
            new Usuario("Gabriela Pereira", "Beco do Sossego, 1", "gabriela.pereira@outlook.com", "(71) 8901-2345", "gabipereira", "mudar123!");
            new Usuario("Felipe Costa", "Travessa da Amizade, 456", "felipe.costa@ig.com.br", "(54) 4567-8901", "felipecostabr", "senhadificil");
            new Usuario("Isabela Vargas", "Rua da Harmonia, 90", "isabela.vargas@yahoo.com.br", "(92) 2109-3456", "isabelavargas", "1q2w3e4r");
            new  Usuario("Daniel Mendes", "Avenida Atlântica, 1000", "daniel.mendes@uol.com.br", "(13) 7890-1234", "danielmendes", "senhasegura2024");
            new Usuario("Carlos Dias", "Rua XV de Novembro, 30", "carlos.dias@profissao.com.br", "(32) 2345-6789", "carlosdiasz", "supersecreto");
        }};

        this.passageiros = new ArrayList<Passageiro>() {{
            new Usuario("Ana Silva", "Rua das Flores, 123", "ana.silva@email.com", "(11) 98765-4321", "anasilva", "123456");
            new Usuario("Pedro Santos", "Avenida Paulista, 500", "pedro.santos@empresa.com.br", "(21) 3210-9876", "pedrosantos", "senhaforte");
            new Usuario("Maria Oliveira", "Praça da Liberdade, s/n", "maria.oliveira@gmail.com", "(41) 5432-1098", "mariazinha", "abc123");
            new Usuario("João Souza", "Rua da Paz, 789", "joao.souza@hotmail.com", "(81) 9123-4567", "joaosouza123", "minha123$");
            new Usuario("Beatriz Campos", "Avenida Brasil, 2000", "bia.campos@provedor.com", "(65) 6789-0123", "biazinha", "qwerty");
        }};
    }
}
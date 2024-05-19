import java.util.ArrayList;

public class Viagem {
    /**
     * Quantidade de lugares disponíveis no veículo (sem contar o motorista)
     */
    private int lugares;
    /**
     * O trajeto é descrito por um conjunto de locais pelo qual o motorista
     * pretende passar, inclusive que servem de pontos para o embarque e
     * desembarque de outros passageiros.
     */
    private ArrayList<Local> trajeto;
    private ArrayList<Passageiro> passageiros;
    private Avaliacao avaliacao;
    private Local partida;
    private Local destino;
}

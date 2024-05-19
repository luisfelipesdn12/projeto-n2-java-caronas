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

    public Viagem(Local partida, Local destino, ArrayList<Local> trajeto, int lugares){
        this.partida = partida;
        this.destino = destino;
        this.trajeto = trajeto;
        this.lugares = lugares;
        passageiros = new ArrayList<Passageiro>();
    }

    public void embarque(Passageiro passageiro){
        if (passageiros.size() < lugares){
            passageiros.add(passageiro);
        } else {
            System.out.println("Quantidade de lugares disponiveis para embarque esgotada");
        }
    }

    public void desembarque(Passageiro passageiro){
        if (passageiros.size() > 0) {
            passageiros.remove(passageiro);
        } else {
            System.out.println("Não possui passageiros para desembarcar nessa viagem!");
        }
    }

    public Local getPartida(){
        return partida;
    }

    public Local getDestino(){
        return destino;
    }

    public ArrayList<Local> getTrajeto(){
        return trajeto;
    }

    public int getLugares(){
        return lugares;
    }
}

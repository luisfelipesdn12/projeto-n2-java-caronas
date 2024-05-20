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
    private Motorista motorista;
    private ArrayList<Passageiro> passageiros;
    private ArrayList<Passageiro> solicitacoes;
    private ArrayList<Avaliacao> avaliacoes;
    private Local partida;
    private Local destino;

    public Viagem(Local partida, Local destino, ArrayList<Local> trajeto, int lugares, Motorista motorista){
        this.partida = partida;
        this.destino = destino;
        this.trajeto = trajeto;
        this.lugares = lugares;
        this.motorista = motorista;
        passageiros = new ArrayList<Passageiro>();
        solicitacoes = new ArrayList<Passageiro>();
        avaliacoes = new ArrayList<Avaliacao>();
    }

    public void solicitar(Passageiro passageiro){
        solicitacoes.add(passageiro);
    }

    public void negar(Passageiro passageiro){
        solicitacoes.remove(passageiro);
    }

    public void aprovar(Passageiro passageiro){
        solicitacoes.remove(passageiro);
        passageiros.add(passageiro);
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

    public Motorista getMotorista() {
        return motorista;
    }
    
    public ArrayList<Passageiro> getPassageiros() {
        return passageiros;
    }

    public ArrayList<Passageiro> getSolicitacoes() {
        return solicitacoes;
    }

    public ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public boolean temLugaresDisponiveis() {
        return passageiros.size() < lugares;
    }

    public int lugaresDisponiveis() {
        return lugares - passageiros.size();
    }

    public boolean temAvaliacaoDe(Passageiro passageiro) {
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getPassageiro().getLogin() == passageiro.getLogin()) {
                return true;
            }
        }

        return false;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    @Override
    public String toString() {
        return "Viagem de (" + partida.toString() + ") para (" + destino.toString() + ") com " + lugaresDisponiveis() + " lugares disponíveis e " + solicitacoes.size() + (solicitacoes.size() == 1 ? " solicitação." : " solicitações.") ;
    }
}

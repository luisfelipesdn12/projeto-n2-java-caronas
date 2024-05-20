public class Avaliacao {
    private int nota;
    private String comentario;

    private Passageiro passageiro;

    public Avaliacao(int nota, Passageiro passageiro) {
        this.nota = nota;
        this.passageiro = passageiro;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    @Override
    public String toString() {
        return "(" + nota + " estrelas) - " + comentario + " - " + passageiro.toString();
    }
}

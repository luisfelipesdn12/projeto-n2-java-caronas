public class Local {
    /**
     * Define a distancia em kilometros em que
     * um ponto é considerado perto do outro.
     */
    private final int DISTANCIA_PERTO = 2;
    private int x;
    private int y;

    public Local(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distancia(Local local) {
        double soma = Math.pow(local.x - x, 2) + Math.pow(local.y - y, 2);
        return Math.sqrt(soma);
    }

    public boolean ePerto(Local local) {
        double distancia = distancia(local);
        return distancia <= DISTANCIA_PERTO;
    }

    @Override
    public String toString() {
        return "x = " + x + " | y = " + y;
    }
}

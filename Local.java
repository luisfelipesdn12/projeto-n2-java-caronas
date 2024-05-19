public class Local {
    private int x;
    private int y;

    public Local(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "x = " + x + " | y = " + y;
    }
}

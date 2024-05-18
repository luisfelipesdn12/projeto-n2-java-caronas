public class Passageiro extends Usuario {
    public Passageiro(String nome, String endereco, String email, String telefone, String login, String senha) {
        super(nome, endereco, email, telefone, login, senha);
        this.tipoUsuario = TipoUsuario.MOTORISTA;
    }
}

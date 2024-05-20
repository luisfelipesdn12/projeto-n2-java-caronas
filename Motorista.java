public class Motorista extends Usuario {
    public Motorista(String nome, Local endereco, String email, String telefone, String login, String senha) {
        super(nome, endereco, email, telefone, login, senha);
        this.tipoUsuario = TipoUsuario.MOTORISTA;
    }
}

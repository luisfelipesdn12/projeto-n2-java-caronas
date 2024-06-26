
public class Usuario {
    private String nome;
    private Local endereco;
    private String email;
    private String telefone;
    private String login;
    private String senha;
    protected TipoUsuario tipoUsuario;

    public Usuario(String nome, Local endereco, String email, String telefone, String login, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public String getNome() {
        return nome;
    }

    public Local getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return (tipoUsuario == TipoUsuario.MOTORISTA ? "Motorista: " : "Passageiro: ") +
            nome + " - " +
            email + " - " +
            telefone;
    }

    String toStringComplete() {
        return "nome: " + nome +
            "\nendereco: " + endereco +
            "\nemail: " + email +
            "\ntelefone: " + telefone +
            "\nlogin: " + login +
            "\nsenha: " + senha +
            "\ntipoUsuario: " + tipoUsuario;
    }
}

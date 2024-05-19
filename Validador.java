public class Validador {
    public boolean validarNome(String nome) {
        return nome.matches("[A-Za-záàâãéèêéuúìíôõóòüûñ ]+");
    }

    public boolean validarEndereco(String endereco) {
        return endereco.contains(" ") && (endereco.contains(",") || endereco.contains("-"));
    }

    public boolean validarEmail(String email) {
        return email.matches("^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$");
    }

    public boolean validarTelefone(String telefone) {
        return telefone.matches("[0-9\\-\\(\\)]+");
    }

    public boolean validarLogin(String login) {
        return login.matches("^[a-zA-Z0-9_]+$");
    }

    public boolean validarSenha(String senha) {
        return senha.matches("^.{4,15}$");
    }
}

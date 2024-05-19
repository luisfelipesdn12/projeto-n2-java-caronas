public class Validador {
    public boolean validarNome(String nome) {
        return nome.matches("[A-Za-záàâãéèêéuúìíôõóòüûñ ]+");
    }

    public boolean validarEndereço(String endereco) {
        return endereco.contains(" ") && (endereco.contains(",") || endereco.contains("-"));
    }

    public boolean validarEmail(String email) {
        return email.matches("^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$");
    }

    public boolean validarTelefone(String telefone) {
        return telefone.matches("[0-9\\-\\(\\)]+");
    }
}

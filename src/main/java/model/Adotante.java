package model;

public class Adotante extends Pessoa {
    private String cpf;

    public String getCpf(){
        return cpf;

    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static class Builder extends Pessoa.PessoaBuilder<Builder> {
        private String cpf;

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Adotante build() {
            Adotante adotante = new Adotante();
            adotante.setNome(nome);
            adotante.setEmail(email);
            adotante.setSenha(senha);
            adotante.setTelefone(telefone);
            adotante.setEndereco(endereco);
            adotante.cpf = cpf;
            return adotante;
        }
    }
}
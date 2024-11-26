package model;

public class Abrigo extends Pessoa {
    private String cnpj;


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public static class Builder extends Pessoa.PessoaBuilder<Builder> {
        private String cnpj;

        public Builder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Abrigo build() {
            Abrigo abrigo = new Abrigo();
            abrigo.setNome(nome);
            abrigo.setEmail(email);
            abrigo.setSenha(senha);
            abrigo.setTelefone(telefone);
            abrigo.setEndereco(endereco);
            abrigo.cnpj = cnpj;
            return abrigo;
        }
    }
}
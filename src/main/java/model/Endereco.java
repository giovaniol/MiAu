package model;

public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;


    private Endereco(EnderecoBuilder builder) {
        this.logradouro = builder.logradouro;
        this.numero = builder.numero;
        this.complemento = builder.complemento;
        this.bairro = builder.bairro;
        this.cep = builder.cep;
        this.cidade = builder.cidade;
        this.estado = builder.estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public static class EnderecoBuilder {
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String cep;
        private String cidade;
        private String estado;

        public EnderecoBuilder setLogradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public EnderecoBuilder setNumero(String numero) {
            this.numero = numero;
            return this;
        }

        public EnderecoBuilder setComplemento(String complemento) {
            this.complemento = complemento;
            return this;
        }

        public EnderecoBuilder setBairro(String bairro) {
            this.bairro = bairro;
            return this;
        }

        public EnderecoBuilder setCep(String cep) {
            this.cep = cep;
            return this;
        }

        public EnderecoBuilder setCidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public EnderecoBuilder setEstado(String estado) {
            this.estado = estado;
            return this;
        }


        public Endereco build() {
            return new Endereco(this);
        }
    }
}

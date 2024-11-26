package model;

public abstract class Pessoa {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private Endereco endereco;


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static abstract class PessoaBuilder<T extends PessoaBuilder<T>> {
        protected String nome;
        protected String email;
        protected String senha;
        protected String telefone;
        protected Endereco endereco;

        public T nome(String nome) {
            this.nome = nome;
            return self();
        }

        public T email(String email) {
            this.email = email;
            return self();
        }

        public T senha(String senha) {
            this.senha = senha;
            return self();
        }

        public T telefone(String telefone) {
            this.telefone = telefone;
            return self();
        }

        public T endereco(Endereco endereco) {
            this.endereco = endereco;
            return self();
        }

        protected abstract T self();

        public abstract Pessoa build();
    }

}

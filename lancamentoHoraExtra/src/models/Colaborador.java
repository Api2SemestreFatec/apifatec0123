package models;

public class Colaborador {

    private String nome;
    private String matricula;
    private String funcao;
    private String email;
    private String login;
    private String nivelDeAcesso;

    public Colaborador(String nome, String matricula, String funcao, String email, String login, String nivelDeAcesso) {
        this.nome = nome;
        this.matricula = matricula;
        this.funcao = funcao;
        this.email = email;
        this.login = login;
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(String nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }
}

package br.sisacademico.model;

public class Aluno {

    //Atributos:
    private int idAluno;
    private int ra;
    private String nome;
    private Curso curso;

    //Construtores:
    public Aluno() {
    }

    public Aluno(int idAluno, int ra, String nome, Curso curso) {
        this.idAluno = idAluno;
        this.ra = ra;
        this.nome = nome;
        this.curso = curso;
    }

    //Métodos modificadores de acesso (sets e gets):
    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
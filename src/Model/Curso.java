package Model;

public class Curso implements Comparable<Curso> {
    private String nome;
    private int codigo;
    private int duracaoSemestres;

    // ? construtor
    public Curso(String nome, int codigo, int duracaoSemestres){
        this.nome = nome;
        this.codigo = codigo;
        this.duracaoSemestres = duracaoSemestres;
    }

    // ? getters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    // ? setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDuracaoSemestres(int duracaoSemestres) {
        this.duracaoSemestres = duracaoSemestres;
    }

    @Override
    public int compareTo(Curso o) {
        return Integer.compare((this.codigo), o.codigo);
    }

    @Override
    public String toString(){
        return codigo + " - " + nome;
    }
}

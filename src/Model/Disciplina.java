package Model;

public class Disciplina implements Comparable<Disciplina> {
    private String nome;
    private int codigo;
    private int cargaHoraria;

    // ? construtor
    public Disciplina(String nome, int codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    // ? getters
    public int getCodigo(){
        return codigo;
    }

    public String getNome(){
        return nome;
    }

    public int getCargaHoraria(){
        return cargaHoraria;
    }

    // ? setters
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCargaHoraria(int cargaHoraria){
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public int compareTo(Disciplina o){
        return Integer.compare(this.codigo, o.codigo);
    }

   @Override
    public String toString() {
        return codigo + " - " + getNome();
    }
}

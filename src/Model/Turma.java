package Model;


public class Turma implements Comparable<Turma> {
    private int id;
    private String ano;
    private int semestre;

    // ? construtor
    public Turma(int id, String ano, int semestre) {
        this.id = id;
        this.ano = ano;
        this.semestre = semestre;
    }

    // ? getters
    public int getId() {
        return id;
    }

    public String getAno() {
        return ano;
    }
    public int getSemestre(){
        return semestre;
    }
    
    // ? setters
    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    @Override
    public int compareTo(Turma o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return "Turma " + id + " (" + ano + "/" + semestre + ")";
    }

}
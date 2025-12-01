package Model;

public class Aluno extends Pessoa implements Comparable<Aluno>{
    private String curso;
    private int matricula;
    
    // ? construtor
    public Aluno(String nome, String cpf, String telefone, String email, String curso, int matricula) {
        super(nome, cpf, telefone, email);
        this.curso = curso;
        this.matricula = matricula;
    }

    // ? getters
    public String getCurso() {
        return curso;
    }

    public int getMatricula() {
        return matricula;
    }

    // ? setters
    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public int compareTo(Aluno other) {
        return Integer.compare(this.matricula, other.matricula);
    }
}

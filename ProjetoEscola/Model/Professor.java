package ProjetoEscola.Model;

public class Professor extends Pessoa {
    private String disciplina;
    private double salario;
    
    public Professor(String nome, String cpf, String telefone, String email, String disciplina, double salario) {
        super(nome, cpf, telefone, email);
        this.disciplina = disciplina;
        this.salario = salario;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public double getSalario() {
        return salario;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }    
}
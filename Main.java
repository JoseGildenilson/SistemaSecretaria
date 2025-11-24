

import ProjetoEscola.Model.Aluno;
import ProjetoEscola.Model.Pessoa;
import Services.ServiceAluno;

public class Main {

    public static void main(String[] args) {
        ServiceAluno serviceAluno = new ServiceAluno();

        System.out.println("=== Teste completo de ServiceAluno ===");

        // Criar alguns alunos
        Aluno aluno1 = new Aluno("João", "123.456.789-00", "99999-1111", "joao@email.com", "Engenharia", 1);
        Aluno aluno2 = new Aluno("Maria", "987.654.321-00", "88888-2222", "maria@email.com", "Medicina", 2);
        Aluno aluno3 = new Aluno("Carlos", "111.222.333-44", "77777-3333", "carlos@email.com", "Direito", 3);

        // Testar inserção
        System.out.println("\nInserindo alunos...");
        serviceAluno.inserir(aluno1.getMatricula(), aluno1);
        serviceAluno.inserir(aluno2.getMatricula(), aluno2);
        serviceAluno.inserir(aluno3.getMatricula(), aluno3);

        // Tentar inserir duplicado
        System.out.println("\nInserindo aluno duplicado (matricula 1)...");
        try {
            serviceAluno.inserir(aluno1.getMatricula(), aluno1);
            System.out.println("Inserção duplicada não causou erro.");
        } catch (Exception e) {
            System.out.println("Erro esperado ao inserir duplicado: " + e.getMessage());
        }

        // Listar alunos
        System.out.println("\nListando alunos em ordem:");
        serviceAluno.imprimirEmOrdem(serviceAluno.getArvoreAVLCopy());

        // Testar buscar existente
        System.out.println("\nBuscando aluno com matrícula 2:");
        try {
            Aluno aluno = serviceAluno.buscar(2);
            System.out.println("Aluno encontrado: " + aluno);
        } catch (Exception e) {
            System.out.println("Aluno não encontrado: " + e.getMessage());
        }
        // Testar buscar não existente
        System.out.println("\nBuscando aluno com matrícula 999 (não existente):");
        try {
            serviceAluno.buscar(999);
        } catch (Exception e) {
            System.out.println("Aluno não encontrado: " + e.getMessage());
        }

        // Testar remover existente
        System.out.println("\nRemovendo aluno com matrícula 2:");
        serviceAluno.remover(2);
        System.out.println("Após remoção:");
        serviceAluno.imprimirEmOrdem(serviceAluno.getArvoreAVLCopy());

        // Testar remover não existente
        System.out.println("\nTentando remover aluno com matrícula 999 (não existente):");
        serviceAluno.remover(999);

        // Testar inserir pessoa inválida
        System.out.println("\nTentando inserir Pessoa inválida (não Aluno):");
        try {
            Pessoa p = new Pessoa("Nome", "cpf", "tel", "email");
            serviceAluno.inserir(10, p);
        } catch (Exception e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\nTeste completo finalizado.");
    }
}

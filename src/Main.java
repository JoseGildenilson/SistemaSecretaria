import java.util.Scanner;
import Services.*;
import Model.*;
import Exception.NaoEncontradoException;

public class Main {
    private static ServiceAluno serviceAluno = new ServiceAluno();
    private static ServiceProfessor serviceProfessor = new ServiceProfessor();
    private static ServiceDisciplina serviceDisciplina = new ServiceDisciplina();
    private static ServiceCurso serviceCurso = new ServiceCurso();
    private static ServiceTurma serviceTurma = new ServiceTurma();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        
        // . Loop principal do programa 
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE GESTÃO ACADÊMICA ===");
            System.out.println("1. Gerir Alunos");
            System.out.println("2. Gerir Professores");
            System.out.println("3. Gerir Disciplinas");
            System.out.println("4. Gerir Cursos");
            System.out.println("5. Gerir Turmas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                String entrada = scanner.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1: menuAluno(); break;
                case 2: menuProfessor(); break;
                case 3: menuDisciplina(); break;
                case 4: menuCurso(); break;
                case 5: menuTurma(); break;
                case 0: System.out.println("A encerrar sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    // ? Menus 

    // . Menu Aluno 
    private static void menuAluno() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE ALUNOS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar");
            System.out.println("6. Árvore");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            
            opcao = lerInteiro();
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Matrícula (número): "); int mat = lerInteiro();
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("CPF: "); String cpf = scanner.nextLine();
                        System.out.print("Telefone: "); String tel = scanner.nextLine();
                        System.out.print("Email: "); String email = scanner.nextLine();
                        System.out.print("Curso: "); String curso = scanner.nextLine();
                        serviceAluno.inserir(new Aluno(nome, cpf, tel, email, curso, mat));
                        System.out.println("Sucesso!");
                        break;
                    case 2:

                        System.out.print("Digite a Matrícula: ");
                        Aluno a = serviceAluno.buscar(lerInteiro());
                        System.out.println("--------------------------------------------------");
                        System.out.println("Matrícula: " + a.getMatricula());
                        System.out.println("Nome:      " + a.getNome());
                        System.out.println("Curso:     " + a.getCurso());
                        System.out.println("CPF:       " + a.getCpf());
                        System.out.println("Telefone:  " + a.getTelefone());
                        System.out.println("Email:     " + a.getEmail());
                        System.out.println("--------------------------------------------------");
                        
                        break;
                        
                    case 3:
                        
                        System.out.print("Digite a Matrícula para atualizar: ");
                        int matUp = lerInteiro();
                        Aluno alUp = serviceAluno.buscar(matUp);
                        
                        System.out.println("--- Atualizando (Pressione ENTER para manter o valor atual) ---");
                        
                        System.out.print("Nome (" + alUp.getNome() + "): ");
                        String nNome = scanner.nextLine();
                        if (nNome.isEmpty()) nNome = alUp.getNome();

                        System.out.print("Telefone (" + alUp.getTelefone() + "): ");
                        String nTel = scanner.nextLine();
                        if (nTel.isEmpty()) nTel = alUp.getTelefone();

                        System.out.print("Email (" + alUp.getEmail() + "): ");
                        String nEmail = scanner.nextLine();
                        if (nEmail.isEmpty()) nEmail = alUp.getEmail();

                        System.out.print("Curso (" + alUp.getCurso() + "): ");
                        String nCurso = scanner.nextLine();
                        if (nCurso.isEmpty()) nCurso = alUp.getCurso();
                        
                        serviceAluno.atualizar(matUp, new Aluno(nNome, alUp.getCpf(), nTel, nEmail, nCurso, matUp));
                        System.out.println("Dados atualizados!");
                        
                        break;
                    case 4:
                        
                        System.out.print("Digite a Matrícula para remover: ");
                        int matriculaRemover = lerInteiro();
                        Aluno alunoRemover = serviceAluno.buscar(matriculaRemover);
                        serviceAluno.remover(matriculaRemover);
                        System.out.println("Aluno(a) '" + alunoRemover.getNome() + "' removido(a) com sucesso!");
                    
                        break;
                    case 5:
                        serviceAluno.listar();
                        break;
                    case 6:
                        serviceAluno.exibirArvore();
                        break;
                    case 0:
                        System.out.println("A voltar...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    // . Menu Professoress 
    private static void menuProfessor() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE PROFESSORES ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar");
            System.out.println("6. Árvore");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();
            
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("ID: "); int id = lerInteiro();
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("CPF: "); String cpf = scanner.nextLine();
                        System.out.print("Tel: "); String tel = scanner.nextLine();
                        System.out.print("Email: "); String email = scanner.nextLine();
                        System.out.print("Disciplina: "); String disc = scanner.nextLine();
                        System.out.print("Salário: "); double sal = Double.parseDouble(scanner.nextLine());
                        serviceProfessor.inserir(new Professor(nome, cpf, tel, email, disc, sal, id));
                        break;
                    case 2:
                        
                        System.out.print("ID: "); 
                        Professor p = serviceProfessor.buscar(lerInteiro());
                        System.out.println("--------------------------------------------------");
                        System.out.println("ID:          " + p.getId());
                        System.out.println("Nome:        " + p.getNome());
                        System.out.println("Disciplina:  " + p.getDisciplina());
                        System.out.println("Salário:     R$ " + String.format("%.2f", p.getSalario()));
                        System.out.println("Telefone:    " + p.getTelefone());
                        System.out.println("Email:       " + p.getEmail());
                        System.out.println("--------------------------------------------------");

                        break;
                    case 3:
                        System.out.print("ID para atualizar: "); int idUp = lerInteiro();
                        Professor pUp = serviceProfessor.buscar(idUp);
                        
                        System.out.println("--- Atualizando (Pressione ENTER para manter o valor atual) ---");

                        System.out.print("Nome (" + pUp.getNome() + "): ");
                        String nNome = scanner.nextLine();
                        if (nNome.isEmpty()) nNome = pUp.getNome();

                        System.out.print("Tel (" + pUp.getTelefone() + "): ");
                        String nTel = scanner.nextLine();
                        if (nTel.isEmpty()) nTel = pUp.getTelefone();

                        System.out.print("Email (" + pUp.getEmail() + "): ");
                        String nEmail = scanner.nextLine();
                        if (nEmail.isEmpty()) nEmail = pUp.getEmail();

                        System.out.print("Disciplina (" + pUp.getDisciplina() + "): ");
                        String nDisc = scanner.nextLine();
                        if (nDisc.isEmpty()) nDisc = pUp.getDisciplina();

                        System.out.print("Salário (" + pUp.getSalario() + "): ");
                        String inputSal = scanner.nextLine();
                        double nSal = inputSal.isEmpty() ? pUp.getSalario() : Double.parseDouble(inputSal);

                        serviceProfessor.atualizar(idUp, new Professor(nNome, pUp.getCpf(), nTel, nEmail, nDisc, nSal, idUp));
                        System.out.println("Professor atualizado!");
                        break;
                    case 4:
                        
                        System.out.print("Digite o ID para remover: ");
                        int idRemover = lerInteiro();
                        Professor professorRemover = serviceProfessor.buscar(idRemover);
                        serviceProfessor.remover(idRemover);
                        System.out.println("Professor(a) '" + professorRemover.getNome() + "' removido(a) com sucesso!");
                        
                        break;
                    case 5: serviceProfessor.listar(); break;
                    case 6: serviceProfessor.exibirArvore(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }
    }

    // . Menu Disciplinas 
    private static void menuDisciplina() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE DISCIPLINAS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar");
            System.out.println("6. Árvore");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Código: "); int cod = lerInteiro();
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("Carga Horária: "); int ch = lerInteiro();
                        serviceDisciplina.inserir(new Disciplina(nome, cod, ch));
                        break;
                    case 2:
                        
                        System.out.print("Código: "); 
                        Disciplina d = serviceDisciplina.buscar(lerInteiro());
                        System.out.println("--------------------------------------------------");
                        System.out.println("Código:        " + d.getCodigo());
                        System.out.println("Nome:          " + d.getNome());
                        System.out.println("Carga Horária: " + d.getCargaHoraria() + "h");
                        System.out.println("--------------------------------------------------");

                        break;
                    case 3:
                        
                    System.out.print("Código: "); int codUp = lerInteiro();
                        Disciplina dUp = serviceDisciplina.buscar(codUp);

                        System.out.print("Nome (" + dUp.getNome() + "): ");
                        String nNome = scanner.nextLine();
                        if (nNome.isEmpty()) nNome = dUp.getNome();

                        System.out.print("Carga Horária (" + dUp.getCargaHoraria() + "): ");
                        String inputCH = scanner.nextLine();
                        int nCH = inputCH.isEmpty() ? dUp.getCargaHoraria() : Integer.parseInt(inputCH);

                        serviceDisciplina.atualizar(codUp, new Disciplina(nNome, codUp, nCH));
                        System.out.println("Disciplina atualizada!");
                        
                        break;
                    case 4:
                        
                        System.out.print("Digite o Código para remover: ");
                        int codigoRemover = lerInteiro();
                        Disciplina disciplinaRemover = serviceDisciplina.buscar(codigoRemover);
                        serviceDisciplina.remover(codigoRemover);
                        System.out.println("Disciplina '" + disciplinaRemover.getNome() + "' removida com sucesso!");
                        
                        break;
                    case 5: serviceDisciplina.listar(); break;
                    case 6: serviceDisciplina.exibirArvore(); break;
                    case 0: break;
                }
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }
    }

   // . Menu Cursos 
    private static void menuCurso() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE CURSOS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar");
            System.out.println("6. Árvore");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Código: "); int cod = lerInteiro();
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("Duração (semestres): "); int dur = lerInteiro();
                        serviceCurso.inserir(new Curso(nome, cod, dur));
                        break;
                    case 2:
                        
                        System.out.print("Código: "); 
                        Curso c = serviceCurso.buscar(lerInteiro());
                        System.out.println("--------------------------------------------------");
                        System.out.println("Código:   " + c.getCodigo());
                        System.out.println("Nome:     " + c.getNome());
                        System.out.println("Duração:  " + c.getDuracaoSemestres() + " semestres");
                        System.out.println("--------------------------------------------------");

                        break;
                    case 3:
                        
                        System.out.print("Código: "); int codUp = lerInteiro();
                        Curso cUp = serviceCurso.buscar(codUp);

                        System.out.print("Nome (" + cUp.getNome() + "): ");
                        String nNome = scanner.nextLine();
                        if (nNome.isEmpty()) nNome = cUp.getNome();

                        System.out.print("Duração (" + cUp.getDuracaoSemestres() + "): ");
                        String inputDur = scanner.nextLine();
                        int nDur = inputDur.isEmpty() ? cUp.getDuracaoSemestres() : Integer.parseInt(inputDur);

                        serviceCurso.atualizar(codUp, new Curso(nNome, codUp, nDur));
                        System.out.println("Curso atualizado!");
                        
                        break;
                    case 4:
                        
                        System.out.print("Digite o Código para remover: ");
                        int codigoRemover = lerInteiro();
                        Curso cursoRemoveCurso = serviceCurso.buscar(codigoRemover);
                        serviceCurso.remover(codigoRemover);
                        System.out.println("Curso '" + cursoRemoveCurso.getNome() + "' removido com sucesso!");
                        
                        break;
                    case 5: serviceCurso.listar(); break;
                    case 6: serviceCurso.exibirArvore(); break;
                    case 0: break;
                }
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }
    }

    // . Menu Turmas 
    private static void menuTurma() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE TURMAS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar");
            System.out.println("6. Árvore");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("ID da Turma: "); int id = lerInteiro();
                        System.out.print("Ano (ex: 2024): "); String ano = scanner.nextLine();
                        System.out.print("Semestre (1 ou 2): "); int sem = lerInteiro();
                        System.out.print("Cód. Disciplina: "); int codDisc = lerInteiro();
                        serviceTurma.inserir(new Turma(id, ano, sem, codDisc));
                        break;
                    case 2:
                        
                        System.out.print("ID: "); 
                        Turma t = serviceTurma.buscar(lerInteiro());
                        System.out.println("--------------------------------------------------");
                        System.out.println("ID da Turma:     " + t.getId());
                        System.out.println("Ano/Semestre:    " + t.getAno() + "/" + t.getSemestre());
                        System.out.println("--------------------------------------------------");
                        
                        break;
                    case 3:
                        
                        System.out.print("ID: "); int idUp = lerInteiro();
                        Turma tUp = serviceTurma.buscar(idUp);

                        System.out.print("Ano (" + tUp.getAno() + "): ");
                        String nAno = scanner.nextLine();
                        if (nAno.isEmpty()) nAno = tUp.getAno();

                        System.out.print("Semestre (" + tUp.getSemestre() + "): ");
                        String inputSem = scanner.nextLine();
                        int nSem = inputSem.isEmpty() ? tUp.getSemestre() : Integer.parseInt(inputSem);

                        serviceTurma.atualizar(idUp, new Turma(idUp, nAno, nSem, 0));
                        System.out.println("Turma atualizada!");
                        
                        break;
                    case 4:
                        
                        System.out.print("Digite o ID da Turma para remover: ");
                        int idTurmaRemover = lerInteiro();
                        Turma turmaRemoveTurma = serviceTurma.buscar(idTurmaRemover);
                        serviceTurma.remover(idTurmaRemover);
                        System.out.println("Turma " + turmaRemoveTurma.getId() + " (" + turmaRemoveTurma.getAno() + ") removida com sucesso!");
                        
                        break;
                    case 5: serviceTurma.listar(); break;
                    case 6: serviceTurma.exibirArvore(); break;
                    case 0: break;
                }
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }
    }

    // ? Métodos auxiliares 

    // . Método para ler e garantindo o não problema com buffer 
    private static int lerInteiro() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
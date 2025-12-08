import Services.*;
import Model.*;
import Exception.NaoEncontradoException;
import Utils.InputHandler;

public class Main {
    private static ServiceAluno serviceAluno = new ServiceAluno();
    private static ServiceProfessor serviceProfessor = new ServiceProfessor();
    private static ServiceDisciplina serviceDisciplina = new ServiceDisciplina();
    private static ServiceCurso serviceCurso = new ServiceCurso();
    private static ServiceTurma serviceTurma = new ServiceTurma();

    public static void main(String[] args) {
        int opcao = -1;

        System.out.println("Deseja carregar dados de exemplo? (S/N)");
        String resposta = InputHandler.lerString("Resposta: ").toUpperCase();
        if (resposta.equals("S") || resposta.equals("SIM")) {
            carregarDadosIniciais();
        }

        // . Loop principal do programa
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE GESTÃO ACADÊMICA ===");
            System.out.println("1. Gerir Alunos");
            System.out.println("2. Gerir Professores");
            System.out.println("3. Gerir Disciplinas");
            System.out.println("4. Gerir Cursos");
            System.out.println("5. Gerir Turmas");
            System.out.println("0. Sair");

            opcao = InputHandler.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    menuAluno();
                    break;
                case 2:
                    menuProfessor();
                    break;
                case 3:
                    menuDisciplina();
                    break;
                case 4:
                    menuCurso();
                    break;
                case 5:
                    menuTurma();
                    break;
                case 0:
                    System.out.println("A encerrar sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
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

        opcao = InputHandler.lerInt("Opção: ");
        try {
            switch (opcao) {
                case 1:
                    System.out.println("\n-- Novo Aluno --");

                    int matricula = InputHandler.lerInt("Matrícula (número): ");

                    if (serviceAluno.existe(matricula)) {
                        System.out
                                .println("ERRO: Já existe um aluno cadastrado com a matrícula " + matricula + ".");
                    } else {
                        // Pedir Turma - DEPENDÊNCIA OBRIGATÓRIA
                        int idTurma = InputHandler.lerInt("ID da Turma: ");
                        if (!serviceTurma.existe(idTurma)) {
                            System.out.println("ERRO: Turma não encontrada.");
                        } else if (!serviceTurma.possuiTodos(idTurma)) {
                            System.out
                                    .println("ERRO: A turma precisa ter Curso, Disciplina e Professor vinculados!");
                        } else {
                            String nome = InputHandler.lerString("Nome: ");
                            String cpf = InputHandler.lerString("CPF: ");
                            String tel = InputHandler.lerString("Telefone: ");
                            String email = InputHandler.lerString("Email: ");
                            String curso = InputHandler.lerString("Curso: ");

                            Aluno aluno = new Aluno(nome, cpf, tel, email, curso, matricula);
                            aluno.setTurma(serviceTurma.buscar(idTurma));
                            serviceAluno.inserir(aluno);
                            System.out.println("Aluno cadastrado com sucesso!");
                        }
                    }
                    break;
                case 2:
                    int matBusca = InputHandler.lerInt("Digite a Matrícula: ");
                    Aluno a = serviceAluno.buscar(matBusca);
                    System.out.println("--------------------------------------------------");
                    System.out.println("Matrícula: " + a.getMatricula());
                    System.out.println("Nome:      " + a.getNome());
                    System.out.println("Curso:     " + a.getCurso());
                    System.out.println("Turma:     " + (a.getTurma() != null ? a.getTurma().getId() : "Sem turma"));
                    System.out.println("CPF:       " + a.getCpf());
                    System.out.println("Telefone:  " + a.getTelefone());
                    System.out.println("Email:     " + a.getEmail());
                    System.out.println("--------------------------------------------------");
                    break;
                case 3:
                    int matUp = InputHandler.lerInt("Digite a Matrícula para atualizar: ");
                    Aluno alUp = serviceAluno.buscar(matUp);

                    System.out.println("--- Atualizando (Pressione ENTER para manter o valor atual) ---");

                    String nNome = InputHandler.lerStringOpcional("Nome (" + alUp.getNome() + "): ");
                    if (nNome.isEmpty())
                        nNome = alUp.getNome();

                    String nTel = InputHandler.lerStringOpcional("Telefone (" + alUp.getTelefone() + "): ");
                    if (nTel.isEmpty())
                        nTel = alUp.getTelefone();

                    String nEmail = InputHandler.lerStringOpcional("Email (" + alUp.getEmail() + "): ");
                    if (nEmail.isEmpty())
                        nEmail = alUp.getEmail();

                    String nCurso = InputHandler.lerStringOpcional("Curso (" + alUp.getCurso() + "): ");
                    if (nCurso.isEmpty())
                        nCurso = alUp.getCurso();

                    serviceAluno.atualizar(matUp, new Aluno(nNome, alUp.getCpf(), nTel, nEmail, nCurso, matUp));
                    System.out.println("Dados atualizados!");

                    break;
                case 4:
                    int matriculaRemover = InputHandler.lerInt("Digite a Matrícula para remover: ");
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
            opcao = InputHandler.lerInt("Opção: ");

            try {
                switch (opcao) {
                    case 1:

                        System.out.println("\n-- Novo Professor --");
                        int id = InputHandler.lerInt("ID: ");

                        if (serviceProfessor.existe(id)) {
                            System.out.println("ERRO: Já existe um professor com o ID " + id);
                        } else {
                            String nome = InputHandler.lerString("Nome: ");
                            String cpf = InputHandler.lerString("CPF: ");
                            String tel = InputHandler.lerString("Tel: ");
                            String email = InputHandler.lerString("Email: ");
                            String disc = InputHandler.lerString("Disciplina: ");
                            double sal = InputHandler.lerDouble("Salário: ");
                            serviceProfessor.inserir(new Professor(nome, cpf, tel, email, disc, sal, id));
                            System.out.println("Professor cadastrado!");
                        }
                        break;
                    case 2:

                        Professor p = serviceProfessor.buscar(InputHandler.lerInt("ID para buscar: "));
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

                        int idUp = InputHandler.lerInt("ID para atualizar: ");
                        Professor pUp = serviceProfessor.buscar(idUp);
                        System.out.println("--- Enter para manter o valor atual ---");

                        String nNome = InputHandler.lerStringOpcional("Nome (" + pUp.getNome() + "): ");
                        if (nNome.isEmpty())
                            nNome = pUp.getNome();

                        String nTel = InputHandler.lerStringOpcional("Tel (" + pUp.getTelefone() + "): ");
                        if (nTel.isEmpty())
                            nTel = pUp.getTelefone();

                        String nEmail = InputHandler.lerStringOpcional("Email (" + pUp.getEmail() + "): ");
                        if (nEmail.isEmpty())
                            nEmail = pUp.getEmail();

                        String nDisc = InputHandler.lerStringOpcional("Disciplina (" + pUp.getDisciplina() + "): ");
                        if (nDisc.isEmpty())
                            nDisc = pUp.getDisciplina();

                        double nSal = InputHandler.lerDoubleOpcional("Salário (" + pUp.getSalario() + "): ",
                                pUp.getSalario());

                        serviceProfessor.atualizar(idUp,
                                new Professor(nNome, pUp.getCpf(), nTel, nEmail, nDisc, nSal, idUp));
                        System.out.println("Professor atualizado!");

                        break;
                    case 4:

                        int idRem = InputHandler.lerInt("ID para remover: ");
                        Professor buffer = serviceProfessor.buscar(idRem);
                        serviceProfessor.remover(idRem);
                        System.out.println("Professor(a) '" + buffer.getNome() + "' removido(a) com sucesso!");

                        break;
                    case 5:
                        serviceProfessor.listar();
                        break;
                    case 6:
                        serviceProfessor.exibirArvore();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
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
            opcao = InputHandler.lerInt("Opção: ");
            try {
                switch (opcao) {
                    case 1:
                        int cod = InputHandler.lerInt("Código: ");
                        if (serviceDisciplina.existe(cod)) {
                            System.out.println("ERRO: Código já existente.");
                        } else {
                            String nome = InputHandler.lerString("Nome: ");
                            int ch = InputHandler.lerInt("Carga Horária: ");
                            serviceDisciplina.inserir(new Disciplina(nome, cod, ch));
                            System.out.println("Disciplina cadastrada!");
                        }
                        break;
                    case 2:

                        Disciplina d = serviceDisciplina.buscar(InputHandler.lerInt("Código: "));
                        System.out.println("--------------------------------------------------");
                        System.out.println("Código:        " + d.getCodigo());
                        System.out.println("Nome:          " + d.getNome());
                        System.out.println("Carga Horária: " + d.getCargaHoraria() + "h");
                        System.out.println("--------------------------------------------------");

                        break;
                    case 3:

                        int codUp = InputHandler.lerInt("Código para atualizar: ");
                        Disciplina dUp = serviceDisciplina.buscar(codUp);

                        String nNome = InputHandler.lerStringOpcional("Nome (" + dUp.getNome() + "): ");
                        if (nNome.isEmpty())
                            nNome = dUp.getNome();

                        int nCH = InputHandler.lerIntOpcional("Carga Horária (" + dUp.getCargaHoraria() + "): ",
                                dUp.getCargaHoraria());

                        serviceDisciplina.atualizar(codUp, new Disciplina(nNome, codUp, nCH));
                        System.out.println("Atualizado!");
                        break;
                    case 4:

                        int codRem = InputHandler.lerInt("Código para remover: ");
                        Disciplina buffer = serviceDisciplina.buscar(codRem);
                        serviceDisciplina.remover(codRem);
                        System.out.println("Disciplina '" + buffer.getNome() + "' removida com sucesso!");

                        break;
                    case 5:
                        serviceDisciplina.listar();
                        break;
                    case 6:
                        serviceDisciplina.exibirArvore();
                        break;
                    case 0:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
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
            opcao = InputHandler.lerInt("Opção: ");
            try {
                switch (opcao) {
                    case 1:
                        int cod = InputHandler.lerInt("Código: ");
                        if (serviceCurso.existe(cod)) {
                            System.out.println("ERRO: Código já existente.");
                        } else {
                            String nome = InputHandler.lerString("Nome: ");
                            int dur = InputHandler.lerInt("Duração (semestres): ");
                            serviceCurso.inserir(new Curso(nome, cod, dur));
                            System.out.println("Curso cadastrado!");
                        }
                        break;
                    case 2:

                        Curso c = serviceCurso.buscar(InputHandler.lerInt("Código: "));
                        System.out.println("--------------------------------------------------");
                        System.out.println("Código:   " + c.getCodigo());
                        System.out.println("Nome:     " + c.getNome());
                        System.out.println("Duração:  " + c.getDuracaoSemestres() + " semestres");
                        System.out.println("--------------------------------------------------");

                        break;
                    case 3:

                        int codUp = InputHandler.lerInt("Código para atualizar: ");
                        Curso cUp = serviceCurso.buscar(codUp);

                        String nNome = InputHandler.lerStringOpcional("Nome (" + cUp.getNome() + "): ");
                        if (nNome.isEmpty())
                            nNome = cUp.getNome();

                        int nDur = InputHandler.lerIntOpcional("Duração (" + cUp.getDuracaoSemestres() + "): ",
                                cUp.getDuracaoSemestres());

                        serviceCurso.atualizar(codUp, new Curso(nNome, codUp, nDur));
                        System.out.println("Atualizado!");
                        break;
                    case 4:

                        int codRem = InputHandler.lerInt("Código para remover: ");
                        Curso buffer = serviceCurso.buscar(codRem);
                        serviceCurso.remover(codRem);
                        System.out.println("Curso '" + buffer.getNome() + "' removido com sucesso!");

                        break;
                    case 5:
                        serviceCurso.listar();
                        break;
                    case 6:
                        serviceCurso.exibirArvore();
                        break;
                    case 0:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
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
            System.out.println("6. Vincular Curso");
            System.out.println("7. Vincular Disciplina");
            System.out.println("8. Vincular Professor");
            System.out.println("9. Árvore");
            System.out.println("0. Voltar");
            opcao = InputHandler.lerInt("Opção: ");
            try {
                switch (opcao) {
                    case 1:
                        int id = InputHandler.lerInt("ID da Turma: ");
                        if (serviceTurma.existe(id)) {
                            System.out.println("ERRO: Turma já existente.");
                        } else {
                            String ano = InputHandler.lerString("Ano (ex: 2024): ");
                            int sem = InputHandler.lerInt("Semestre (1 ou 2): ");
                            serviceTurma.inserir(new Turma(id, ano, sem));
                            System.out.println("Turma cadastrada!");
                        }
                        break;
                    case 2:
                        Turma t = serviceTurma.buscar(InputHandler.lerInt("ID: "));
                        System.out.println("--------------------------------------------------");
                        System.out.println("ID da Turma:     " + t.getId());
                        System.out.println("Ano/Semestre:    " + t.getAno() + "/" + t.getSemestre());
                        System.out.println(
                                "Curso:           " + (t.getCurso() != null ? t.getCurso().getNome() : "Sem curso"));
                        System.out.println("Disciplina:      "
                                + (t.getDisciplina() != null ? t.getDisciplina().getNome() : "Sem disciplina"));
                        System.out.println("Professor:       "
                                + (t.getProfessor() != null ? t.getProfessor().getNome() : "Sem professor"));
                        System.out.println("--------------------------------------------------");
                        break;
                    case 3:
                        int idUp = InputHandler.lerInt("ID para atualizar: ");
                        Turma tUp = serviceTurma.buscar(idUp);

                        String nAno = InputHandler.lerStringOpcional("Ano (" + tUp.getAno() + "): ");
                        if (nAno.isEmpty())
                            nAno = tUp.getAno();

                        int nSem = InputHandler.lerIntOpcional("Semestre (" + tUp.getSemestre() + "): ",
                                tUp.getSemestre());

                        serviceTurma.atualizar(idUp, new Turma(idUp, nAno, nSem));
                        System.out.println("Atualizado!");
                        break;
                    case 4:
                        int idRem = InputHandler.lerInt("ID para remover: ");
                        Turma buffer = serviceTurma.buscar(idRem);
                        serviceTurma.remover(idRem);
                        System.out.println(
                                "Turma " + buffer.getId() + " (" + buffer.getAno() + ") removida com sucesso!");
                        break;
                    case 5:
                        serviceTurma.listar();
                        break;
                    case 6:
                        // Vincular Curso
                        int idTurmaCurso = InputHandler.lerInt("ID da Turma: ");
                        int codCurso = InputHandler.lerInt("Código do Curso: ");
                        serviceTurma.vincularCurso(idTurmaCurso, serviceCurso.buscar(codCurso));
                        System.out.println("Curso vinculado com sucesso!");
                        break;
                    case 7:
                        // Vincular Disciplina
                        int idTurmaDisc = InputHandler.lerInt("ID da Turma: ");
                        int codDisc = InputHandler.lerInt("Código da Disciplina: ");
                        serviceTurma.vincularDisciplina(idTurmaDisc, serviceDisciplina.buscar(codDisc));
                        System.out.println("Disciplina vinculada com sucesso!");
                        break;
                    case 8:
                        // Vincular Professor
                        int idTurmaProf = InputHandler.lerInt("ID da Turma: ");
                        int idProf = InputHandler.lerInt("ID do Professor: ");
                        serviceTurma.vincularProfessor(idTurmaProf, serviceProfessor.buscar(idProf));
                        System.out.println("Professor vinculado com sucesso!");
                        break;
                    case 9:
                        serviceTurma.exibirArvore();
                        break;
                    case 0:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void carregarDadosIniciais() {
        System.out.println("\n>>> Carregando dados iniciais...");

        try {
            serviceCurso.inserir(new Curso("Ciência da Computação", 1, 8));
            serviceCurso.inserir(new Curso("Engenharia de Software", 2, 8));
            serviceCurso.inserir(new Curso("Sistemas de Informação", 3, 8));

            serviceDisciplina.inserir(new Disciplina("Estrutura de Dados", 101, 60));
            serviceDisciplina.inserir(new Disciplina("Programação Orientada a Objetos", 102, 80));
            serviceDisciplina.inserir(new Disciplina("Banco de Dados", 103, 60));
            serviceDisciplina.inserir(new Disciplina("Algoritmos", 104, 80));

            serviceProfessor.inserir(new Professor("João Silva", "111.111.111-11",
                    "(84) 99999-0001", "joao.silva@email.com", "Estrutura de Dados", 8500.00, 1));
            serviceProfessor.inserir(new Professor("Maria Santos", "222.222.222-22",
                    "(84) 99999-0002", "maria.santos@email.com", "POO", 9000.00, 2));
            serviceProfessor.inserir(new Professor("Pedro Costa", "333.333.333-33",
                    "(84) 99999-0003", "pedro.costa@email.com", "Banco de Dados", 8800.00, 3));

            serviceTurma.inserir(new Turma(1, "2026", 2));
            serviceTurma.inserir(new Turma(2, "2026", 2));
            serviceTurma.inserir(new Turma(3, "2026", 1));

            serviceTurma.vincularCurso(1, serviceCurso.buscar(1));
            serviceTurma.vincularDisciplina(1, serviceDisciplina.buscar(101));
            serviceTurma.vincularProfessor(1, serviceProfessor.buscar(1));

            serviceTurma.vincularCurso(2, serviceCurso.buscar(2));
            serviceTurma.vincularDisciplina(2, serviceDisciplina.buscar(102));
            serviceTurma.vincularProfessor(2, serviceProfessor.buscar(2));

            serviceTurma.vincularCurso(3, serviceCurso.buscar(3));
            serviceTurma.vincularDisciplina(3, serviceDisciplina.buscar(103));
            serviceTurma.vincularProfessor(3, serviceProfessor.buscar(3));

            Aluno a1 = new Aluno("Ana Paula", "444.444.444-44", "(84) 98888-0001",
                    "ana.paula@email.com", "Ciência da Computação", 20231001);
            a1.setTurma(serviceTurma.buscar(1));
            serviceAluno.inserir(a1);

            Aluno a2 = new Aluno("Carlos Eduardo", "555.555.555-55", "(84) 98888-0002",
                    "carlos.eduardo@email.com", "Ciência da Computação", 20231002);
            a2.setTurma(serviceTurma.buscar(1));
            serviceAluno.inserir(a2);

            Aluno a3 = new Aluno("Beatriz Lima", "666.666.666-66", "(84) 98888-0003",
                    "beatriz.lima@email.com", "Engenharia de Software", 20231003);
            a3.setTurma(serviceTurma.buscar(2));
            serviceAluno.inserir(a3);

            Aluno a4 = new Aluno("Daniel Souza", "777.777.777-77", "(84) 98888-0004",
                    "daniel.souza@email.com", "Sistemas de Informação", 20231004);
            a4.setTurma(serviceTurma.buscar(3));
            serviceAluno.inserir(a4);

            Aluno a5 = new Aluno("Fernanda Oliveira", "888.888.888-88", "(84) 98888-0005",
                    "fernanda.oliveira@email.com", "Ciência da Computação", 20231005);
            a5.setTurma(serviceTurma.buscar(1));
            serviceAluno.inserir(a5);

            System.out.println("✓ Dados iniciais carregados com sucesso!");
            System.out.println("  - 3 Cursos");
            System.out.println("  - 4 Disciplinas");
            System.out.println("  - 3 Professores");
            System.out.println("  - 3 Turmas (com vínculos completos)");
            System.out.println("  - 5 Alunos");

        } catch (Exception e) {
            System.out.println("Erro ao carregar dados iniciais: " + e.getMessage());
        }
    }

}
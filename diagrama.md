# Diagrama de Classes do Sistema

```mermaid
classDiagram
    %% ==========================================
    %% PACOTE: MODEL
    %% ==========================================
    namespace Model {
        class Pessoa {
            -String nome
            -String cpf
            -String telefone
            -String email
            +getNome() String
            +setCpf(String cpf)
        }

        class Aluno {
            -String curso
            -int matricula
            -Turma turma
            +getMatricula() int
            +setTurma(Turma turma)
            +compareTo(Aluno o) int
        }

        class Professor {
            -String disciplina
            -double salario
            -int id
            -Turma turma
            +getId() int
            +setTurma(Turma turma)
            +compareTo(Professor o) int
        }

        class Turma {
            -int id
            -String ano
            -int semestre
            -Disciplina disciplina
            -Professor professor
            -Curso curso
            -ArvoreAVL~Aluno~ alunos
            +adicionarAluno(Aluno aluno)
            +removerAluno(int matricula)
            +setProfessor(Professor professor)
            +setCurso(Curso curso)
            +compareTo(Turma o) int
        }

        class Curso {
            -String nome
            -int codigo
            -int duracaoSemestres
            -ArvoreAVL~Turma~ turmas
            +adicionarTurma(Turma turma)
            +removerTurma(int id)
            +buscarTurma(int id) Turma
            +compareTo(Curso o) int
        }

        class Disciplina {
            -String nome
            -int codigo
            -int cargaHoraria
            +compareTo(Disciplina o) int
        }
    }

    %% Relacionamentos de Herança
    Pessoa <|-- Aluno
    Pessoa <|-- Professor

    %% Relacionamentos de Associação/Composição
    Turma "0..*" o-- "1" Curso : pertence a
    Curso "1" *-- "0..*" Turma : contem
    
    Turma "0..*" --> "0..1" Disciplina : tem
    
    Turma "0..1" <--> "0..1" Professor : leciona/vinculado
    
    Turma "1" <--> "0..*" Aluno : matriculado/vinculado

    %% ==========================================
    %% PACOTE: ARVORE AVL
    %% ==========================================
    namespace ArvoreAVL {
        class No~T~ {
            T chave
            No~T~ esquerda
            No~T~ direita
            int altura
        }

        class ArvoreAVL~T~ {
            -No~T~ raiz
            +inserir(T chave)
            +remover(T chave)
            +buscar(T chave) T
            +emOrdem() List~T~
            -rotacaoDireita(No y) No
            -rotacaoEsquerda(No x) No
        }
    }

    ArvoreAVL *-- No : compoe

    %% ==========================================
    %% PACOTE: INTERFACE & SERVICES
    %% ==========================================
    namespace Interface {
        class Service~T~ {
            <<interface>>
            +inserir(T objeto)
            +remover(int id)
            +buscar(int id) T
            +atualizar(int id, T dados)
            +listar()
            +existe(int id) boolean
        }
    }

    namespace Services {
        class ServiceAluno {
            -ArvoreAVL~Aluno~ arvore
        }
        class ServiceProfessor {
            -ArvoreAVL~Professor~ arvore
        }
        class ServiceCurso {
            -ArvoreAVL~Curso~ arvore
        }
        class ServiceDisciplina {
            -ArvoreAVL~Disciplina~ arvore
        }
        class ServiceTurma {
            -ArvoreAVL~Turma~ arvore
            +vincularCurso(int idTurma, Curso curso)
            +vincularProfessor(int idTurma, Professor prof)
            +vincularDisciplina(int idTurma, Disciplina disc)
            +possuiTodos(int idTurma) boolean
        }
    }

    Service <|.. ServiceAluno
    Service <|.. ServiceProfessor
    Service <|.. ServiceCurso
    Service <|.. ServiceDisciplina
    Service <|.. ServiceTurma

    %% Dependencias dos Services para as Árvores
    ServiceAluno --> ArvoreAVL : usa
    ServiceProfessor --> ArvoreAVL : usa
    ServiceCurso --> ArvoreAVL : usa
    ServiceDisciplina --> ArvoreAVL : usa
    ServiceTurma --> ArvoreAVL : usa

    %% Dependencias dos Services para os Models
    ServiceAluno ..> Aluno : gerencia
    ServiceProfessor ..> Professor : gerencia
    ServiceCurso ..> Curso : gerencia
    ServiceDisciplina ..> Disciplina : gerencia
    ServiceTurma ..> Turma : gerencia

    %% ==========================================
    %% UTILS & MAIN
    %% ==========================================
    class InputHandler {
        +lerInt(String msg)$ int
        +lerString(String msg)$ String
        +lerDouble(String msg)$ double
    }

    class Main {
        -ServiceAluno serviceAluno
        -ServiceTurma serviceTurma
        +main(String[] args)$
    }

    Main --> ServiceAluno : instancia
    Main --> ServiceTurma : instancia
    Main ..> InputHandler : usa
```
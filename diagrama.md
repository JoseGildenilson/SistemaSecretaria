# Diagrama UML - Sistema de Gestão Acadêmica

```mermaid
classDiagram

%% ============================
%% Model
%% ============================

class Pessoa {
    -String nome
    -String cpf
    -String telefone
    -String email
    +getNome() String
    +getCpf() String
    +getTelefone() String
    +getEmail() String
}

class Aluno {
    -String curso
    -int matricula
    -Turma turma
    +getMatricula() int
    +setTurma(Turma turma)
    +compareTo(Aluno other) int
}

class Professor {
    -String disciplina
    -double salario
    -int id
    -Turma turma
    +getId() int
    +setTurma(Turma turma)
    +compareTo(Professor other) int
}

class Curso {
    -String nome
    -int codigo
    -int duracaoSemestres
    -ArvoreAVL<Turma> turmas
    +adicionarTurma(Turma turma)
    +removerTurma(int turmaId)
    +buscarTurma(int turmaId) Turma
    +compareTo(Curso other) int
}

class Disciplina {
    -String nome
    -int codigo
    -int cargaHoraria
    +compareTo(Disciplina other) int
}

class Turma {
    -int id
    -String ano
    -int semestre
    -Disciplina disciplina
    -Professor professor
    -Curso curso
    -ArvoreAVL<Aluno> alunos
    +adicionarAluno(Aluno aluno)
    +removerAluno(int matricula)
    +buscarAluno(int matricula) Aluno
    +setProfessor(Professor professor)
    +setCurso(Curso curso)
    +setDisciplina(Disciplina disciplina)
    +compareTo(Turma other) int
}

%% Herança
Pessoa <|-- Aluno
Pessoa <|-- Professor

%% Associações principais
Curso o-- Turma : contém
Turma o-- Aluno : contém
Turma --> Disciplina : tem
Turma --> Professor : tem
Turma --> Curso : pertence
Aluno --> Turma : vinculado
Professor --> Turma : vinculado

%% ============================
%% AVL Tree
%% ============================

class No<T> {
    T chave
    No<T> esquerda
    No<T> direita
    int altura
}

class ArvoreAVL<T> {
    -No<T> raiz
    +inserir(T chave)
    +remover(T chave)
    +buscar(T chave) T
    +emOrdem() List<T>
    +exibirArvore()
}

ArvoreAVL *-- No : composição

%% ============================
%% Services
%% ============================

class Service<T> {
    <<interface>>
    +inserir(T objeto)
    +remover(int id)
    +buscar(int id) T
    +atualizar(int id, T novosDados)
    +listar()
    +exibirArvore()
    +existe(int id) boolean
}

class ServiceAluno {
    -ArvoreAVL<Aluno> arvore
}

class ServiceProfessor {
    -ArvoreAVL<Professor> arvore
}

class ServiceDisciplina {
    -ArvoreAVL<Disciplina> arvore
}

class ServiceCurso {
    -ArvoreAVL<Curso> arvore
}

class ServiceTurma {
    -ArvoreAVL<Turma> arvore
    +vincularCurso(int turmaId, Curso curso)
    +vincularDisciplina(int turmaId, Disciplina disciplina)
    +vincularProfessor(int turmaId, Professor professor)
    +possuiTodos(int turmaId) boolean
}

Service <|.. ServiceAluno
Service <|.. ServiceProfessor
Service <|.. ServiceDisciplina
Service <|.. ServiceCurso
Service <|.. ServiceTurma

ServiceAluno --> ArvoreAVL
ServiceProfessor --> ArvoreAVL
ServiceDisciplina --> ArvoreAVL
ServiceCurso --> ArvoreAVL
ServiceTurma --> ArvoreAVL

%% ============================
%% Exceptions
%% ============================

class NaoEncontradoException {
    +NaoEncontradoException(String message)
}

Service ..> NaoEncontradoException : lança

%% ============================
%% Utils
%% ============================

class InputHandler {
    -Scanner scanner$
    +lerString(String msg)$ String
    +lerInt(String msg)$ int
    +lerDouble(String msg)$ double
    +lerStringOpcional(String msg)$ String
}

%% ============================
%% Main
%% ============================

class Main {
    -ServiceAluno serviceAluno
    -ServiceProfessor serviceProfessor
    -ServiceDisciplina serviceDisciplina
    -ServiceCurso serviceCurso
    -ServiceTurma serviceTurma
    +main(String[] args)$
}

Main --> ServiceAluno
Main --> ServiceProfessor
Main --> ServiceDisciplina
Main --> ServiceCurso
Main --> ServiceTurma
Main ..> InputHandler : usa
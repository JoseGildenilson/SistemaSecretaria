package Services;

import ArvoreAVL.ArvoreAVL;
import Exception.NaoEncontradoException;
import Interface.Service;
import Model.Turma;
import Model.Curso;
import Model.Disciplina;
import Model.Professor;

public class ServiceTurma implements Service<Turma> {
    private ArvoreAVL<Turma> arvore = new ArvoreAVL<>();

    @Override
    public void inserir(Turma t) {
        arvore.inserir(t);
    }

    @Override
    public void remover(int id) {
        arvore.remover(new Turma(id, "", 0));
    }

    @Override
    public Turma buscar(int id) throws NaoEncontradoException {
        Turma t = arvore.buscar(new Turma(id, "", 0));
        if (t == null) throw new NaoEncontradoException("Turma não encontrada.");
        return t;
    }

    @Override
    public void atualizar(int id, Turma novosDados) throws NaoEncontradoException {
        Turma existente = buscar(id);
        existente.setAno(novosDados.getAno());
        existente.setSemestre(novosDados.getSemestre());
    }

    @Override
    public void listar() {
        if (arvore.emOrdem().isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
        } else {
            for (Turma t : arvore.emOrdem()) {
                System.out.println("--------------------------------------------------");
                System.out.println("ID da Turma:     " + t.getId());
                System.out.println("Ano/Semestre:    " + t.getAno() + "/" + t.getSemestre());
                System.out.println("Curso:           " + (t.getCurso() != null ? t.getCurso().getNome() : "Sem curso"));
                System.out.println("Disciplina:      " + (t.getDisciplina() != null ? t.getDisciplina().getNome() : "Sem disciplina"));
                System.out.println("Professor:       " + (t.getProfessor() != null ? t.getProfessor().getNome() : "Sem professor"));
                System.out.println("--------------------------------------------------");
            }
        }
    }

    @Override
    public void exibirArvore() {
        arvore.exibirArvore();
    }

    @Override
    public boolean existe(int id) {
        try {
            buscar(id);
            return true;
        } catch (NaoEncontradoException e){
            return false;
        }
    }

    public void vincularCurso(int turmaId, Curso curso) throws NaoEncontradoException {
        Turma turma = buscar(turmaId);
        turma.setCurso(curso);
    }

    public void vincularDisciplina(int turmaId, Disciplina disciplina) throws NaoEncontradoException {
        Turma turma = buscar(turmaId);
        turma.setDisciplina(disciplina);
    }

    public void vincularProfessor(int turmaId, Professor professor) throws NaoEncontradoException {
        Turma turma = buscar(turmaId);
        turma.setProfessor(professor);
    }

    public boolean possuiTodos(int turmaId) throws NaoEncontradoException {
        Turma turma = buscar(turmaId);
        return turma.getCurso() != null && turma.getDisciplina() != null && turma.getProfessor() != null;
    }
}
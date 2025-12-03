package Services;

import ArvoreAVL.ArvoreAVL;
import Exception.NaoEncontradoException;
import Interface.Service;
import Model.Turma;

public class ServiceTurma implements Service<Turma> {
    private ArvoreAVL<Turma> arvore = new ArvoreAVL<>();

    @Override
    public void inserir(Turma t) {
        arvore.inserir(t);
    }

    @Override
    public void remover(int id) {
        arvore.remover(new Turma(id, "", 0, 0));
    }

    @Override
    public Turma buscar(int id) throws NaoEncontradoException {
        Turma t = arvore.buscar(new Turma(id, "", 0, 0));
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
                System.out.println("--------------------------------------------------");
            }
        }
    }

    @Override
    public void exibirArvore() {
        arvore.exibirArvore();
    }
}
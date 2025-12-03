package Services;

import ArvoreAVL.ArvoreAVL;
import Exception.NaoEncontradoException;
import Interface.Service;
import Model.Curso;

public class ServiceCurso implements Service<Curso> {
    private ArvoreAVL<Curso> arvore = new ArvoreAVL<>();

    @Override
    public void inserir(Curso c) {
        arvore.inserir(c);
    }

    @Override
    public void remover(int codigo) {
        arvore.remover(new Curso("", codigo, 0));
    }

    @Override
    public Curso buscar(int codigo) throws NaoEncontradoException {
        Curso c = arvore.buscar(new Curso("", codigo, 0));
        if (c == null) throw new NaoEncontradoException("Curso não encontrado.");
        return c;
    }

    @Override
    public void atualizar(int codigo, Curso novosDados) throws NaoEncontradoException {
        Curso existente = buscar(codigo);
        existente.setNome(novosDados.getNome());
        existente.setDuracaoSemestres(novosDados.getDuracaoSemestres());
    }

    @Override
    public void listar() {
        if (arvore.emOrdem().isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            for (Curso c : arvore.emOrdem()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Código:   " + c.getCodigo());
                System.out.println("Nome:     " + c.getNome());
                System.out.println("Duração:  " + c.getDuracaoSemestres() + " semestres");
                System.out.println("--------------------------------------------------");
            }
        }
    }

    @Override
    public void exibirArvore() {
        arvore.exibirArvore();
    }
}
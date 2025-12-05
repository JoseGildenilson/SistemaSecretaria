package Services;

import ArvoreAVL.ArvoreAVL;
import Exception.NaoEncontradoException;
import Interface.Service;
import Model.Disciplina;

public class ServiceDisciplina implements Service<Disciplina>{
    private ArvoreAVL<Disciplina> arvore = new ArvoreAVL<>();

    @Override
    public void inserir(Disciplina d) {
        arvore.inserir(d);
    }

    @Override
    public void remover(int codigo){
        arvore.remover(new Disciplina("", codigo, 0));
    }

    @Override
    public Disciplina buscar(int codigo) throws NaoEncontradoException {
        Disciplina d = arvore.buscar(new Disciplina("", codigo, 0));
        if(d == null) throw new NaoEncontradoException("Disciplina não encontrada");
        return d;
    }

    @Override
    public void atualizar(int codigo, Disciplina novos) throws NaoEncontradoException {
        Disciplina existente = buscar(codigo);
        existente.setNome(novos.getNome());
        existente.setCargaHoraria(novos.getCargaHoraria());
    }

    @Override
    public void listar() {
        if (arvore.emOrdem().isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            for (Disciplina d : arvore.emOrdem()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Código:        " + d.getCodigo());
                System.out.println("Nome:          " + d.getNome());
                System.out.println("Carga Horária: " + d.getCargaHoraria() + "h");
                System.out.println("--------------------------------------------------");
            }
        }
    }

    @Override
    public void exibirArvore() {
        arvore.exibirArvore();
    }

    @Override
    public boolean existe(int codigo) {
        try {
            buscar(codigo);
            return true;
        } catch (NaoEncontradoException e){
            return false;
        }
    }
}

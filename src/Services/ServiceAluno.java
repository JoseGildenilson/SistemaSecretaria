
package Services;

import ArvoreAVL.ArvoreAVL;
import Exception.NaoEncontradoException;
import Interface.Service;
import Model.Aluno;


public class ServiceAluno implements Service<Aluno> {
    private ArvoreAVL<Aluno> arvore = new ArvoreAVL<>();

    @Override
    public void inserir(Aluno aluno) {
        arvore.inserir(aluno);
    }

    @Override
    public void remover(int matricula){
        Aluno dummy = new Aluno("", "", "", "", "", matricula);
        arvore.remover(dummy);
    }

    @Override
    public Aluno buscar(int matricula) throws NaoEncontradoException {
        Aluno dummy = new Aluno("", "", "", "", "", matricula);
        Aluno encontrado = arvore.buscar(dummy);
        if(encontrado == null) throw new NaoEncontradoException("Aluno não encontrado");
        return encontrado;
    }

    @Override
    public void atualizar(int matricula, Aluno novosDados) throws NaoEncontradoException {
        Aluno existente = buscar(matricula);
        existente.setNome(novosDados.getNome());
        existente.setTelefone(novosDados.getTelefone());
        existente.setEmail(novosDados.getEmail());
        existente.setCurso(novosDados.getCurso());
    }

    @Override
    public void listar() {
        if (arvore.emOrdem().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno a : arvore.emOrdem()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Matrícula: " + a.getMatricula());
                System.out.println("Nome:      " + a.getNome());
                System.out.println("Curso:     " + a.getCurso());
                System.out.println("CPF:       " + a.getCpf());
                System.out.println("Telefone:  " + a.getTelefone());
                System.out.println("Email:     " + a.getEmail());
                System.out.println("--------------------------------------------------");
            }
        }
    }

    @Override
    public void exibirArvore() {
        arvore.exibirArvore();
    }

    @Override
    public boolean existe(int matricula) {
        try {
            buscar(matricula);
            return true;
        } catch (NaoEncontradoException e){
            return false;
        }
    }

}

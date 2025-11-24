
package Services;

import ArvoreAVL.ArvoreAVL;
import Exception.NaoEncontradoException;
import ProjetoEscola.Model.Aluno;
import ProjetoEscola.Model.Pessoa;
import Interface.Service;

import java.util.List;
public class ServiceAluno implements Service<Integer> {
    ArvoreAVL<Aluno> arvoreAVL = new ArvoreAVL<>();

    @Override
    public void inserir(int chave, Pessoa pessoa) {
        // Assumindo que Pessoa é do tipo Aluno
        if (!(pessoa instanceof Aluno)) {
            throw new IllegalArgumentException("Pessoa precisa ser do tipo Aluno");
        }
        Aluno aluno = (Aluno) pessoa;
        aluno.setMatricula(chave);
        arvoreAVL.inserir(aluno);
    }

    @Override
    public void remover(int matricula) {
        // Criar um objeto Aluno "dummy" para remover
        Aluno dummy = new Aluno("", "", "", "", "", matricula);
        arvoreAVL.remover(dummy);
    }

    @Override
    public void imprimirEmOrdem(ArvoreAVL arvoreAVL) {
        List<Aluno> alunos = arvoreAVL.emOrdem();
        for (Aluno aluno : alunos) {
            System.out.println(aluno.getNome());
        }
    }

    public ArvoreAVL<Aluno> getArvoreAVLCopy() {
        ArvoreAVL<Aluno> copia = new ArvoreAVL<>();
        for (Aluno aluno : arvoreAVL.emOrdem()) {
            copia.inserir(aluno);
        }
        return copia;
    }

    @Override
    public Aluno buscar(Integer matricula) throws NaoEncontradoException  {
        Aluno dummy = new Aluno("", "", "", "", "", matricula);
        Aluno result = arvoreAVL.buscar(dummy);
        if (result != null) {
            return result;
        } 
        throw new NaoEncontradoException("Aluno com matricula " + matricula + " nao encontrado.");
    }

}

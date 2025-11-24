package Interface;

import ArvoreAVL.ArvoreAVL;
import Exception.NaoEncontradoException;
import ProjetoEscola.Model.Aluno;
import ProjetoEscola.Model.Pessoa;

public interface Service<T> {
    void inserir(int chave, Pessoa pessoa);
    void remover(int matricula);
    Aluno buscar(T matricula) throws NaoEncontradoException;
    void imprimirEmOrdem(ArvoreAVL arvoreAVL);
}

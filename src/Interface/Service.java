package Interface;

import Exception.NaoEncontradoException;

public interface Service<T> {
    void inserir(T objeto);
    void remover(int id);
    T buscar(int id) throws NaoEncontradoException;
    void atualizar(int id, T novosDados) throws NaoEncontradoException;
    void listar();
    void exibirArvore();
    boolean existe(int id);
}

package ArvoreAVL;

public class ArvoreAVL<T extends Comparable<T>> {

    private No<T> raiz = null;

    private int altura(No<T> no) {
        if (no == null) return 0;
        return no.altura;
    }

    // Método buscar público para buscar chave na árvore
    public T buscar(T chave) {
        No<T> resultado = buscar(raiz, chave);
        return (resultado != null) ? resultado.chave : null;
    }

    // Método recursivo auxiliar para busca
    private No<T> buscar(No<T> no, T chave) {
        if (no == null) return null;

        int cmp = chave.compareTo(no.chave);
        if (cmp == 0) {
            return no;
        } else if (cmp < 0) {
            return buscar(no.esquerda, chave);
        } else {
            return buscar(no.direita, chave);
        }
    }

    private int fatorBalanceamento(No<T> no) {
        if (no == null) return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    private No<T> rotacaoDireita(No<T> y) {
        No<T> x = y.esquerda;
        No<T> T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    private No<T> rotacaoEsquerda(No<T> x) {
        No<T> y = x.direita;
        No<T> T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    private No<T> inserir(No<T> no, T chave) {
        if (no == null)
            return new No<T>(chave);

        int cmp = chave.compareTo(no.chave);
        if (cmp < 0) {
            no.esquerda = inserir(no.esquerda, chave);
        } else if (cmp > 0) {
            no.direita = inserir(no.direita, chave);
        } else {
            return no; // chaves duplicadas não permitidas
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        int balance = fatorBalanceamento(no);

        // Caso LL
        if (balance > 1 && chave.compareTo(no.esquerda.chave) < 0)
            return rotacaoDireita(no);

        // Caso RR
        if (balance < -1 && chave.compareTo(no.direita.chave) > 0)
            return rotacaoEsquerda(no);

        // Caso LR
        if (balance > 1 && chave.compareTo(no.esquerda.chave) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Caso RL
        if (balance < -1 && chave.compareTo(no.direita.chave) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void inserir(T chave) {
        raiz = inserir(raiz, chave);
    }
    
    private No<T> noMinimo(No<T> no) {
        No<T> atual = no;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    private No<T> remover(No<T> no, T chave) {
        if (no == null) return null;

        int cmp = chave.compareTo(no.chave);
        if (cmp < 0) {
            no.esquerda = remover(no.esquerda, chave);
        } else if (cmp > 0) {
            no.direita = remover(no.direita, chave);
        } else {
            // Nó com apenas um filho ou nenhum
            if ((no.esquerda == null) || (no.direita == null)) {
                No<T> temp = null;
                if (no.esquerda != null) temp = no.esquerda;
                else temp = no.direita;

                // Sem filhos
                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                // Nó com dois filhos: pegar o menor sucessor da direita
                No<T> temp = noMinimo(no.direita);
                no.chave = temp.chave;
                no.direita = remover(no.direita, temp.chave);
            }
        }

        if (no == null) return no; // Caso base para retorno

        // Atualizar altura
        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;

        int balance = fatorBalanceamento(no);

        // Caso LL
        if (balance > 1 && fatorBalanceamento(no.esquerda) >= 0)
            return rotacaoDireita(no);

        // Caso LR
        if (balance > 1 && fatorBalanceamento(no.esquerda) < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Caso RR
        if (balance < -1 && fatorBalanceamento(no.direita) <= 0)
            return rotacaoEsquerda(no);

        // Caso RL
        if (balance < -1 && fatorBalanceamento(no.direita) > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void remover(T chave) {
        raiz = remover(raiz, chave);
    }
    private void emOrdem(No<T> no, java.util.List<T> lista) {
        if (no != null) {
            emOrdem(no.esquerda, lista);
            lista.add(no.chave);
            emOrdem(no.direita, lista);
        }
    }

    public java.util.List<T> emOrdem() {
        java.util.List<T> lista = new java.util.ArrayList<>();
        emOrdem(raiz, lista);
        return lista;
    }
}

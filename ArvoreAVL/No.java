package ArvoreAVL;

public class No<T> {
    T chave;
    T pessoa;
    No<T> esquerda, direita;
    int altura;

    public No(T chave) {
        this.chave = chave;
        altura = 1;
    }

    public No(T chave, No<T> esquerda, No<T> direita) {
        this.chave = chave;
        this.esquerda = esquerda;
        this.direita = direita;
        altura = 1 + Math.max(altura(esquerda), altura(direita));
    }

    private int altura(No<T> no) {
        if (no == null) return 0;
        return no.altura;
    }

    @Override
    public String toString() {
        return "No{" +
                "chave=" + chave +
                '}';
    }

    public T getChave() {
        return chave;
    }

    public void setChave(T chave) {
        this.chave = chave;
    }

    public No<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    public No<T> getDireita() {
        return direita;
    }

    public void setDireita(No<T> direita) {
        this.direita = direita;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public T getPessoa() {
        return pessoa;
    }

    public void setPessoa(T pessoa) {
        this.pessoa = pessoa;
    }
}

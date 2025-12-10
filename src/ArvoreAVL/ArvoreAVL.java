package ArvoreAVL;

import java.util.ArrayList;
import java.util.List;

public class ArvoreAVL<T extends Comparable<T>> {

    private No<T> raiz = null;

    private int altura(No<T> no) {
        if (no == null) return 0;
        return no.getAltura(); 
    }

    public int getTamanho() {
        return emOrdem().size();
    }

    // Método buscar público para buscar chave na árvore
    public T buscar(T chave) {
        No<T> resultado = buscar(raiz, chave);
        return (resultado != null) ? resultado.getChave() : null; 
    }

    // Método recursivo auxiliar para busca
    private No<T> buscar(No<T> no, T chave) {
        if (no == null) return null;

        int cmp = chave.compareTo(no.getChave()); 
        if (cmp == 0) {
            return no;
        } else if (cmp < 0) {
            return buscar(no.getEsquerda(), chave); 
        } else {
            return buscar(no.getDireita(), chave); 
        }
    }

    private int fatorBalanceamento(No<T> no) {
        if (no == null) return 0;
        return altura(no.getEsquerda()) - altura(no.getDireita()); 
    }

    private No<T> rotacaoDireita(No<T> y) {
        No<T> x = y.getEsquerda(); 
        No<T> z = x.getDireita(); 

        x.setDireita(y); 
        y.setEsquerda(z); 

        y.setAltura(Math.max(altura(y.getEsquerda()), altura(y.getDireita())) + 1);
        x.setAltura(Math.max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);

        return x;
    }

    private No<T> rotacaoEsquerda(No<T> x) {

        No<T> y = x.getDireita(); 
        No<T> z = y.getEsquerda(); 

        y.setEsquerda(x); 
        x.setDireita(z); 


        x.setAltura(Math.max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);
        y.setAltura(Math.max(altura(y.getEsquerda()), altura(y.getDireita())) + 1);

        return y;
    }

    private No<T> inserir(No<T> no, T chave) {
        if (no == null)
            return new No<T>(chave);

        int cmp = chave.compareTo(no.getChave()); 

        if (cmp < 0) {
            no.setEsquerda(inserir(no.getEsquerda(), chave)); 
        } else if (cmp > 0) {
            no.setDireita(inserir(no.getDireita(), chave)); 
        } else {
            return no; // chaves duplicadas não permitidas
        }

        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));

        int balance = fatorBalanceamento(no);

        if (balance > 1 && chave.compareTo(no.getEsquerda().getChave()) < 0) 
            return rotacaoDireita(no);

        if (balance < -1 && chave.compareTo(no.getDireita().getChave()) > 0) 
            return rotacaoEsquerda(no);

        if (balance > 1 && chave.compareTo(no.getEsquerda().getChave()) > 0) { 
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda())); 
            return rotacaoDireita(no);
        }

        if (balance < -1 && chave.compareTo(no.getDireita().getChave()) < 0) { 
            no.setDireita(rotacaoDireita(no.getDireita())); 
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void inserir(T chave) {
        raiz = inserir(raiz, chave);
    }
    
    private No<T> noMinimo(No<T> no) {
        No<T> atual = no;
        while (atual.getEsquerda() != null) { 
            atual = atual.getEsquerda(); 
        }
        return atual;
    }

    private No<T> remover(No<T> no, T chave) {
        if (no == null) return null;

        int cmp = chave.compareTo(no.getChave()); 

        if (cmp < 0) {
            no.setEsquerda(remover(no.getEsquerda(), chave)); 
        } else if (cmp > 0) {
            no.setDireita(remover(no.getDireita(), chave)); 
        } else {

            if ((no.getEsquerda() == null) || (no.getDireita() == null)) { 
                No<T> temp = null;
                if (no.getEsquerda() != null) temp = no.getEsquerda(); 
                else temp = no.getDireita(); 

                // Sem filhos
                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else {

                No<T> temp = noMinimo(no.getDireita()); 
                no.setChave(temp.getChave()); 
                no.setDireita(remover(no.getDireita(), temp.getChave())); 
            }
        }

        if (no == null) return no; // Caso base para retorno

        no.setAltura(Math.max(altura(no.getEsquerda()), altura(no.getDireita())) + 1); 

        int balance = fatorBalanceamento(no);

        if (balance > 1 && fatorBalanceamento(no.getEsquerda()) >= 0) 
            return rotacaoDireita(no);

        if (balance > 1 && fatorBalanceamento(no.getEsquerda()) < 0) { 
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda())); 
            return rotacaoDireita(no);
        }

        if (balance < -1 && fatorBalanceamento(no.getDireita()) <= 0) 
            return rotacaoEsquerda(no);

        if (balance < -1 && fatorBalanceamento(no.getDireita()) > 0) { 
            no.setDireita(rotacaoDireita(no.getDireita())); 
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void remover(T chave) {
        raiz = remover(raiz, chave);
    }

    private void emOrdem(No<T> no, List<T> lista) {
        if (no != null) {
            emOrdem(no.getEsquerda(), lista); 
            lista.add(no.getChave());
            emOrdem(no.getDireita(), lista);
        }
    }

    public List<T> emOrdem() {
        List<T> lista = new ArrayList<>();
        emOrdem(raiz, lista);
        return lista;
    }

    public void exibirArvore(){
        imprimirArvore(raiz, "", true);
    }

    private void imprimirArvore(No<T> no, String prefixo, boolean isFim){
        if(no != null){
            System.out.print(prefixo);
            System.out.print(isFim ? "└── " : "├── ");
            System.out.println(no.getChave()); 

            imprimirArvore(no.getEsquerda(), prefixo + (isFim ? "    " : "│   "), false); 
            imprimirArvore(no.getDireita(), prefixo + (isFim ? "    " : "│   "), true); 
        }
    }
}
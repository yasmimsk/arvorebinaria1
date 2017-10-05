package utfpr.dainf.ct.ed.exemplo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * 
 * Exemplo de implementação de árvore binária.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós na árvore
 */
public class ArvoreBinaria<E> {
    
    private E dado;
    private ArvoreBinaria<E> esquerda;
    private ArvoreBinaria<E> direita;
    
    // para percurso iterativo
    private boolean inicio = true;
    private Stack<ArvoreBinaria<E>> pilha;
    private ArvoreBinaria<E> ultimoVisitado;

    /**
     * Cria uma árvore binária com dado nulo na raiz.
     */
    public ArvoreBinaria() {
    }

    /**
     * Cria uma árvore binária com dado {@code dado} na raiz.
     * @param valor O dado do nó raiz
     */
    public ArvoreBinaria(E dado) {
        this.dado = dado;
    }
    
    /**
     * Adiciona um nó à esquerda do nó corrente.
     * @param dado O dado associado ao nó inserido.
     * @return A árvore adicionada ao nó
     */
    public ArvoreBinaria<E> insereEsquerda(E dado) {
        ArvoreBinaria<E> e = esquerda;
        esquerda = new ArvoreBinaria<>(dado);
        esquerda.esquerda = e;
        return esquerda;
    }
    
    /**
     * Adiciona um nó à esquerda do nó corrente.
     * @param dado O dado associado ao nó inserido.
     * @return A árvore adicionada ao nó
     */
    public ArvoreBinaria<E> insereDireita(E dado) {
        ArvoreBinaria<E> d = direita;
        direita = new ArvoreBinaria<>(dado);
        direita.direita = d;
        return direita;
    }
    
    /**
     * Implementação padrão que exibe o dado armazenado no nó usando
     * o método {@code toString() }.
     * Pode ser sobrecarregado em classes derivadas para implementar outras
     * formas de visita.
     * @param no O nó a ser visitado
     */
    protected void visita(ArvoreBinaria<E> no) {
        System.out.print(" " + no.dado);
    }
    
    /**
     * Visita os nós da subárvore em-ordem.
     * @param raiz A raiz da subárvore
     */
    public void visitaEmOrdem(ArvoreBinaria<E> raiz) {
        if (raiz != null) {
            ArvoreBinaria.this.visitaEmOrdem(raiz.esquerda);
            visita(raiz);
            ArvoreBinaria.this.visitaEmOrdem(raiz.direita);
        }
    }
    
    /**
     * Visita a árvore em pré-ordem
     * @param raiz
     */
    public void visitaPreOrdem(ArvoreBinaria<E> raiz) {
        if(raiz != null){
            visita(raiz);
            visitaPreOrdem(raiz.esquerda);
            visitaPreOrdem(raiz.direita);
        }
    }
    
    /**
     *
     * @param raiz
     */
    public void visitaPosOrdem(ArvoreBinaria<E> raiz) {
        if(raiz != null){
            visitaPosOrdem(raiz.esquerda);
            visitaPosOrdem(raiz.direita);
            visita(raiz);
        }
    }
    
     /**
     * Visita os nós da árvore em pré-ordem a partir da raiz.
     */
    public void visitaPreOrdem() {
        visitaEmOrdem(this);
    }
    
    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaEmOrdem() {
        visitaEmOrdem(this);
    }
    
    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }
    
    /**
     * Reinicia o percurso a partir do início.
     * Deve ser chamado após percorrer toda a árvore para realizar novo
     * percurso ou para voltar ao início a qualquer momento.
     */
    public void reinicia() {
        inicializaPilha();
        pilha.clear();
        ultimoVisitado = this;
        inicio = true;
    }
    
    /**
     * Retorna o dado do próximo nó em-ordem.
     * @return O dado do próximo nó em-ordem.
     */
    public ArvoreBinaria<E> proximoEmOrdem() {
        ArvoreBinaria<E> resultado = null;
        if (inicio) {
            reinicia();
            inicio = false;
        }
        if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.esquerda;
            }
            ultimoVisitado = pilha.pop();
            resultado = ultimoVisitado;
            ultimoVisitado = ultimoVisitado.direita;
        }
        return resultado;
    }
    
    /**
     *
     * @param raiz
     */
    public void proximoPreOrdem(ArvoreBinaria<E> raiz) {
        if(raiz != null){
            reinicia();
            pilha.push(raiz);
        }
        
        while(!pilha.isEmpty()){
            raiz = pilha.pop();
            visita(raiz);
            
            if(raiz.direita != null){
                pilha.push(raiz.direita);
            }
            if(raiz.esquerda != null){
                pilha.push(raiz.esquerda);
            }
        }
    }
    
    /**
     *
     * @param raiz
     */
    public void proximoPosOrdem(ArvoreBinaria<E> raiz) {
        reinicia();
        ultimoVisitado = null;
        
        while(!pilha.isEmpty() || raiz != null){
            if(raiz != null){
                pilha.push(raiz);
                raiz = raiz.esquerda;
            }
            
            else{
                ArvoreBinaria<E> noVisto = pilha.peek();
                
                if(noVisto.direita != null && ultimoVisitado != noVisto.direita){
                    raiz = noVisto.direita;
                }
                
                else{
                    visita(noVisto);
                    ultimoVisitado = pilha.pop();
                }
            }
        }
    }
    
    /**
     *
     * @param raiz
     */
    int i = 0;
    int flag = 1;
    ArrayList<ArvoreBinaria<E>> lista = new ArrayList();
    public void proximoEmNivel(ArvoreBinaria<E> no) {
        
        lista.add(no);
        
        if(no.esquerda != null)
            lista.add(no.esquerda);
        if(no.direita != null)
            lista.add(no.direita);
        
        for(int j = 1; j < lista.size(); j++)
        {
            if(lista.get(j).esquerda != null)
                lista.add(lista.get(j).esquerda);
            if(lista.get(j).direita != null)
                lista.add(lista.get(j).direita);
        }
    }
    
    public void visitaEmNivel(ArvoreBinaria<E> raiz)
    {
        for(int j = 0; j < lista.size(); j++)
        {
            System.out.print(lista.get(j).dado + " ");
        }
    }
    
    /**
     * Retorna o dado armazenado no nó.
     * @return O dado armazenado no nó.
     */
    public E getDado() {
        return dado;
    }

    /**
     * Atribui um dado ao nó.
     * @param dado O dado a ser atribuído ao nó.
     */
    public void setDado(E dado) {
        this.dado = dado;
    }

    /**
     * Retorna a árvore esqueda.
     * @return A árvore esquerda.
     */
    protected ArvoreBinaria<E> getEsquerda() {
        return esquerda;
    }

    /**
     * Retorna a árvore direita.
     * @return A árvore direita.
     */
    protected ArvoreBinaria<E> getDireita() {
        return direita;
    }
    
    
    
}

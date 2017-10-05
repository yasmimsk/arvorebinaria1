
import java.util.ArrayList;
import utfpr.dainf.ct.ed.exemplo.ArvoreBinaria;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * 
 * Cria e percorre a seguinte árvore binária:
 *       
 *                    8
 *                   / \
 *                  /   \
 *                 3     10
 *                / \     \
 *               1   6     14 
 *                  / \    /
 *                 4   7  13
 *                  \
 *                   5
 * 
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class Main {
    public static void main(String[] args) {
        // cria a raiz da árvore binária
        ArvoreBinaria<Integer> a = new ArvoreBinaria<>(8);
        
        // monta o ramo esquerdo
        ArvoreBinaria<Integer> e = a.insereEsquerda(3);
        e.insereEsquerda(1);
        ArvoreBinaria<Integer> d = e.insereDireita(6);
        d.insereEsquerda(4).insereDireita(5);
        d.insereDireita(7);

        // monta o ramo direito
        a.insereDireita(10).insereDireita(14).insereEsquerda(13);
        
        System.out.println("PERCURSO RECURSIVO");
        a.visitaEmOrdem();
        System.out.println("\nPERCURSO ITERATIVO");
        ArvoreBinaria<Integer> no;
        while ((no = a.proximoEmOrdem()) != null) {
            System.out.print(" " + no.getDado());
        }
        
        System.out.println("\nPercurso em pré-ordem recursivo:");
        a.visitaPreOrdem(a);
        System.out.println("\nPercurso em pré-ordem iterativo:");
        a.proximoPreOrdem(a);
        
        System.out.println("\nPercurso em pós-ordem recursivo:");
        a.visitaPosOrdem(a);
        System.out.println("\nPercurso em pós-ordem iterativo:");
        a.proximoPosOrdem(a);
        
        System.out.println("\nPercurso em nível: ");
        a.proximoEmNivel(a);
        a.visitaEmNivel(a);
        
        
    }
}

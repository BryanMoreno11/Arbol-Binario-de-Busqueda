/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import arbolBinario.*;
import tadCola.Cola;
import tadCola.ColaVacia;
import tadCola.TadCola;



/***
 * SimularOperacionesABB realizará las operaciones del arbol binario que se tienen que visualizar en la vista.
 * También realizará los métodos que necesita el controlador para gráficar de forma correcta el arbol binario de búsqueda
 * @author Steven
 * @param <Integer> 
 */
public class SimularOperacionesABB <Integer extends Comparable<Integer>> extends ArbolBB<Integer>{
    /**
     * Llama al método constructor de la clase clase padre, es decir ArbolBB.
     */
    public SimularOperacionesABB() {
        super();
    }
    /**
     * Nos indica cuantos nodos derechos tiene la rama izquierda del nodo que se pasa como parámetro
     * @param nodo Recibe un nodo del arbol binario de búsqueda
     * @return Nos devuelve el número de nodos derechos que tiene la rama izquierda del nodo que se envio como parámetro
     */
    public int contarHijosDerechosRamaIzquierda(NodoArbol<Integer> nodo){
         if(nodo!=null) 
            return contarHijosDerechosRamaIzquierdaR(nodo.getIz());
         return 0;
    }

    private int contarHijosDerechosRamaIzquierdaR(NodoArbol<Integer> nodo){
        
        if(nodo!=null){
            if(nodo.getDe()!=null)
         return contarHijosDerechosRamaIzquierdaR(nodo.getDe())+1+contarHijosDerechosRamaIzquierdaR(nodo.getIz());  
            else
                return contarHijosDerechosRamaIzquierdaR(nodo.getIz());
        }
           
        return 0;
    }
    /**
     *  Nos indica cuantos nodos izquierdos tiene la rama derecha del nodo que se pasa como parámetro
     * @param nodo Recibe un nodo del arbol binario de búsqueda
     * @return Nos devuelve el número de nodos izquierdos que tiene la rama derecha del nodo que se envio como parámetro
     */
    
    public int contarHijosIzquierdosRamaDerecha(NodoArbol<Integer> nodo){
        if(nodo!=null)
            return contarHijosIzquierdosRamaDerechaR(nodo.getDe());
        return 0;
    }
    private int contarHijosIzquierdosRamaDerechaR(NodoArbol<Integer> nodo){
        if(nodo!=null){
            if(nodo.getIz()!=null)
         return contarHijosIzquierdosRamaDerechaR(nodo.getIz())+1+contarHijosIzquierdosRamaDerechaR(nodo.getDe()); 
            else
                return contarHijosIzquierdosRamaDerechaR(nodo.getDe());
        }        
        return 0;
    }
    /***
     * Retorna en String el recorrido inOrder del arbol.
     * @param <Integer> Este método trabaja con árboles binarios que tengan datos de tipo Integer.
     * @param arbol Recibe un nodo de un  árbol binario de búsqueda.
     * @return Nos retorna  en String el recorrido inOrder del nodo que se paso como parámetro.
     */
    public static <Integer extends Comparable<Integer>> String inOrder(NodoArbol<Integer> arbol) {
        String aux = "";
        return inOrder(arbol, aux);
    }

    private static <Integer extends Comparable<Integer>> String inOrder(NodoArbol<Integer> arbol, String aux) {
        if (arbol != null) {
            if (arbol.getIz() != null) {
                aux = inOrder(arbol.getIz(), aux);
            }
            aux = aux + arbol.getClave() + "  ";
            if (arbol.getDe() != null) {
                aux = inOrder(arbol.getDe(), aux);
            }
            return aux;
        }
        return aux;
    }
    
    /***
     * Retorna en String el recorrido preOrder del arbol.
     * @param <Integer> Este método trabaja con árboles binarios que tengan datos de tipo Integer.
     * @param arbol Recibe un nodo de un árbol binario de búsqueda.
     * @return Nos retorna  en String el recorrido preOrder del nodo que se paso como parámetro.
     */
    public static <Integer extends Comparable<Integer>> String preOrder(NodoArbol<Integer> arbol) {
        String aux = "";
        return preOrder(arbol, aux);
    }

    private static <T extends Comparable<T>> String preOrder(NodoArbol<T> arbol, String aux) {
        if (arbol != null) {
            aux = aux + arbol.getClave() + "  ";
            aux = preOrder(arbol.getIz(), aux);
            aux = preOrder(arbol.getDe(), aux);
            return aux;
        }
        return aux;
    }

    /***
     * Retorna en String el recorrido postOrder del arbol.
     * @param <Integer> Este método trabaja con árboles binarios que tengan datos de tipo Integer.
     * @param arbol Recibe un nodo de un árbol binario de búsqueda.
     * @return Nos retorna  en String el recorrido postOrder del nodo que se paso como parámetro.
     */
    public static <Integer extends Comparable<Integer>> String postOrder(NodoArbol<Integer> arbol) {
        String aux = "";
        return postOrder(arbol, aux);
    }

    private static <T extends Comparable<T>> String postOrder(NodoArbol<T> arbol, String aux) {
        if (arbol != null) {
            aux = postOrder(arbol.getIz(), aux);
            aux = postOrder(arbol.getDe(), aux);
            aux = aux + arbol.getClave() + "  ";
            return aux;
        }
        return aux;
    }
    /***
     * Retorna en String el recorrido la amplitud del arbol.
     * @param <Integer> Este método trabaja con árboles binarios que tengan datos de tipo Integer.
     * @param arbol Recibe un nodo de un árbol binario de búsqueda.
     * @return Nos retorna  en String el recorrido en amplitud del nodo que se paso como parámetro.
     * @throws tadCola.ColaVacia 
     */
    public static <Integer extends Comparable<Integer>> String amplitud(NodoArbol<Integer> arbol) throws ColaVacia {
        String aux = "";
        return amplitud(arbol, aux);
    }

    static <T extends Comparable<T>> String amplitud(NodoArbol<T> arbol, String aux) throws ColaVacia {
        NodoArbol<T> p;
        Cola<NodoArbol<T>> cola = new TadCola<>();

        p = arbol;
        if (p != null) {
            cola.encolar(p);
        }
        while (!cola.colaVacia()) {
            p = cola.desencolar();
            aux += p.getClave() + "  ";
            if (p.getIz() != null) {
                cola.encolar(p.getIz());
            }
            if (p.getDe() != null) {
                cola.encolar(p.getDe());
            }
        }
        return aux;
    }
}

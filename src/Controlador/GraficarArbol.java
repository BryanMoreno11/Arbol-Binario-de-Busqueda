/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import arbolBinario.ArbolBB;
import arbolBinario.NodoArbol;
import Modelo.SimularOperacionesABB;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * Esta clase se encarga de dibujar el arbol binario de búsqueda que veremos en
 * la vista del programa.
 *
 * @author Steven
 */
public class GraficarArbol extends JPanel {

    private SimularOperacionesABB abb;
    /**
     * Contruye un objeto GraficarArbol con una dimensión de 675x480 e inicializa la referencia de la clase
     * SimularOperacionesABB.
     */
    public GraficarArbol() {
        setPreferredSize(new Dimension(675, 480));
        this.abb = new SimularOperacionesABB();
    }
    /**
     * Retorna una referencia de la clase SimularOperacionesABB.
     * @return Retorna una referencia de la clase SimularOperacionesABB.
     */
    public ArbolBB<Integer> getAbb() {
        return abb;
    }
    /**
     * Establece el valor que tendrá la referencia de la clase SimularOperacionesABB.
     * @param abb Recibe una referencia de la clase SimularOperacionesABB. 
     */
    public void setAbb(SimularOperacionesABB<Integer> abb) {
        this.abb = abb;
    }

    /**
     * *
     * Este método se encarga de dibujar el arbol binario de búsqueda que
     * veremos en la vista del programa.
     *
     * @param g2d Recibe un objeto Graphics2D que será el encargado de dibujar
     * los elementos del árbol binario de búsqueda.
     * @param x Recibe la posición en el eje de las x donde se situará un nodo.
     * @param y Recibe la posición en el eje de las y donde se situará un nodo.
     * @param n Recibe un nodo de un arbol binario de búsqueda del que obtendrá
     * los valores a graficar.
     */
    public void dibujar(Graphics2D g2d, int x, int y, NodoArbol<Integer> n) {
        int extraIzq;
        int extraDer;
        if (n != null) {
            String dato = n.getClave().toString();
            Rectangle r = new Rectangle(x, y, 65, 20);
            g2d.draw(r);
            g2d.drawString(dato, (int) r.getMinX() + 2, (int) r.getCenterY() + 4);
            extraIzq = calcularExtraIzq(n, r.width);
            extraDer = calcularExtraDer(n, r.width);
            if (n.getIz() != null) {
                g2d.drawLine((int) (r.getMinX() - 20 - extraIzq), (int) r.getMaxY() + 40, (int) r.getMinX(), (int) r.getMaxY());
            }
            if (n.getDe() != null) {
                g2d.drawLine(((int) (r.getMaxX() + 20 + extraDer)), (int) r.getMaxY() + 40, (int) r.getMaxX(), (int) r.getMaxY());
            }
            dibujar(g2d, (int) (r.getMinX() - r.getWidth() - extraIzq), (int) r.getMaxY() + 40, n.getIz());
            dibujar(g2d, (int) (r.getMaxX() + extraDer), (int) r.getMaxY() + 40, n.getDe());
        }

    }
    /**
     * Este método inicializa los componentes necesarios para pintar el árbol binario de búsqueda
     * @param g El contexto gráfico en el que pintar.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        redimensionarPanel();
        dibujar(g2d, this.getSize().width / 2, 10, abb.getRaiz());
    }

    /**
     * *
     * Este método nos devuelve la distancia extra hacia la izquierda que deben
     * tener los nodos y subramas de la rama izquierda del nodo que se paso como
     * párametro para evitar que los nodos de la rama izquierda se
     * choquen/combinen con los de las subramas izquierdas de la rama derecha.
     *
     * @param nodo Recibe el nodo con el que determinará la distancia extra
     * hacia la izquierda que deberán tener los nodos y subramas de la rama
     * izquierda de dicho nodo.
     * @param width Recibe como parámetro el ancho de un nodo.
     * @return Nos retorna la distancia que debe desplazarse hacia la izquierda
     * los nodos y subramas de la rama izquierda del nodo que se paso como
     * parámetro.
     */
    public int calcularExtraIzq(NodoArbol<Integer> nodo, int width) {
        if (abb.contarHijosDerechosRamaIzquierda(nodo) != 0 || abb.contarHijosIzquierdosRamaDerecha(nodo) != 0) {
            int diferencia = abb.contarHijosDerechosRamaIzquierda(nodo) - abb.contarHijosIzquierdosRamaDerecha(nodo);
            if (diferencia <= 0) {
                if (abb.contarHijosIzquierdosRamaDerecha(nodo.getDe()) != 0 && abb.contarHijosDerechosRamaIzquierda(nodo.getDe()) != 0) {
                    if (abb.contarHijosDerechosRamaIzquierda(nodo.getDe()) >= abb.contarHijosIzquierdosRamaDerecha(nodo.getDe())) 
                        return (abb.contarHijosDerechosRamaIzquierda(nodo)) * width + abb.contarHijosIzquierdosRamaDerecha(nodo.getDe()) * width;
                     else 
                        return (abb.contarHijosDerechosRamaIzquierda(nodo)) * width + abb.contarHijosDerechosRamaIzquierda(nodo.getDe()) * width;
                }
                return width * abb.contarHijosDerechosRamaIzquierda(nodo);
            } else if (diferencia > 0) {
                if (abb.contarHijosIzquierdosRamaDerecha(nodo.getDe()) != 0 && abb.contarHijosDerechosRamaIzquierda(nodo.getDe()) != 0) {
                    if (abb.contarHijosDerechosRamaIzquierda(nodo.getDe()) >= abb.contarHijosIzquierdosRamaDerecha(nodo.getDe())) 
                        return (abb.contarHijosIzquierdosRamaDerecha(nodo)) * width + abb.contarHijosIzquierdosRamaDerecha(nodo.getDe()) * width;
                     else 
                        return (abb.contarHijosIzquierdosRamaDerecha(nodo)) * width + abb.contarHijosDerechosRamaIzquierda(nodo.getDe()) * width;
                }
                return (abb.contarHijosIzquierdosRamaDerecha(nodo)) * width;
            }

        }
        return 0;
    }

    /**
     * *
     * Este método nos devuelve la distancia extra hacia la derecha que deben
     * tener los nodos y subramas de la rama derecha del nodo que se paso como
     * párametro para evitar que los nodos de la rama derecha se
     * choquen/combinen con los de las subramas derechas de la rama izquierda.
     *
     * @param nodo Recibe el nodo con el que determinará la distancia extra
     * hacia la derecha que deberán tener los nodos y subramas de la rama
     * derecha de dicho nodo.
     * @param width Recibe como parámetro el ancho de un nodo.
     * @return Nos retorna la distancia que debe desplazarse hacia la derecha
     * los nodos y subramas de la rama derecha del nodo que se paso como
     * parámetro.
     */
    public int calcularExtraDer(NodoArbol<Integer> nodo, int width) {
        if (abb.contarHijosDerechosRamaIzquierda(nodo) != 0 || abb.contarHijosIzquierdosRamaDerecha(nodo) != 0) {
            int diferencia = abb.contarHijosIzquierdosRamaDerecha(nodo) - abb.contarHijosDerechosRamaIzquierda(nodo);
            if (diferencia <= 0) {
                if (abb.contarHijosIzquierdosRamaDerecha(nodo.getIz()) != 0 && abb.contarHijosDerechosRamaIzquierda(nodo.getIz()) != 0) {
                    if (abb.contarHijosIzquierdosRamaDerecha(nodo.getIz()) >= abb.contarHijosDerechosRamaIzquierda(nodo.getIz())) 
                        return (abb.contarHijosIzquierdosRamaDerecha(nodo)) * width + abb.contarHijosDerechosRamaIzquierda(nodo.getIz()) * width;
                     else 
                        return (abb.contarHijosIzquierdosRamaDerecha(nodo)) * width + abb.contarHijosIzquierdosRamaDerecha(nodo.getIz()) * width;
                }
                return width * abb.contarHijosIzquierdosRamaDerecha(nodo);
            } else if (diferencia > 0) {
                if (abb.contarHijosIzquierdosRamaDerecha(nodo.getIz()) != 0 && abb.contarHijosDerechosRamaIzquierda(nodo.getIz()) != 0) {
                    if (abb.contarHijosIzquierdosRamaDerecha(nodo.getIz()) >= abb.contarHijosDerechosRamaIzquierda(nodo.getIz())) 
                        return (abb.contarHijosDerechosRamaIzquierda(nodo)) * width + abb.contarHijosDerechosRamaIzquierda(nodo.getIz()) * width;
                     else 
                        return (abb.contarHijosDerechosRamaIzquierda(nodo)) * width + abb.contarHijosIzquierdosRamaDerecha(nodo.getIz()) * width;
                }
                return (abb.contarHijosDerechosRamaIzquierda(nodo)) * width;
            }
        }
        return 0;
    }
    /**
     * Este método aumenta las dimensiones del panel según se incremente la altura del árbol o la distancia horizontal que hay entre los
     * nodos.
     */
    public void redimensionarPanel() {
        if (abb.altura() > 7) {
            if (calcularExtraIzq(abb.getRaiz(), 135) >= calcularExtraDer(abb.getRaiz(), 135)) 
                this.setPreferredSize(new Dimension(135 * abb.altura() + calcularExtraIzq(abb.getRaiz(), 135), 60 * abb.altura()));
             else 
                this.setPreferredSize(new Dimension(135 * abb.altura() + calcularExtraDer(abb.getRaiz(), 135), 60 * abb.altura()));
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author OJavierHR
 * @param <K>
 * @param <V> 
 */
public class EjerciciosABBI<K extends Comparable<K>, V>
        extends ArbolBinarioBusquedaRecursivo<K, V> {

    public EjerciciosABBI() {
    }

    /*
        Implementar metodo que cuente las hojas del arbol 
     */
    public int contarHojas() {
        int hojas = 0;
        if(!esVacio()){
            Stack<NodoBinario<K, V>> pilaAux = new Stack();
            NodoBinario<K, V> nodoActual;
            
            pilaAux.add(getRaiz());
            while(!pilaAux.isEmpty()){
                nodoActual = pilaAux.pop();
                if(!nodoActual.esVacioHijoDerecho()){
                    pilaAux.add(nodoActual.getHijoDerecho());
                }
                if(!nodoActual.esVacioHijoIzquierdo()){
                    pilaAux.add(nodoActual.getHijoIzquierdo());
                }
                if(nodoActual.esHoja()){
                    hojas++;
                }
            }
        }
        return hojas;
    }
    
    /*
        Implementar metodo que cuente las hojas del arbol en un determinado 
        nivel
    */
    public int contarHojasNivel(int nivel){
        int hojas = 0;
        if(!esVacio()){
            Queue<NodoBinario<K, V>> colaAux = new LinkedList();
            NodoBinario<K, V> nodoActual;
            int nodosEnNivel;
            int nivelActual = 0;
            
            colaAux.add(getRaiz());
            while(!colaAux.isEmpty()){
                nodosEnNivel = colaAux.size();
                while(nodosEnNivel > 0){
                    nodoActual = colaAux.poll();
                    if(!nodoActual.esVacioHijoIzquierdo()){
                        colaAux.add(nodoActual.getHijoIzquierdo());
                    }
                    if(!nodoActual.esVacioHijoDerecho()){
                        colaAux.add(nodoActual.getHijoDerecho());
                    }
                    if(nivelActual == nivel && nodoActual.esHoja()){
                        hojas++;
                    }
                    nodosEnNivel--;
                }
                nivelActual++;
            }
        }
        return hojas;
    }
}

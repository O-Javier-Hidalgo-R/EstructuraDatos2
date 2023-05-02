/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Stack;

import bo.uagrm.ficct.ed2.arbolbusqueda.IArbolBusqueda;

/**
 * Clase que permite la implementacion de una estructura de datos basada en el
 * tipo de dato abstracto de arbol binario de busqueda (usa solo instrucciones
 * iterativas)
 *
 * @author OJavierHR
 * @param <K> tipo de datos del que sera la clave de los nodos del arbol.
 * @param <V> tipo de datos del que sera el valor de los nodos del arbol.
 */
public class ArbolBinarioBusquedaIterativo<K extends Comparable<K>, V>
        implements IArbolBusqueda<K, V> {

    /**
     * Nodo inicial del arbol.
     */
    protected NodoBinario<K, V> raiz;

    /**
     * Constructor por defecto.
     */
    public ArbolBinarioBusquedaIterativo() {
    }

    /**
     * Retorna la raiz del arbol.
     *
     * @return raiz
     */
    public NodoBinario<K, V> getRaiz() {
        return raiz;
    }

    /**
     * Ingresa un valor a la raiz de la clase.
     *
     * @param raiz
     */
    public void setRaiz(NodoBinario<K, V> raiz) {
        this.raiz = raiz;
    }

    /**
     * Elimina la raiz (vacia todo el arbol).
     */
    @Override
    public void vaciar() {
        setRaiz((NodoBinario<K, V>) NodoBinario.nodoVacio());
    }

    /**
     * Verifica si el arbol esta vacio.
     *
     * @return True si el arbol esta vacio
     */
    @Override
    public boolean esVacio() {
        return NodoBinario.esVacio(raiz);
    }

    /**
     * Inserta un nuevo nodo (por sus parametros) en el lugar que le corresponda
     * en funcion de la clave, en caso de ya estar en el arbol la clave
     * sobreescribe el valor del nodo.
     *
     * @param clave del nodo a insertar
     * @param valor del nodo a insertar
     */
    @Override
    public void insertar(K clave, V valor) {
        if (esVacio()) {
            setRaiz(new NodoBinario<>(clave, valor));
        } else {
            NodoBinario<K, V> nodoActual = getRaiz();
            NodoBinario<K, V> nodoPadreActual = new NodoBinario<>();
            while (!NodoBinario.esVacio(nodoActual)) {
                nodoPadreActual = nodoActual;
                if (clave.compareTo(nodoActual.getClave()) < 0) {
                    nodoActual = nodoActual.getHijoIzquierdo();
                } else {
                    nodoActual = nodoActual.getHijoDerecho();
                }
            }
            if (clave.compareTo(nodoPadreActual.getClave()) < 0) {
                nodoPadreActual.setHijoIzquierdo(new NodoBinario(clave, valor));
            } else {
                nodoPadreActual.setHijoDerecho(new NodoBinario(clave, valor));
            }
        }
    }

    /**
     * Funcion que retorna el valor de un nodo buscado por valor de la clave,
     * retorna null si no existe
     *
     * @param clave se usa para buscar en el arbol
     * @return valor que se encuentre en el nodo a buscar
     */
    @Override
    public V buscar(K clave) {
        NodoBinario<K, V> nodoActual = getRaiz();
        while (!NodoBinario.esVacio(nodoActual)) {
            if (clave.compareTo(nodoActual.getClave()) == 0) {
                return nodoActual.getValor();
            } else if (clave.compareTo(nodoActual.getClave()) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
        return null;
    }

    /**
     * Valida si en el arbol existe un nodo con la clave especificada por
     * parametros.
     *
     * @param clave se verifica si existe en el arbol.
     * @return True si la clave ya existe en el arbol.
     */
    @Override
    public boolean contiene(K clave) {
        return buscar(clave) != null;
    }

    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos
     * respetando como criterio el numero de nivel y el orden de izquierda a
     * derecha.
     *
     * @return lista con el recorrido.
     */
    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (!esVacio()) {
            Queue<NodoBinario<K, V>> colaAux = new LinkedList<>();
            NodoBinario<K, V> nodoActual;
            int nodosEnNivel;
            //Si se requiere identificar el nivel en el que se encuentra
            int nivel = 0;

            colaAux.add(getRaiz());
            while (!colaAux.isEmpty()) {
                nodosEnNivel = colaAux.size();
                while (nodosEnNivel > 0) {
                    nodoActual = colaAux.poll();
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        colaAux.add(nodoActual.getHijoIzquierdo());
                    }
                    if (!nodoActual.esVacioHijoDerecho()) {
                        colaAux.add(nodoActual.getHijoDerecho());
                    }
                    nodosEnNivel--;
                    recorrido.add(nodoActual.getClave());
                }
                //Indicamos que vamos al siquiente nivel
                nivel++;
            }
        }
        return recorrido;
    }

    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos 
     * siguiendo un recorrido preorden. 
     * @return lista con el recorrido en preorden
     */
    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();

        if (!esVacio()) {
            NodoBinario<K, V> nodoActual;
            Stack<NodoBinario<K, V>> pilaAux = new Stack<>();

            pilaAux.push(getRaiz());
            while (!pilaAux.isEmpty()) {
                nodoActual = pilaAux.pop();
                if (!nodoActual.esVacioHijoDerecho()) {
                    pilaAux.push(nodoActual.getHijoDerecho());
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    pilaAux.push(nodoActual.getHijoIzquierdo());
                }
                recorrido.add(nodoActual.getClave());
            }
        }
        return recorrido;
    }

    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos en 
     * inorden.
     * @return lista con el recorrido inorden
     */
    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new ArrayList<>();

        if (!esVacio()) {
            NodoBinario<K, V> nodoActual = getRaiz();
            Stack<NodoBinario<K, V>> pilaAux = new Stack<>();

            while (!pilaAux.isEmpty() || !NodoBinario.esVacio(nodoActual)) {
                if (!NodoBinario.esVacio(nodoActual)) {
                    pilaAux.push(nodoActual);
                    nodoActual = nodoActual.getHijoIzquierdo();
                } else {
                    nodoActual = pilaAux.pop();
                    recorrido.add(nodoActual.getClave());
                    nodoActual = nodoActual.getHijoDerecho();
                }
            }
        }
        return recorrido;
    }
    
    /**
     * Retorna le recorrido resltado de un recorrido en pos orde por el 
     * arbol.
     * @return recorrido posorden 
     */
    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        if (!esVacio()) {
            NodoBinario<K, V> nodoActual = getRaiz();
            NodoBinario<K, V> nodoUltimoDesapilado = new NodoBinario<>();
            Stack<NodoBinario<K, V>> pilaAux = new Stack<>();
            while (!pilaAux.isEmpty() || !NodoBinario.esVacio(nodoActual)) {
                if (!NodoBinario.esVacio(nodoActual)) {
                    pilaAux.push(nodoActual);
                    nodoActual = nodoActual.getHijoIzquierdo();
                } else {
                    nodoActual = pilaAux.peek().getHijoDerecho();
                    if (NodoBinario.esVacio(nodoActual)
                            || nodoActual == nodoUltimoDesapilado) {
                        nodoUltimoDesapilado = pilaAux.pop();
                        recorrido.add(nodoUltimoDesapilado.getClave());
                        nodoActual
                                = (NodoBinario<K, V>) NodoBinario.nodoVacio();
                    }
                }
            }
        }
        return recorrido;
    }

    /**
     * Elimina el nodo con la clave especificada por parametros.
     * @param clave valor usado para especificar el nodo a eliminar
     * @throws IllegalArgumentException 
     */
    @Override
    public void eliminar(K clave) {
        NodoBinario<K, V> nodoEliminando = getRaiz();
        NodoBinario<K, V> nodoPadreEliminando = new NodoBinario<>();
        
        while (!NodoBinario.esVacio(nodoEliminando) && clave.compareTo(nodoEliminando.getClave()) != 0) {
            nodoPadreEliminando = nodoEliminando;
            if (!nodoEliminando.esVacioHijoIzquierdo()) {
                nodoEliminando = nodoEliminando.getHijoIzquierdo();
            } else {
                nodoEliminando = nodoEliminando.getHijoDerecho();
            }
        }
        if (!NodoBinario.esVacio(nodoEliminando)) {
            NodoBinario<K, V> nodoRemplazo;
            if (nodoEliminando.esIncompleto()) {
                if (!nodoEliminando.esVacioHijoIzquierdo()) {
                    nodoRemplazo = nodoEliminando.getHijoIzquierdo();
                } else {
                    nodoRemplazo = nodoEliminando.getHijoDerecho();
                }
                if (NodoBinario.esVacio(nodoPadreEliminando)) {
                    setRaiz(nodoRemplazo);
                } else {
                    if (nodoPadreEliminando.getHijoIzquierdo() == nodoEliminando) {
                        nodoPadreEliminando.setHijoIzquierdo(nodoRemplazo);
                    } else {
                        nodoPadreEliminando.setHijoDerecho(nodoRemplazo);
                    }
                }
            } else {
                NodoBinario<K, V> nodoPadreRemplazo = nodoEliminando;

                nodoRemplazo = nodoEliminando.getHijoDerecho();
                while (!nodoRemplazo.esVacioHijoIzquierdo()) {
                    nodoPadreRemplazo = nodoRemplazo;
                    nodoRemplazo = nodoRemplazo.getHijoIzquierdo();
                }
                nodoEliminando.setValor(nodoRemplazo.getValor());
                nodoEliminando.setClave(nodoRemplazo.getClave());
                if (nodoPadreRemplazo.getHijoDerecho() == nodoRemplazo) {
                    nodoPadreRemplazo.setHijoDerecho(nodoRemplazo.getHijoDerecho());
                } else {
                    nodoPadreRemplazo.setHijoIzquierdo(nodoRemplazo.getHijoDerecho());
                }
            }
        }
    }

    /**
     * Retorna la cantidad de nodos no vacios en el arbol
     * @return cantidad de nodos
     */
    @Override
    public int size() {
        int contador = 0;
        if(!esVacio()){
            NodoBinario<K, V> nodoActual;
            int nodoEnNivel;
            Queue<NodoBinario<K, V>> colaAux = new LinkedList();
            
            colaAux.add(getRaiz());
            while (!colaAux.isEmpty()) {                
                nodoEnNivel = colaAux.size();
                while(nodoEnNivel > 0){
                    nodoActual = colaAux.poll();
                    if(!nodoActual.esVacioHijoIzquierdo()){
                        colaAux.add(nodoActual.getHijoIzquierdo());
                    }
                    if(!nodoActual.esVacioHijoDerecho()){
                        colaAux.add(nodoActual.getHijoDerecho());
                    }
                    nodoEnNivel--;
                    contador++;
                }
            }
        }
        return contador;
    }

    /**
     * Muestra la altura del arbol, que es conteo de niveles del arbol 
     * comenzando desde 1
     * @return altura del arbol
     */
    @Override
    public int altura() {
        int alt = 0;
        if(!esVacio()){
            NodoBinario<K, V> nodoActual;
            Queue<NodoBinario<K, V>> colaAux = new LinkedList<>();
            int nodosEnNivel;
            
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
                    nodosEnNivel--;
                }
                alt++;
            }
        }
        return alt;
    }

    /**
     * Muestra el nivel del arbol, que es el conteo de los niveles comenzando 
     * desde 0
     * @return 
     */
    @Override
    public int nivel() {
        return altura() - 1;
    }
}
 
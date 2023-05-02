/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 * to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

import bo.uagrm.ficct.ed2.arbolbusqueda.IArbolBusqueda;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la implementacion de una estructura de datos basada en 
 * el tipo de dato abstracto de arbol binario de busqueda (usa solo 
 * instrucciones recursivas)
 * @author OJavierHR
 * @param <K> tipo de datos del que sera la clave de los nodos del arbol.
 * @param <V> tipo de datos del que sera el valor de los nodos del arbol.
 */
public class ArbolBinarioBusquedaRecursivo<K extends Comparable<K>, V> 
        implements IArbolBusqueda<K, V>{
    
    /**
     * Nodo inicial del arbol.
     */
    protected NodoBinario<K, V> raiz;

    /**
     * Constructor por defecto.
     */
    public ArbolBinarioBusquedaRecursivo() {
        
    }

    /**
     * Constructor por copia
     * @param raiz 
     */
    public ArbolBinarioBusquedaRecursivo(NodoBinario<K, V> raiz) {
        this.raiz = raiz;
    }
    
    public ArbolBinarioBusquedaRecursivo(List<K> clavesInOrden, 
            List<V> valoresInOrden, List<K> clavesNoInOrden, 
            List<V> valoresNoInOrden, boolean usandoPreOrden){
        if(usandoPreOrden){
            setRaiz(reconstruirConPreOrden(clavesInOrden, valoresInOrden, 
                    clavesNoInOrden, valoresNoInOrden));
        }else{
            setRaiz(reconstruirConPostOrden(clavesInOrden, valoresInOrden, 
                    clavesNoInOrden, valoresNoInOrden));
        }
    }
    
    private NodoBinario<K, V> reconstruirConPreOrden(List<K> clavesInOrden, 
            List<V> valoresInOrden, List<K> clavesPreOrden, 
            List<V> valoresPreOrden) {
        if(clavesInOrden.isEmpty()){
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }else{
            K clavePadre = clavesPreOrden.get(0);
            V valorPadre = valoresPreOrden.get(0);
            int posicionClaveInOrden = posicionDeClave(clavePadre, clavesInOrden);
            //parte izquierda
            List<K> clavesInOrdeIzq = clavesInOrden.subList(0, posicionClaveInOrden);
            List<V> valoresInOrdeIzq = valoresInOrden.subList(0, posicionClaveInOrden);
            List<K> clavesPreOrdeIzq = clavesPreOrden.subList(1, posicionClaveInOrden +1);
            List<V> valoresPreOrdeIzq = valoresPreOrden.subList(1, posicionClaveInOrden +1);
            //parte derecha
            List<K> clavesInOrdeDer = clavesInOrden.subList(posicionClaveInOrden +1, clavesInOrden.size());
            List<V> valoresInOrdeDer = valoresInOrden.subList(posicionClaveInOrden +1, clavesInOrden.size());
            List<K> clavesPreOrdeDer = clavesPreOrden.subList(posicionClaveInOrden +1, clavesInOrden.size());
            List<V> valoresPreOrdeDer = valoresPreOrden.subList(posicionClaveInOrden +1, clavesInOrden.size());
            //armamos
            NodoBinario<K, V> nodoPadre = new NodoBinario<>(clavePadre, valorPadre);
            nodoPadre.setHijoIzquierdo(reconstruirConPreOrden(clavesInOrdeIzq, valoresInOrdeIzq, clavesPreOrdeIzq, valoresPreOrdeIzq));
            nodoPadre.setHijoDerecho(reconstruirConPreOrden(clavesInOrdeDer, valoresInOrdeDer, clavesPreOrdeDer, valoresPreOrdeDer));
            return nodoPadre;
        }
    }
    
    private NodoBinario<K, V> reconstruirConPostOrden(List<K> clavesInOrden, 
            List<V> valoresInOrden, List<K> clavesPostOrden, 
            List<V> valoresPostOrden) {
        if(clavesInOrden.isEmpty()){
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }else{
            K clavePadre = clavesPostOrden.get(clavesPostOrden.size()-1);
            V valorPadre = valoresPostOrden.get(valoresPostOrden.size()-1);
            int posicionClaveInOrden = posicionDeClave(clavePadre, clavesInOrden);
            //parte izquierda
            List<K> clavesInOrdeIzq = clavesInOrden.subList(0, posicionClaveInOrden);
            List<V> valoresInOrdeIzq = valoresInOrden.subList(0, posicionClaveInOrden);
            List<K> clavesPostOrdeIzq = clavesPostOrden.subList(0, posicionClaveInOrden);
            List<V> valoresPostOrdenIzq = valoresPostOrden.subList(0, posicionClaveInOrden);
            //parte derecha
            List<K> clavesInOrdeDer = clavesInOrden.subList(posicionClaveInOrden +1, clavesInOrden.size());
            List<V> valoresInOrdeDer = valoresInOrden.subList(posicionClaveInOrden +1, clavesInOrden.size());
            List<K> clavesPostOrdenDer = clavesPostOrden.subList(posicionClaveInOrden, clavesInOrden.size()-1);
            List<V> valoresPostOrdenDer = valoresPostOrden.subList(posicionClaveInOrden, clavesInOrden.size()-1);
            //armamos
            NodoBinario<K, V> nodoPadre = new NodoBinario<>(clavePadre, valorPadre);
            nodoPadre.setHijoIzquierdo(reconstruirConPostOrden(clavesInOrdeIzq, valoresInOrdeIzq, clavesPostOrdeIzq, valoresPostOrdenIzq));
            nodoPadre.setHijoDerecho(reconstruirConPostOrden(clavesInOrdeDer, valoresInOrdeDer, clavesPostOrdenDer, valoresPostOrdenDer));
            return nodoPadre;
        }
    }
    
    private int posicionDeClave(K claveABuscar, List<K> listaDeClaves){
        for (int i = 0; i < listaDeClaves.size(); i++) {
            K claveActual = listaDeClaves.get(i);
            if(claveActual.compareTo(claveABuscar) == 0){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Retorna la raiz del arbol.
     * @return raiz
     */
    public NodoBinario<K, V> getRaiz() {
        return raiz;
    }

    /**
     * Ingresa un valor a la raiz de la clase.
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
     * @return True si el arbol esta vacio
     */
    @Override
    public boolean esVacio() {
        return NodoBinario.esVacio(raiz);
    }

    /**
     * Inserta un nuevo nodo (por sus parametros) en el lugar que le 
     * corresponda en funcion de la clave, en caso de ya estar en el arbol la 
     * clave sobreescribe el valor del nodo.
     * @param clave del nodo a insertar
     * @param valor del nodo a insertar
     */
    @Override
    public void insertar(K clave, V valor) {
        setRaiz(insertarR(getRaiz(), new NodoBinario<>(clave, valor)));
    }

    private NodoBinario<K, V> insertarR(NodoBinario<K, V> raiz, 
            NodoBinario<K, V> nuevoNodo) {
        if (NodoBinario.esVacio(raiz)) {
            return nuevoNodo;
        } else if (nuevoNodo.getClave().compareTo(raiz.getClave()) == 0) {
            raiz.setValor(nuevoNodo.getValor());
        } else if (nuevoNodo.getClave().compareTo(raiz.getClave()) < 0) {
            raiz.setHijoIzquierdo(insertarR(
                    raiz.getHijoIzquierdo(), nuevoNodo));
        } else {
            raiz.setHijoDerecho(insertarR(
                    raiz.getHijoDerecho(), nuevoNodo));
        }
        return raiz;
    }

    /**
     * Funcion que retorna el valor de un nodo buscado por valor de la clave,
     * retorna null si no existe
     * @param clave se usa para buscar en el arbol
     * @return valor que se encuentre en el nodo a buscar
     */
    @Override
    public V buscar(K clave) {
        return buscarR(getRaiz(), clave);
    }

    public V buscarR(NodoBinario<K, V> nodoActual, K clave) {
        if (NodoBinario.esVacio(nodoActual)) {
            return null;
        }else if (clave.compareTo(nodoActual.getClave()) == 0) {
            return nodoActual.getValor();
        } else if (clave.compareTo(nodoActual.getClave()) < 0) {
            return buscarR(nodoActual.getHijoIzquierdo(), clave);
        } else {
            return buscarR(nodoActual.getHijoDerecho(), clave);
        }
    }

    /**
     * Valida si en el arbol existe un nodo con la clave especificada por 
     * parametros.
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
     * @return lista con el recorrido.
     */
    @Override
    public List<K> recorridoPorNiveles() {
        return recorridoPorNivelesR(getRaiz());
    }

    private List<K> recorridoPorNivelesR(NodoBinario<K, V> nodoActual) {
        List<K> recorrido = new ArrayList<>();
        int nivel = nivelR(nodoActual);
        for (int i = 0; i <= nivel; i++) {
            recorrerNivel(nodoActual, i, recorrido);
        }
        return recorrido;
    }

    private void recorrerNivel(NodoBinario<K, V> nodoActual, int nivel, 
            List<K> recorrido) {
        if (!NodoBinario.esVacio(nodoActual)) {
            if (nivel == 0) {
                recorrido.add(nodoActual.getClave());
            } else {
                recorrerNivel(nodoActual.getHijoIzquierdo(), 
                        nivel - 1, recorrido);
                recorrerNivel(nodoActual.getHijoDerecho(), 
                        nivel - 1, recorrido);
            }
        }
    }

    private int nivelR(NodoBinario<K, V> nodoActual) {
        if (!NodoBinario.esVacio(nodoActual)) {
            int izq = nivelR(nodoActual.getHijoIzquierdo()) + 1;
            int der = nivelR(nodoActual.getHijoDerecho()) + 1;
            if (izq > der) {
                return izq;
            } else {
                return der;
            }
        }
        return -1;
    }
    
    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos 
     * siguiendo un recorrido preorden. 
     * @return lista con el recorrido en preorden
     */
    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPreOrdenR(getRaiz(), recorrido);
        return recorrido;
    }
    
    private void recorridoEnPreOrdenR(NodoBinario<K, V> raiz, List<K> recorrido) {
        if(NodoBinario.esVacio(raiz)){
            return;
        }
        recorrido.add(raiz.getClave());
        recorridoEnPreOrdenR(raiz.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenR(raiz.getHijoDerecho(), recorrido);
    }
    
    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos en 
     * inorden.
     * @return lista con el recorrido inorden
     */
    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnInOrdenR(getRaiz(), recorrido);
        return recorrido;
    }
    
    private void recorridoEnInOrdenR(NodoBinario<K, V> raiz, List<K> recorrido) {
        if(NodoBinario.esVacio(raiz)){
            return;
        }
        recorridoEnInOrdenR(raiz.getHijoIzquierdo(), recorrido);
        recorrido.add(raiz.getClave());
        recorridoEnInOrdenR(raiz.getHijoDerecho(), recorrido);
    }
    
    /**
     * Retorna le recorrido resltado de un recorrido en pos orde por el 
     * arbol.
     * @return recorrido posorden 
     */
    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPosOrdenR(getRaiz(), recorrido);
        return recorrido;
    }
    
    private void recorridoEnPosOrdenR(NodoBinario<K, V> raiz, List<K> recorrido) {
        if(NodoBinario.esVacio(raiz)){
            return;
        }
        recorridoEnPosOrdenR(raiz.getHijoIzquierdo(), recorrido);
        recorridoEnPosOrdenR(raiz.getHijoDerecho(), recorrido);
        recorrido.add(raiz.getClave());
    }

    /**
     * Elimina el nodo con la clave especificada por parametros.
     * @param clave valor usado para especificar el nodo a eliminar
     * @throws IllegalArgumentException 
     */
    @Override
    public void eliminar(K clave) {
        setRaiz(eliminarR(getRaiz(), clave));
    }

    private NodoBinario<K, V> eliminarR(NodoBinario<K, V> nodoActual, K clave) {
        if (NodoBinario.esVacio(nodoActual)) {
            return nodoActual;
        } else if (clave.compareTo(nodoActual.getClave()) == 0) {
            if (nodoActual.esIncompleto()) {
                if (clave.compareTo(nodoActual.getClave()) < 0) {
                    return nodoActual.getHijoIzquierdo();
                } else {
                    return nodoActual.getHijoDerecho();
                }
            } else {
                NodoBinario<K, V> nodoRemplazo = nodoMenor(nodoActual.getHijoDerecho());
                nodoActual.setClave(nodoRemplazo.getClave());
                nodoActual.setValor(nodoRemplazo.getValor());
                nodoActual.setHijoDerecho(eliminarR(nodoActual.getHijoDerecho(), nodoRemplazo.getClave()));
            }
        } else {
            if (clave.compareTo(nodoActual.getClave()) < 0) {
                nodoActual.setHijoIzquierdo(eliminarR(nodoActual.getHijoIzquierdo(), clave));
            } else {
                nodoActual.setHijoDerecho(eliminarR(nodoActual.getHijoDerecho(), clave));
            }
        }
        return nodoActual;
    }
    
    private NodoBinario<K, V> nodoMenor(NodoBinario<K, V> raiz){
        if(raiz.esVacioHijoIzquierdo()){
            return raiz;
        }
        return nodoMenor(raiz.getHijoIzquierdo());
    }
    
    /**
     * Retorna la cantidad de nodos no vacios en el arbol
     * @return cantidad de nodos
     */
    @Override
    public int size() {
        return sizeR(getRaiz());
    }
    
    private int sizeR(NodoBinario<K, V> nodoActual){
        if(NodoBinario.esVacio(nodoActual)){
            return 0;
        }
        return sizeR(nodoActual.getHijoIzquierdo()) + 
                sizeR(nodoActual.getHijoDerecho()) + 
                1;
    }
    
    /**
     * Muestra la altura del arbol, que es conteo de niveles del arbol 
     * comenzando desde 1
     * @return altura del arbol
     */
    @Override
    public int altura() {
        return nivelR(raiz) + 1;
    }
    
    /**
     * Muestra el nivel del arbol, que es el conteo de los niveles comenzando 
     * desde 0
     * @return 
     */
    @Override
    public int nivel() {
        return nivelR(raiz);
    }
}
 
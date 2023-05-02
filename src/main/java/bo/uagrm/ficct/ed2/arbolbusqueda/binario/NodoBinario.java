/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

/**
 * Clase que se usa para implementar un nodo binario usado en arboles binarios 
 * de busqueda
 * @author OJavierHR
 * @param <K> Atributo para la clave
 * @param <V> Atributo para el valor
 */
public class NodoBinario<K extends Comparable<K>, V>{
    
    private K clave;
    private V valor;
    private NodoBinario hijoDerecho;
    private NodoBinario hijoIzquierdo;

    /**
     * Constructor vacio de la clase
     */
    public NodoBinario() {
    
    }
    
    /**
     * Constructor por copia de la clase
     * @param copiando Nodo binario del que se copian los valores
     */
    public NodoBinario(NodoBinario<K, V> copiando){
        this.clave = copiando.getClave();
        this.valor = copiando.getValor();
        this.hijoDerecho = copiando.getHijoDerecho();
        this.hijoIzquierdo = copiando.getHijoIzquierdo();
    }

    /**
     * Constructor parametrizado
     * @param clave Valor de la clave 
     * @param valor Valor del "valor" de la clave
     */
    public NodoBinario(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    /**
     * Muestra el valor de la clave del nodo.
     * @return clave
     */
    public K getClave() {
        return clave;
    }

    /**
     * Permite cambiar el valor de la clave del nodo
     * @param clave 
     */
    public void setClave(K clave) {
        this.clave = clave;
    }

    /**
     * Muestra el valor del "valor" del nodo
     * @return valor
     */
    public V getValor() {
        return valor;
    }

    /**
     * Permite cambiar el valor de la clave del valor
     * @param valor 
     */
    public void setValor(V valor) {
        this.valor = valor;
    }

    /**
     * Muestra el hijo derecho del nodo
     * @return 
     */
    public NodoBinario<K, V> getHijoDerecho() {
        return hijoDerecho;
    }

    /**
     * Permite cambiar el valor del hijo derecho del nodo
     * @param hijoDerecho 
     */
    public void setHijoDerecho(NodoBinario hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    /**
     * Muestra el hijo izquierdo del nodo
     * @return 
     */
    public NodoBinario<K, V> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    /**
     * Permite cambiar el valor del hijo izquierdo del nodo.
     * @param hijoIzquierdo 
     */
    public void setHijoIzquierdo(NodoBinario hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }
    
    /**
     * Valida si el hijo izquierdo esta vacio
     * @return 
     */
    public boolean esVacioHijoIzquierdo(){
        return esVacio(hijoIzquierdo);
    }
    
    /**
     * Valida si el hijo derecho esta vacio
     * @return 
     */
    public boolean esVacioHijoDerecho(){
        return esVacio(hijoDerecho);
    }
    
    /**
     * Valida si el nodo no tiene hijo
     * @return 
     */
    public boolean esHoja(){
        return esVacioHijoDerecho() && esVacioHijoIzquierdo();
    }
    
    /**
     * Valida si el nodo es incompleto (no tiene hijos o solo tiene uno)
     * @return 
     */
    public boolean esIncompleto(){
        return !(!esVacioHijoDerecho() && !esVacioHijoIzquierdo());
    }
    
    /**
     * Muestra una representacion de la clase mediante un string
     * @return 
     */
    @Override
    public String toString() {
        return "(" + clave + ", " + valor + ')';
    }
    
    /**
     * Funcion compartida de la clase nodo que valida si el nodo pasado por 
     * parametros esta vacio.
     * @param nodo
     * @return 
     */
    public static boolean esVacio(NodoBinario nodo){
        return nodo == null;
    }
    
    /**
     * Funcion compartida de la clase nodo que retorna un nodo nulo.
     * @return 
     */
    public static NodoBinario<?, ?> nodoVacio(){
        return null;
    } 
}

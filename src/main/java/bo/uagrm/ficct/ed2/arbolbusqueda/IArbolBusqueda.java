/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda;

import java.util.List;

/**
 * Interfaz que implementa el comportamiento comun entre los arboles
 * @author OJavierHR
 * @param <K> Atributo usado para implementar la clave de los nodos 
 * @param <V> Atributo usado para implementar el valor de la clave
 */
public interface IArbolBusqueda<K extends Comparable<K>, V> {
    void vaciar();
    boolean esVacio();
    void insertar(K clave, V valor);
    V buscar(K clave);
    boolean contiene(K clave);
    List<K> recorridoPorNiveles();
    List<K> recorridoPreOrden();
    List<K> recorridoInOrden();
    List<K> recorridoPostOrden();
    void eliminar(K clave) throws IllegalArgumentException;
    int size();
    int altura();
    int nivel();
}

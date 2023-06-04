/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario.ejercicios;

import bo.uagrm.ficct.ed2.arbolbusqueda.IArbolBusqueda;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaRecursivo;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.NodoBinario;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EjerciciosABBR<K extends Comparable<K>, V>
        extends ArbolBinarioBusquedaRecursivo<K, V> {

    public EjerciciosABBR() {
    }

    /*
        Implementar metodo que cuente las hojas del arbol 
     */
    public int contarHojas() {
        return contarHojasR(getRaiz());
    }

    private int contarHojasR(NodoBinario<K, V> raiz) {
        if (NodoBinario.esVacio(raiz)) {
            return 0;
        } else if (raiz.esHoja()) {
            return 1;
        } else {
            return contarHojasR(raiz.getHijoIzquierdo())
                    + contarHojasR(raiz.getHijoDerecho());
        }
    }
    
    /*
        Implementar metodo que cuente las hojas del arbol en un determinado 
        nivel
    */
    public int contarHojasNivel(int nivel){
        return contarHojasNivel(getRaiz(), nivel);
    }

    private int contarHojasNivel(NodoBinario<K, V> raiz, int nivel) {
        if (NodoBinario.esVacio(raiz) || nivel < 0) {
            return 0;
        }else if(raiz.esHoja() && nivel == 0){
            return 1;
        }else {
            return contarHojasNivel(raiz.getHijoIzquierdo(), nivel-1)
                    + contarHojasNivel(raiz.getHijoDerecho(), nivel-1);
        }
    }
    
    /*
        funcion que retorne la lista de claves de los niveles del arbol menos el
        nivel n
    */
    public List<K> mostrarNodosMenosN(int nivel) {
        return mostrarNodosMenosN(raiz, nivel);
    }
    
    private List<K> mostrarNodosMenosN(NodoBinario<K, V> raiz, int nivel) {
        List<K> result = new LinkedList<>();
        int nivelesArbol = this.nivel();
        for (int i = 0; i <= nivelesArbol; i++) {
            if(i != nivel){
                recorrerNivel(raiz, i, result);
            }
        }
        return result;
    }
    
    private void recorrerNivel(NodoBinario<K, V> raiz, int nivel, List<K> result) {
        if(NodoBinario.esVacio(raiz) || nivel < 0){
            return;
        }
        if(nivel == 0){
            result.add(raiz.getClave());
        }
        if(!raiz.esVacioHijoIzquierdo()){
            recorrerNivel(raiz.getHijoIzquierdo(), nivel-1, result);
        }
        if(!raiz.esVacioHijoDerecho()){
            recorrerNivel(raiz.getHijoDerecho(), nivel-1, result);
        }
        
    }
    
    public static void main(String[] arg){
        EjerciciosABBR<Integer, String> aBBR = new EjerciciosABBR<>();
        
        aBBR.insertar(5, "cinco");
        aBBR.insertar(4, "cuatro");
        aBBR.insertar(6, "seis");
        aBBR.insertar(3, "tres");
        aBBR.insertar(2, "dos");
        aBBR.insertar(7, "ciete");
        aBBR.insertar(10, "diez");
        aBBR.insertar(1, "uno");
        aBBR.insertar(8, "ocho");
        aBBR.insertar(9, "nueve");
        
        System.out.println(aBBR.recorridoPorNiveles());
        System.out.println(aBBR.mostrarNodosMenosN(3));
    }
}

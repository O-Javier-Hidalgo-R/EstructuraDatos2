/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

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
}

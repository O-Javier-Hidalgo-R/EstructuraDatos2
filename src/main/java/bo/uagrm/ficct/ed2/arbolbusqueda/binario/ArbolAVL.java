/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

/**
 * 
 * @author OJavierHR
 * @param <K>
 * @param <V> 
 */
public class ArbolAVL<K extends Comparable<K>, V> extends ArbolBinarioBusquedaRecursivo<K, V> {
    @Override
    public void insertar(K clave, V valor){
        setRaiz(insertar(getRaiz(), clave, valor));
    }

    private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual, 
            K claveAInsertar, V valorAInsertar) {
        if (NodoBinario.esVacio(nodoActual)) {
            return new NodoBinario<>(claveAInsertar, valorAInsertar);
        } else if (claveAInsertar.compareTo(nodoActual.getClave()) == 0) {
            nodoActual.setValor(valorAInsertar);
            return nodoActual;
        } else {
            if (claveAInsertar.compareTo(nodoActual.getClave()) < 0) {
                nodoActual.setHijoIzquierdo(insertar(
                        nodoActual.getHijoIzquierdo(), claveAInsertar, 
                        valorAInsertar));
            } else {
                nodoActual.setHijoDerecho(insertar(
                        nodoActual.getHijoDerecho(), 
                        claveAInsertar, valorAInsertar));
            }
            return balancear(nodoActual);
        }
    }
    
    @Override
    public V eliminar(K clave) {
        NodoBinario<K, V> nodoEliminado = new NodoBinario<>();
        setRaiz(eliminar(getRaiz(), clave, nodoEliminado));
        return nodoEliminado.getValor();
    }
    
    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K clave, NodoBinario<K, V> nodoEliminado) {
        if (!NodoBinario.esVacio(nodoActual)) {
            if (clave.compareTo(nodoActual.getClave()) == 0) {
                nodoEliminado = nodoActual;
                if (nodoActual.esIncompleto()) {
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        return nodoActual.getHijoIzquierdo();
                    } else {
                        return nodoActual.getHijoDerecho();
                    }
                } else {
                    NodoBinario<K, V> nodoRemplazo = nodoMenor(nodoActual.getHijoDerecho());
                    nodoActual.setClave(nodoRemplazo.getClave());
                    nodoActual.setValor(nodoRemplazo.getValor());
                    nodoActual.setHijoDerecho(eliminar(nodoActual.getHijoDerecho(), nodoRemplazo.getClave(), nodoEliminado));
                }
            } else if (clave.compareTo(nodoActual.getClave()) < 0) {
                nodoActual.setHijoIzquierdo(eliminar(nodoActual.getHijoIzquierdo(), clave, nodoEliminado));
            } else {
                nodoActual.setHijoDerecho(eliminar(nodoActual.getHijoDerecho(), clave, nodoEliminado));
            }
        }
        return balancear(nodoActual);
    }

    private NodoBinario<K, V> balancear(NodoBinario<K, V> nodoActual) {
        if (!NodoBinario.esVacio(nodoActual)) {
            int alturaIzq = nivelR(nodoActual.getHijoIzquierdo());
            int alturaDer = nivelR(nodoActual.getHijoDerecho());
            int diferencia = alturaIzq - alturaDer;
            if ((diferencia > LIM_SUP)) {
                NodoBinario<K, V> hijoIzquierdo = nodoActual.getHijoIzquierdo();
                alturaIzq = nivelR(hijoIzquierdo.getHijoIzquierdo());
                alturaDer = nivelR(hijoIzquierdo.getHijoDerecho());
                if (alturaDer > alturaIzq) {
                    return rotacionDobleDerecha(nodoActual);
                } else {
                    return rotacionSimpleDerecha(nodoActual);
                }
            } else if (diferencia < LIM_INF) {
                NodoBinario<K, V> hijoDerecho = nodoActual.getHijoDerecho();
                alturaIzq = nivelR(hijoDerecho.getHijoIzquierdo());
                alturaDer = nivelR(hijoDerecho.getHijoDerecho());
                if (alturaIzq > alturaDer) {
                    return rotacionDobleIzquierda(nodoActual);
                } else {
                    return rotacionSimpleizquierda(nodoActual);
                }
            }
        }
        return nodoActual;
    }
    
    private static final int LIM_SUP = 1;
    private static final int LIM_INF = -1;

    private NodoBinario<K, V> rotacionDobleDerecha(NodoBinario<K, V> nodoActual) {
        nodoActual.setHijoIzquierdo(rotacionSimpleizquierda(nodoActual.getHijoIzquierdo()));
        return rotacionSimpleDerecha(nodoActual);
    }
    
    private NodoBinario<K, V> rotacionDobleIzquierda(NodoBinario<K, V> nodoActual) {
        nodoActual.setHijoDerecho(rotacionDobleDerecha(nodoActual.getHijoDerecho()));
        return rotacionSimpleizquierda(nodoActual);
    }

    private NodoBinario<K, V> rotacionSimpleDerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario rotacionSimpleizquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoActual);
        return nodoQueRota;
    }
}

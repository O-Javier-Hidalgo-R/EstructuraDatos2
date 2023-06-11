/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.mvias;

import java.util.Stack;

public class ArbolB<K extends Comparable<K>, V> extends ArbolMVias<K, V> {

    private final int NRO_MINIMO_HIJOS;
    private final int NRO_MINIMO_DATOS;
    private final int NRO_MAXIMO_HIJOS;
    private final int NRO_MAXIMO_DATOS;
    private final int POSICION_INVALIDA = -1;

    public ArbolB(int orden) {
        super(orden + 1);
        this.NRO_MAXIMO_HIJOS = orden;
        this.NRO_MAXIMO_DATOS = orden - 1;
        this.NRO_MINIMO_DATOS = NRO_MAXIMO_DATOS / 2;
        this.NRO_MINIMO_HIJOS = NRO_MINIMO_DATOS + 1;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (super.esVacio()) {
            super.raiz = new NodoNVias<>(super.orden, claveAInsertar,
                    valorAInsertar);
            return;
        }

        Stack<NodoNVias<K, V>> pilaDeAncestros = new Stack<>();
        NodoNVias<K, V> nodoActual = this.raiz;
        while (!NodoNVias.esVacio(nodoActual)) {
            int posicionClaveExistente = existeClaveEnNodo(nodoActual,
                    claveAInsertar);
            if (posicionClaveExistente != POSICION_INVALIDA) {
                nodoActual.setValor(posicionClaveExistente, valorAInsertar);
                nodoActual = NodoNVias.nodoVacio();
            } else {
                if (nodoActual.esHoja()) {
                    super.colocarDatoOrdenadamente(nodoActual,
                            claveAInsertar, valorAInsertar);
                    if (nodoActual.cantidadDeClavesNoVacias()
                            > this.NRO_MAXIMO_DATOS) {
                        this.dividir(nodoActual, pilaDeAncestros);
                    }
                    nodoActual = NodoNVias.nodoVacio();
                } else {
                    int posParaBajar = super.busPosBajar(nodoActual, claveAInsertar);
                    pilaDeAncestros.push(nodoActual);
                    nodoActual = nodoActual.getHijo(posParaBajar);
                }
            }
        }
    }

    private int existeClaveEnNodo(NodoNVias<K, V> nodoActual, K claveAInsertar) {
        int result = POSICION_INVALIDA;
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (claveAInsertar.compareTo(nodoActual.getClave(i)) == 0) {
                result = i;
            }
        }
        return result;
    }

    private void dividir(NodoNVias<K, V> nodoActual,
            Stack<NodoNVias<K, V>> pilaDeAncestros) {
        final int POS_MEDIANA = NRO_MAXIMO_DATOS / 2;
        NodoNVias<K, V> nodoParteDerecha = new NodoNVias<>(super.orden);
        int j = 0;
        int i = 0;
        K claveMedio = nodoActual.getClave(POS_MEDIANA);
        V valorMedio = nodoActual.getValor(POS_MEDIANA);

        //Copiamos los valores del nodo actual al que sera la parte derecha
        for (i = POS_MEDIANA + 1; i < NRO_MAXIMO_HIJOS; i++) {
            nodoParteDerecha.setClave(j,
                    nodoActual.getClave(i));
            nodoParteDerecha.setValor(j,
                    nodoActual.getValor(i));
            nodoParteDerecha.setHijo(j,
                    nodoActual.getHijo(i));
            j++;
        }
        nodoParteDerecha.setHijo(j,
                nodoActual.getHijo(i));

        //Eliminamos las referencia del nodo actual que ya no sirven para 
        //usarlo como el nodo izquierdo
        for (i = POS_MEDIANA; i < NRO_MAXIMO_HIJOS; i++) {
            nodoActual.setClave(i, (K) NodoNVias.datoVacio());
            nodoActual.setValor(i, (V) NodoNVias.datoVacio());
            nodoActual.setHijo(i+1, NodoNVias.nodoVacio());
        }

        if (nodoActual == super.raiz) {
            NodoNVias<K, V> nodoNuevo = new NodoNVias<>(super.orden);
            //Colocamos los valores que llevara el nodo nuevo;
            nodoNuevo.setClave(0, claveMedio);
            nodoNuevo.setValor(0, valorMedio);
            nodoNuevo.setHijo(0, nodoActual);
            nodoNuevo.setHijo(1, nodoParteDerecha);

            //La raiz ahora es el nodo nuevo
            super.raiz = nodoNuevo;
        }else{
            NodoNVias<K, V> nodoPadre = pilaDeAncestros.pop();
            insertarDatoNodoOrdenadamente(nodoPadre, claveMedio, valorMedio, nodoParteDerecha);
            if(nodoPadre.cantidadDeClavesNoVacias() > NRO_MAXIMO_DATOS){
                dividir(nodoPadre, pilaDeAncestros);
            }
        }
    }
    
    private void insertarDatoNodoOrdenadamente(NodoNVias<K, V> nodoNVias,
            K clave, V valor, NodoNVias<K, V> HijoDerecho) {
        int i = 0;
        //identidico donde voy a insertar el nodo en el nodo (posicion)
        while (nodoNVias.getClave(i) != (K) NodoNVias.datoVacio()
                && clave.compareTo(nodoNVias.getClave(i)) > 0) {
            i++;
        }
        //Empujo los valores y hijos hacia adelante 
        int l = nodoNVias.cantidadDeClavesNoVacias()-1;
        for (int j = l; j >= i; j--) {
            nodoNVias.setClave(j + 1, nodoNVias.getClave(j));
            nodoNVias.setClave(j + 1, nodoNVias.getClave(j));
        }
        for (int j = l+1; j >= i + 1; j--) {
            nodoNVias.setHijo(j + 1, nodoNVias.getHijo(j));
        }

        //al fin inserto los valores
        nodoNVias.setClave(i, clave);
        nodoNVias.setValor(i, valor);
        nodoNVias.setHijo(i + 1, HijoDerecho);
    }
    
    /*
    //Insertar recursivo (incompleto)
    @Override
    public void insertar(K claveAinsertar, V valorAInsertar) {
        if (super.esVacio()) {
            super.raiz = new NodoNVias(super.orden, claveAinsertar,
                    valorAInsertar);
            return;
        }
        insertar(super.raiz, claveAinsertar, valorAInsertar);
    }

    private NodoNVias<K, V> insertar(NodoNVias<K, V> nodoActual, K claveAinsertar,
            V valorAInsertar) {
        if (nodoActual.esHoja()) {
            insertarDatoNodoOrdenadamente(nodoActual, claveAinsertar,
                    valorAInsertar, NodoNVias.nodoVacio());
        } else {
            int posBajar = super.busPosBajar(nodoActual, claveAinsertar);
            NodoNVias<K, V> nodoNuevo = insertar(nodoActual.getHijo(posBajar),
                    claveAinsertar, valorAInsertar);
            if (nodoNuevo != NodoNVias.nodoVacio()) {
                K clavePivote = nodoNuevo.getClave(0);
                V valorPivote = nodoNuevo.getValor(0);
                insertarDatoNodoOrdenadamente(nodoNuevo, clavePivote, valorPivote, nodoNuevo);
                for (int i = 0; i < nodoNuevo.cantidadDeClavesNoVacias() - 1; i++) {
                    nodoNuevo.setClave(i, nodoNuevo.getClave(i + 1));
                    nodoNuevo.setValor(i, nodoNuevo.getValor(i + 1));
                }
                nodoNuevo.setClave(nodoNuevo.cantidadDeClavesNoVacias() - 1,
                        (K) NodoNVias.datoVacio());
                nodoNuevo.setValor(nodoNuevo.cantidadDeClavesNoVacias() - 1,
                        (V) NodoNVias.datoVacio());
            }
        }
        if (nodoActual.cantidadDeClavesNoVacias() > NRO_MAXIMO_DATOS) {
            return dividir(nodoActual);
        } else {
            return NodoNVias.nodoVacio();
        }
    }

    private void insertarDatoNodoOrdenadamente(NodoNVias<K, V> nodoNVias,
            K clave, V valor, NodoNVias<K, V> HijoDerecho) {
        int i = 0;
        //identidico donde voy a insertar el nodo en el nodo (posicion)
        while (nodoNVias.getClave(i) != (K) NodoNVias.datoVacio()
                && clave.compareTo(nodoNVias.getClave(i)) > 0) {
            i++;
        }
        //Empujo los valores y hijos hacia adelante 
        int l = nodoNVias.cantidadDeClavesNoVacias()-1;
        for (int j = l; j >= i; j--) {
            nodoNVias.setClave(j + 1, nodoNVias.getClave(j));
            nodoNVias.setClave(j + 1, nodoNVias.getClave(j));
        }
        for (int j = l+1; j >= i + 1; j--) {
            nodoNVias.setHijo(j + 1, nodoNVias.getHijo(j));
        }

        //al fin inserto los valores
        nodoNVias.setClave(i, clave);
        nodoNVias.setValor(i, valor);
        nodoNVias.setHijo(i + 1, HijoDerecho);
    }

    private NodoNVias<K, V> dividir(NodoNVias<K, V> nodoActual) {
        if (nodoActual == super.raiz) {
            final int DATO_DEL_MEDIO = (super.orden - 2) / 2;
            NodoNVias<K, V> nodoNuevo = new NodoNVias<>(super.orden);
            NodoNVias<K, V> nodoParteDerecha
                    = new NodoNVias<>(super.orden);
            int j = 0;
            int i;
            int l = nodoActual.cantidadDeClavesNoVacias() - 1;

            //Copiamos los valores del nodo actual al que sera la parte derecha
            for (i = DATO_DEL_MEDIO + 1; i <= l; i++) {
                nodoParteDerecha.setClave(j,
                        nodoActual.getClave(i));
                nodoParteDerecha.setValor(j,
                        nodoActual.getValor(i));
                nodoParteDerecha.setHijo(j,
                        nodoNuevo.getHijo(i));
                j++;
            }
            nodoParteDerecha.setHijo(j+1,
                    nodoActual.getHijo(i));

            //Colocamos los valores que llevara el nodo nuevo;
            nodoNuevo.setClave(0,
                    nodoActual.getClave(DATO_DEL_MEDIO));
            nodoNuevo.setValor(0,
                    nodoActual.getValor(DATO_DEL_MEDIO));
            nodoNuevo.setHijo(0, nodoActual);
            nodoNuevo.setHijo(1, nodoParteDerecha);

            //Eliminamos las referencia del nodo actual que ya no sirven para 
            //usarlo como el nodo izquierdo
            for (i = DATO_DEL_MEDIO; i <= l; i++) {
                nodoActual.setClave(i, (K) NodoNVias.datoVacio());
                nodoActual.setValor(i, (V) NodoNVias.datoVacio());
                nodoActual.setHijo(i, NodoNVias.nodoVacio());
            }
            nodoActual.setHijo(l+1, NodoNVias.nodoVacio());

            //La raiz ahora es el nodo nuevo
            super.raiz = nodoNuevo;
            return NodoNVias.nodoVacio();
        }
        NodoNVias<K, V> nodoNuevo = new NodoNVias<>(super.orden);
        int j = 0;
        int f = nodoActual.cantidadDeClavesNoVacias();
        for (int i = (f - 1) / 2;
                i < f;
                i++) {
            nodoNuevo.setClave(j, nodoActual.getClave(i));
            nodoNuevo.setValor(j, nodoActual.getValor(i));
            nodoActual.setClave(i, (K) NodoNVias.datoVacio());
            nodoActual.setValor(i, (V) NodoNVias.datoVacio());
            if (!nodoActual.esHoja()) {
                nodoNuevo.setHijo(j + 1, nodoActual.getHijo(i + 1));
                nodoActual.setHijo(i + 1, NodoNVias.nodoVacio());
            }
            j++;
        }
        return nodoNuevo;
    }

    @Override
    public V eliminar(K clave) throws IllegalArgumentException {
        return super.eliminar(clave); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
     */
}

package bo.uagrm.ficct.ed2.arbolbusqueda;

import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolMVias;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author OJavierHR
 */
public class MainArbolBusquedaBinario {
    public static void main(String[] args) {
        ArbolMVias<Integer, String> mVias = new ArbolMVias<>(4);
        mVias.insertar(80, "");
        mVias.insertar(120, "");
        mVias.insertar(200, "");
        mVias.insertar(50, "");
        mVias.insertar(70, "");
        mVias.insertar(75, "");
        mVias.insertar(98, "");
        mVias.insertar(110, "");
        mVias.insertar(130, "");
        mVias.insertar(140, "");
        mVias.insertar(150, "");
        mVias.insertar(400, "");
        mVias.insertar(500, "");
        mVias.insertar(560, "");
        mVias.insertar(72, "");
        mVias.insertar(134, "");
        mVias.insertar(160, "");
        mVias.insertar(170, "");
        mVias.insertar(190, "");
        
        System.out.println(mVias.recorridoPorNiveles());
        System.out.println(mVias.recorridoPreOrden());
        System.out.println(mVias.recorridoInOrden());
        System.out.println(mVias.recorridoPostOrden());
        System.out.println(mVias.size());
        System.out.println(mVias.nivel());
    }
}

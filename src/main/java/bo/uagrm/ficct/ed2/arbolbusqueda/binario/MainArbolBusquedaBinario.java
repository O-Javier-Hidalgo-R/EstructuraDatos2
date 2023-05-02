/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class MainArbolBusquedaBinario {
    public static void main(String[] args) {
        
        /*
            Arbol binario de busqueda iterativo 
        */
        ArbolBinarioBusquedaIterativo abbi = 
                new ArbolBinarioBusquedaIterativo<>();
        
        /*
            Arbol binario de busqueda recursivo
        */
        ArbolBinarioBusquedaRecursivo abbr = 
                new ArbolBinarioBusquedaRecursivo<>();
        
        System.out.println("Estructura de datos");
        System.out.println("Arbol binario de busqueda (iterativo)");
        System.out.println("-------------------------------------------------"
                + "-----------------------");
        
        /*
                        45
                23              65
            2      38      52        96
             7           48
        */
        
        abbi.insertar(45, "A");
        abbi.insertar(23, "B");
        abbi.insertar(65, "C");
        abbi.insertar(2, "D");
        abbi.insertar(38, "E");
        abbi.insertar(52, "F");
        abbi.insertar(96, "G");
        abbi.insertar(7, "H");
        abbi.insertar(48, "I");
        
        System.out.println(abbi.size());
        System.out.println(abbi.altura());
        System.out.println(abbi.nivel());
        System.out.println(abbi.recorridoPorNiveles());
        System.out.println(abbi.recorridoPreOrden());
        System.out.println(abbi.recorridoInOrden());
        System.out.println(abbi.recorridoPostOrden());
        
        /*
            Insertar operaciones y cambios aqui...
        */
        System.out.println(abbi.buscar(45));
        System.out.println(abbi.buscar(2));
        System.out.println(abbi.buscar(10));
        System.out.println(abbi.buscar(48));
        
        abbi.eliminar(45);
        abbi.eliminar(23);
        abbi.eliminar(2);
        abbi.eliminar(7);

        
        System.out.println(abbi.recorridoPorNiveles());
        System.out.println(abbi.recorridoPreOrden());
        System.out.println(abbi.recorridoInOrden());
        System.out.println(abbi.recorridoPostOrden());
        
        System.out.println();
        System.out.println("Estructura de datos");
        System.out.println("Arbol binario de busqueda (recursivo)");
        System.out.println("-------------------------------------------------"
                + "-----------------------");
        
        /*
                        45
                23              65
            2      38      52        96
             7           48
        */
        
        /*abbr.insertar(45, "A");
        abbr.insertar(23, "B");
        abbr.insertar(65, "C");
        abbr.insertar(2, "D");
        abbr.insertar(38, "E");
        abbr.insertar(52, "F");
        abbr.insertar(96, "G");
        abbr.insertar(7, "H");
        abbr.insertar(48, "I");*/
        
        List<Integer> clavesInOrden = new LinkedList<>();
        List<String> valoresInOrden = new LinkedList<>();
        List<Integer> clavesPreOrden = new LinkedList<>();
        List<String> valoresPreOrden = new LinkedList<>();
        List<Integer> clavesPostOrden = new LinkedList<>();
        List<String> valoresPostOrden = new LinkedList<>();
        
        clavesInOrden.add(2);
        clavesInOrden.add(7);
        clavesInOrden.add(23);
        clavesInOrden.add(38);
        clavesInOrden.add(45);
        clavesInOrden.add(48);
        clavesInOrden.add(52);
        clavesInOrden.add(65);
        clavesInOrden.add(96);
        
        valoresInOrden.add("D");
        valoresInOrden.add("H");
        valoresInOrden.add("B");
        valoresInOrden.add("E");
        valoresInOrden.add("A");
        valoresInOrden.add("I");
        valoresInOrden.add("F");
        valoresInOrden.add("C");
        valoresInOrden.add("G");
        
        clavesPreOrden.add(45);
        clavesPreOrden.add(23);
        clavesPreOrden.add(2);
        clavesPreOrden.add(7);
        clavesPreOrden.add(38);
        clavesPreOrden.add(65);
        clavesPreOrden.add(52);
        clavesPreOrden.add(48);
        clavesPreOrden.add(96);
        
        valoresPreOrden.add("A");
        valoresPreOrden.add("B");
        valoresPreOrden.add("D");
        valoresPreOrden.add("H");
        valoresPreOrden.add("E");
        valoresPreOrden.add("C");
        valoresPreOrden.add("F");
        valoresPreOrden.add("I");
        valoresPreOrden.add("G");
        
        clavesPostOrden.add(7);
        clavesPostOrden.add(2);
        clavesPostOrden.add(38);
        clavesPostOrden.add(23);
        clavesPostOrden.add(48);
        clavesPostOrden.add(52);
        clavesPostOrden.add(96);
        clavesPostOrden.add(65);
        clavesPostOrden.add(45);
        
        valoresPostOrden.add("H");
        valoresPostOrden.add("D");
        valoresPostOrden.add("E");
        valoresPostOrden.add("B");
        valoresPostOrden.add("I");
        valoresPostOrden.add("F");
        valoresPostOrden.add("G");
        valoresPostOrden.add("C");
        valoresPostOrden.add("A");
        
         abbr = new ArbolBinarioBusquedaRecursivo<>(clavesInOrden, valoresInOrden, clavesPostOrden, valoresPostOrden, false);
        
        System.out.println(abbr.size());
        System.out.println(abbr.altura());
        System.out.println(abbr.nivel());
        System.out.println(abbr.recorridoPorNiveles());
        System.out.println(abbr.recorridoPreOrden());
        System.out.println(abbr.recorridoInOrden());
        System.out.println(abbr.recorridoPostOrden());
        
        /*
            Insertar operaciones y cambios aqui...
        */
        System.out.println(abbr.buscar(45));
        System.out.println(abbr.buscar(2));
        System.out.println(abbr.buscar(10));
        System.out.println(abbr.buscar(48));
        
        abbr.eliminar(45);
        abbr.eliminar(23);
        abbr.eliminar(2);
        abbr.eliminar(7);
        
        System.out.println(abbr.recorridoPorNiveles());
        System.out.println(abbr.recorridoPreOrden());
        System.out.println(abbr.recorridoInOrden());
        System.out.println(abbr.recorridoPostOrden());
        
        EjerciciosABBR aBBR = new EjerciciosABBR();
        EjerciciosABBI aBBI = new EjerciciosABBI();
        
        aBBR.insertar(45, "A");
        aBBR.insertar(23, "B");
        aBBR.insertar(65, "C");
        aBBR.insertar(2, "D");
        aBBR.insertar(38, "E");
        aBBR.insertar(52, "F");
        aBBR.insertar(96, "G");
        aBBR.insertar(7, "H");
        aBBR.insertar(48, "I");
        
        aBBI.insertar(45, "A");
        aBBI.insertar(23, "B");
        aBBI.insertar(65, "C");
        aBBI.insertar(2, "D");
        aBBI.insertar(38, "E");
        aBBI.insertar(52, "F");
        aBBI.insertar(96, "G");
        aBBI.insertar(7, "H");
        aBBI.insertar(48, "I");
        
        System.out.println(aBBI.contarHojas());
        System.out.println(aBBI.contarHojasNivel(2));
    }
}

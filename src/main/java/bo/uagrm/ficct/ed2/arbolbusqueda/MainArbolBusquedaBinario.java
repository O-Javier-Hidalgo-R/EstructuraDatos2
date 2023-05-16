package bo.uagrm.ficct.ed2.arbolbusqueda;



import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolAVL;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaIterativo;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaRecursivo;
import java.util.Scanner;

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
        Scanner entrada = new Scanner(System.in);
        IArbolBusqueda<Integer, String> ab;
        
        System.out.println("Que tipo de arbol quiere probar:");
        System.out.println("Binario Recursivo = br");
        System.out.println("Binario Iterativo = bi");
        System.out.println("AVL = avl");
        
        ab = new ArbolAVL<>();
        ab.insertar(5, "A");
        ab.insertar(2, "B");
        ab.insertar(6, "C");
        ab.insertar(1, "D");
        ab.insertar(3, "E");
        ab.insertar(4, "F");
        
        ab.eliminar(1);
        ab.eliminar(2);
        
        System.out.println("Recorrido por niveles");
        System.out.println(ab.recorridoPorNiveles());
        
        /*switch (entrada.nextLine()) {
        case "br":
        {
        ab = new ArbolBinarioBusquedaRecursivo<>();
        ab.insertar(10, "A");
        ab.insertar(9, "B");
        ab.insertar(8, "C");
        ab.insertar(7, "D");
        ab.insertar(6, "E");
        
        System.out.println("Recorrido por niveles");
        System.out.println(ab.recorridoPorNiveles());
        }
        break;
        case "bi":
        {
        ab = new ArbolBinarioBusquedaIterativo<>();
        }
        break;
        case "avl":
        {
        ab = new ArbolAVL<>();
        ab.insertar(10, "A");
        ab.insertar(9, "B");
        ab.insertar(8, "C");
        ab.insertar(7, "D");
        ab.insertar(6, "E");
        
        System.out.println("Recorrido por niveles");
        System.out.println(ab.recorridoPorNiveles());
        }
        break;
        default:
        {
        
        }
        }*/
    }
}

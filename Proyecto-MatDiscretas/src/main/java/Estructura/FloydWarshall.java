package Estructura;

import Estructura.Grafo.Camino;
import Estructura.Grafo.Vertice;
import javafx.scene.shape.Circle;

/**
 *
 * @author vv
 */
public class FloydWarshall {
    
    private static final long INFINITO = 999999999;
    protected static long[][] matrizAdyacencia;
    protected static Circle[][] matrizRecorrido;


    private static void inicializarMatrices(Grafo grafo){
        int n = grafo.getListaDeClaves().size();
        matrizAdyacencia = new long[n][n];
        matrizRecorrido = new Circle[n][n];

        // Inicializar la matriz de Adyacencia
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizAdyacencia[i][j] = (i == j) ? 0 : INFINITO;
            }
        }

        // Llenar la Matriz de Adyacencia con los pesos de los caminos
        int i = 0;
        for (Circle c1 : grafo.getListaDeClaves()) {
            Vertice v = grafo.listaVertices.get(c1);
            int j = 0;
            
            for (Circle c2 : grafo.getListaDeClaves()) {
                if ( i != j) {
                    
                    for (Camino camino : v.listaCaminos) {
                        if (camino.llegada == grafo.listaVertices.get(c2)) {
                            matrizAdyacencia[i][j] = camino.peso;
                            matrizRecorrido[i][j] = grafo.getListaDeClaves().get(j);//llenar matriz de recorrido
                        }
                    }
                }
                j++;
            }
            i++;
        }

    }
    
    public static void algoritmoFloydWarshall(Grafo grafo){
        inicializarMatrices(grafo);
        int verticesSize = grafo.listaVertices.size();
        long temp1, temp2, temp3, temp4, minimo;
        
        //Realiza la comparacion para encontrar los caminos minimos entre todos
        //los vertices
        for(int k = 0; k < verticesSize; k++ ){
            for(int i = 0; i < verticesSize; i++ ){
                for(int j = 0; j < verticesSize; j++ ){
                    temp1 = matrizAdyacencia[i][j];
                    temp2 = matrizAdyacencia[i][k];
                    temp3 = matrizAdyacencia[k][j];
                    temp4 = temp2 + temp3;
                    
                    minimo = Math.min(temp1, temp4);
                    if( temp1 != temp4 ){
                        if( minimo == temp4 ){
                            matrizRecorrido[i][j] = matrizRecorrido[i][k];
                        }
                    }
                    matrizAdyacencia[i][j] = minimo;
                }
            }
        }
    }
    
    public static void printMatrices(){
        System.out.println("-> Matriz de Adyacencia");
        for (long[] m : matrizAdyacencia) {
            for(long e: m){
                System.out.print(String.valueOf(e) + " - ");
            }
            System.out.println("");
        }
        System.out.println();
        
        System.out.println("-> Matriz de recorrido");
         for (Circle[] m : matrizRecorrido) {
            for(Circle e: m){
                if(e== null) { System.out.print("null" + " - ");}
                else{
                System.out.print(e.getId() + " - ");}
            }
            System.out.println("");
        }
    }

}

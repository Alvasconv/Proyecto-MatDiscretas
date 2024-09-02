package Estructura;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author vv
 */
public class Grafo {

    Map<Circle, Vertice> listaVertices;

    public Grafo() {
        listaVertices = new LinkedHashMap<>();
    }

    protected class Vertice {
        
        public Circle contenido;
        public List<Camino> listaCaminos;

        public Vertice(Circle circulo) {
            circulo.setFill(Color.TRANSPARENT);
            circulo.setStroke(Color.BLACK); //Despues hay que cambiarlo a transparente
            contenido = circulo;
            listaCaminos = new ArrayList<>();
        }
        
        public int getPeso(Circle vLlegada){
            for(Camino c: listaCaminos){
                if(c.partida.equals(this) && c.llegada.contenido.equals(vLlegada)){
                    return c.peso;
                }
            }
            throw new NoSuchElementException("no se encontro el camino.");
        }
    }

    protected class Camino {
        
        public Integer peso; //distancia entre vertices
        public Vertice partida, llegada;

        // Si tenemos acceso al Vertice como objeto
        public Camino(Vertice partida, Vertice llegada, Integer peso) {
            this.peso = peso;
            this.partida = partida;
            this.llegada = llegada;
        }

        public Camino(Circle partida, Circle llegada, Integer peso) {
            this.peso = peso;
            Vertice p = listaVertices.get(partida);
            Vertice l = listaVertices.get(llegada);
            this.partida = p;
            this.llegada = l;
        }

    }

    public Circle addVertice(Circle circulo) {
        
        Vertice vertice = new Vertice(circulo);
        listaVertices.put(circulo, vertice);
        return circulo;
    }

    
    public Integer addCamino(Circle partida, Circle llegada, Integer peso) {
        
        Vertice p = listaVertices.get(partida);
        Vertice l = listaVertices.get(llegada);

        if (p == null || l == null) {
            return null;
        }
        Camino edge = new Camino(p, l, peso);
        p.listaCaminos.add(edge);
        return peso;
    }
    
    //Retorna la lista de los vertices ordenada tal cual fueron agregados al Map
    public List<Circle> getListaDeClaves(){
        
        Set<Circle> claves = listaVertices.keySet();
        List<Circle> listaClaves = new ArrayList<>();
        listaClaves.addAll(claves);
        return listaClaves;
    }
    
    
    public void actualizarMatrices(){
        FloydWarshall.algoritmoFloydWarshall(this);
        FloydWarshall.printMatrices(this);
    }
    
    
    public long pesoCaminoMasCorto(Circle partida, Circle llegada){
        
        int indiceX = this.getListaDeClaves().indexOf(partida);
        int indiceY = this.getListaDeClaves().indexOf(llegada);
        return FloydWarshall.matrizAdyacencia[indiceX][indiceY];
    }
    
    
    public void dibujarCaminoMasCorto(Circle partida, Circle llegada, AnchorPane root){
       
        int indiceX = this.getListaDeClaves().indexOf(partida);
        int indiceY = this.getListaDeClaves().indexOf(llegada);
        Circle temp = FloydWarshall.matrizRecorrido[indiceX][indiceY];

        Line camino = new Line( partida.getLayoutX() , partida.getLayoutY()
                               ,temp.getLayoutX(), temp.getLayoutY() );
        camino.setStrokeWidth(3);
        camino.setStroke(Color.RED);
        root.getChildren().add(camino);
        
        if( !temp.equals(llegada) ){
            dibujarCaminoMasCorto(temp, llegada, root);
        }
    }

    
    @Override
    public String toString() {
        String s = "";
        for (final Vertice v : listaVertices.values()) {
            s += "  " + v.toString() + "\n";
        }
        return "Vertices: {\n" + s + "}";
    }
}
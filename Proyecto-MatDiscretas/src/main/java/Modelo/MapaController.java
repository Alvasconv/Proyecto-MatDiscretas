package Modelo;

import Estructura.Grafo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author vv
 */
public class MapaController implements Initializable {

    @FXML TextField puntoA;
    @FXML TextField puntoB;
    @FXML Button btnCalcular;
    @FXML Button btnClear;
    @FXML Label distanciaRecorrido;
    @FXML AnchorPane mapa;
    
    //Listado de objetos tipo Circle (falta añadir mas)
    @FXML Circle circulo1;
    @FXML Circle circulo2;
    @FXML Circle circulo3;
    @FXML Circle circulo4;
    @FXML Circle circulo5;
    
    Grafo grafo;
    private Circle partida = null, llegada = null;
    private boolean isSelectedPuntoA = false, isSelectedPuntoB = false;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        puntoA.setFocusTraversable(false);//Esto es solo para que no aparezcan
        puntoB.setFocusTraversable(false);//resaltados los textfields al inicio
        grafo = new Grafo();
        
        //Itera cada elemento que esta dentro del AnchorPane y busca los que son tipo Circle
        //Ademas los agrega de forma automatica al grafo (SOLO LOS VERTICES NO LOS CAMINOS)
        for (javafx.scene.Node nodo : mapa.lookupAll("*")) {
            if (nodo instanceof Circle) {
                addEventosVertices((Circle) nodo);//Le agrega los EventHandler
            }
        }
        
        agregarCaminosGrafo(); //Hay que añadirle todos los caminos entre los grafos manualmente.
        dibujar();
        escogerPunto();
        limpiarTodo();
    }
    
    private void dibujar(){
        btnCalcular.setOnAction((ActionEvent e)-> {
            if( partida!=null && llegada!=null ){
                try{
                grafo.actualizarMatrices();
                grafo.dibujarCaminoMasCorto(partida, llegada, mapa);
                distanciaRecorrido.setText(" " + grafo.pesoCaminoMasCorto(partida, llegada) + "km" );
                }
                //La excepcion salta solo cuando no hay forma de llegar de un punto al otro
                catch(Exception ex){ 
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Fallo al encontrar un camino");
                    alert.setContentText("No existe una forma de poder llegar desde "
                                        +partida.getId() + " hasta " + llegada.getId()
                                        + "\n Intente probando otras rutas.");
                    alert.showAndWait(); 
                }
            }
        });
    }

    
    private void escogerPunto(){
        puntoA.setOnMouseClicked((MouseEvent e)->{
            isSelectedPuntoA = true;
            isSelectedPuntoB = false;
        });
        
        puntoB.setOnMouseClicked((MouseEvent e)->{
            isSelectedPuntoB = true;
            isSelectedPuntoA = false;
        });
    }
    
    private void limpiarTodo(){
        btnClear.setOnAction((ActionEvent e)-> {
            isSelectedPuntoA = false;
            isSelectedPuntoB = false;
            distanciaRecorrido.setText("");
            puntoA.setText("");
            puntoB.setText("");
            
            if(partida != null){
                partida.setFill(Color.TRANSPARENT);
                partida.setStroke(Color.TRANSPARENT);
                partida = null;
            }
            
            if(llegada != null){
                llegada.setFill(Color.TRANSPARENT);
                llegada.setStroke(Color.TRANSPARENT);
                llegada = null;
            }
            
            //Elimina todas las lineas creadas sobre el mapa
            for (javafx.scene.Node nodo : mapa.lookupAll("*")) {
                if (nodo instanceof Line) {
                    mapa.getChildren().remove((Line)nodo);
                }
            }
        });
    }
    
    public void addEventosVertices(Circle circulo) {
        circulo.setOnMouseClicked((MouseEvent e)-> seleccionar(circulo) );
        grafo.addVertice(circulo);
    }
    
    private void seleccionar(Circle punto){
        //Seleccion del punto A (partida)
        if( isSelectedPuntoA && !isSelectedPuntoB ){

            if( partida != null ){
                partida.setFill(Color.TRANSPARENT);
                partida.setStroke(Color.TRANSPARENT);
            }
            punto.setFill(Color.RED);
            punto.setStroke(Color.BLACK);
            partida = punto;
            puntoA.setText(punto.getId());
        }
        //Seleccion del punto B (llegada)
        if( isSelectedPuntoB && !isSelectedPuntoA ){

            if( llegada != null ){
                llegada.setFill(Color.TRANSPARENT);
                partida.setStroke(Color.TRANSPARENT);
            }
            punto.setFill(Color.RED);
            punto.setStroke(Color.BLACK);
            llegada = punto;
            puntoB.setText(punto.getId());
        }   
    }

    //Aqui se deben de ir agregando todos los caminos manuamente
    private void agregarCaminosGrafo(){
        
        grafo.addCamino(circulo1, circulo2, 10);
        grafo.addCamino(circulo1, circulo5, 15);
        grafo.addCamino(circulo2, circulo3, 10);
        grafo.addCamino(circulo3, circulo4, 20);
        grafo.addCamino(circulo4, circulo5, 5);
    }
}

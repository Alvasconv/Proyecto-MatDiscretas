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
    
    // Listado de objetos tipo Círculo (Callejones)

    @FXML Circle callejon1;
    @FXML Circle callejon2;
    @FXML Circle callejon3;
    @FXML Circle callejon4;
    @FXML Circle callejon5;
    @FXML Circle callejon6;
    @FXML Circle callejon7;
    @FXML Circle callejon8;
    @FXML Circle callejon9;
    @FXML Circle callejon10;
    @FXML Circle callejon11;
    @FXML Circle callejon12;
    @FXML Circle callejon13;
    @FXML Circle callejon14;
    @FXML Circle callejon15;
    @FXML Circle callejon16;
    @FXML Circle callejon17;
    @FXML Circle callejon18;
    @FXML Circle callejon19;
    @FXML Circle callejon20;
    @FXML Circle callejon21;
    @FXML Circle callejon22;
    @FXML Circle callejon23;

    // Listado de objetos tipo Círculo (Intersecciones)

    @FXML Circle circulo1;
    @FXML Circle circulo2;
    @FXML Circle circulo3;
    @FXML Circle circulo4;
    @FXML Circle circulo5;
    @FXML Circle circulo6;
    @FXML Circle circulo7;
    @FXML Circle circulo8;
    @FXML Circle circulo9;
    @FXML Circle circulo10;
    @FXML Circle circulo11;
    @FXML Circle circulo12;
    @FXML Circle circulo13;
    @FXML Circle circulo14;
    @FXML Circle circulo15;
    @FXML Circle circulo16;
    @FXML Circle circulo17;
    @FXML Circle circulo18;
    @FXML Circle circulo19;
    @FXML Circle circulo20;
    @FXML Circle circulo21;
    @FXML Circle circulo22;
    @FXML Circle circulo23;
    @FXML Circle circulo24;
    @FXML Circle circulo25;
    @FXML Circle circulo26;
    @FXML Circle circulo27;
    @FXML Circle circulo28;
    @FXML Circle circulo29;
    @FXML Circle circulo30;
    @FXML Circle circulo31;
    @FXML Circle circulo32;
    @FXML Circle circulo33;
    @FXML Circle circulo34;
    @FXML Circle circulo35;
    @FXML Circle circulo36;
    @FXML Circle circulo37;
    @FXML Circle circulo38;
    @FXML Circle circulo39;
    @FXML Circle circulo40;
    @FXML Circle circulo41;
    @FXML Circle circulo42;
    @FXML Circle circulo43;
    @FXML Circle circulo44;
    @FXML Circle circulo45;
    @FXML Circle circulo46;
    @FXML Circle circulo47;
    @FXML Circle circulo48;
    @FXML Circle circulo49;
    @FXML Circle circulo50;
    @FXML Circle circulo51;
    
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
                llegada.setStroke(Color.TRANSPARENT);
            }
            punto.setFill(Color.RED);
            punto.setStroke(Color.BLACK);
            llegada = punto;
            puntoB.setText(punto.getId());
        }   
    }

    //Aqui se deben de ir agregando todos los caminos manuamente
    private void agregarCaminosGrafo(){

        // Añadir caminos de los callejones

        grafo.addCamino(circulo13, callejon1, 10); // Callejón 1 IDA
        grafo.addCamino(callejon1, circulo13, 10); // Callejón 1 VUELTA
        grafo.addCamino(circulo14, callejon2, 10); // Callejón 2 IDA
        grafo.addCamino(callejon2, circulo14, 10); // Callejón 2 VUELTA
        grafo.addCamino(circulo17, callejon3, 10); // Callejón 3 IDA
        grafo.addCamino(callejon3, circulo17, 10); // Callejón 3 VUELTA
        grafo.addCamino(circulo16, callejon4, 10); // Callejón 4 IDA
        grafo.addCamino(callejon4, circulo16, 10); // Callejón 4 VUELTA
        grafo.addCamino(circulo19, callejon5, 10); // Callejón 5 IDA
        grafo.addCamino(callejon5, circulo19, 10); // Callejón 5 VUELTA
        grafo.addCamino(circulo20, callejon6, 10); // Callejón 6 IDA
        grafo.addCamino(callejon6, circulo20, 10); // Callejón 6 VUELTA
        grafo.addCamino(circulo25, callejon7, 10); // Callejón 7 IDA
        grafo.addCamino(callejon7, circulo25, 10); // Callejón 7 VUELTA
        grafo.addCamino(circulo20, callejon8, 10); // Callejón 8 IDA
        grafo.addCamino(callejon8, circulo20, 10); // Callejón 8 VUELTA
        grafo.addCamino(circulo21, callejon9, 10); // Callejón 9 IDA
        grafo.addCamino(callejon9, circulo21, 10); // Callejón 9 VUELTA
        grafo.addCamino(circulo29, callejon10, 10); // Callejón 10 IDA
        grafo.addCamino(callejon10, circulo29, 10); // Callejón 10 VUELTA
        grafo.addCamino(circulo26, callejon11, 10); // Callejón 11 IDA
        grafo.addCamino(callejon11, circulo26, 10); // Callejón 11 VUELTA
        grafo.addCamino(circulo27, callejon12, 10); // Callejón 12 IDA
        grafo.addCamino(callejon12, circulo27, 10); // Callejón 12 VUELTA
        grafo.addCamino(circulo28, callejon13, 10); // Callejón 13 IDA
        grafo.addCamino(callejon13, circulo28, 10); // Callejón 13 VUELTA
        grafo.addCamino(circulo29, callejon14, 10); // Callejón 14 IDA
        grafo.addCamino(callejon14, circulo29, 10); // Callejón 14 VUELTA
        grafo.addCamino(circulo30, callejon15, 10); // Callejón 15 IDA
        grafo.addCamino(callejon15, circulo30, 10); // Callejón 15 VUELTA
        grafo.addCamino(circulo35, callejon16, 10); // Callejón 16 IDA
        grafo.addCamino(callejon16, circulo35, 10); // Callejón 16 VUELTA
        grafo.addCamino(circulo43, callejon17, 10); // Callejón 17 IDA
        grafo.addCamino(callejon17, circulo43, 10); // Callejón 17 VUELTA
        grafo.addCamino(circulo48, callejon18, 10); // Callejón 18 IDA
        grafo.addCamino(callejon18, circulo48, 10); // Callejón 18 VUELTA
        grafo.addCamino(circulo49, callejon19, 10); // Callejón 19 IDA
        grafo.addCamino(callejon19, circulo49, 10); // Callejón 19 VUELTA
        grafo.addCamino(circulo49, callejon20, 10); // Callejón 20 IDA
        grafo.addCamino(callejon20, circulo49, 10); // Callejón 20 VUELTA
        grafo.addCamino(circulo50, callejon21, 10); // Callejón 21 IDA
        grafo.addCamino(callejon21, circulo50, 10); // Callejón 21 VUELTA
        grafo.addCamino(circulo46, callejon22, 10); // Callejón 22 IDA
        grafo.addCamino(callejon22, circulo46, 10); // Callejón 22 VUELTA
        grafo.addCamino(circulo47, callejon23, 10); // Callejón 23 IDA
        grafo.addCamino(callejon23, circulo47, 10); // Callejón 23 VUELTA

        // Añadir camino de las intersecciones
        
        grafo.addCamino(circulo1, circulo2, 10); 
        grafo.addCamino(circulo1, circulo6, 15);
        grafo.addCamino(circulo2, circulo3, 10);
        grafo.addCamino(circulo20, circulo19, 10);
        grafo.addCamino(circulo3, circulo4, 20);
        grafo.addCamino(circulo4, circulo5, 5); 
        grafo.addCamino(circulo5, circulo2, 10); 
        grafo.addCamino(circulo5, circulo6, 5); 
        grafo.addCamino(circulo4, circulo9, 10);
        grafo.addCamino(circulo8, circulo9, 15);
        grafo.addCamino(circulo8, circulo5, 10); 
        grafo.addCamino(circulo7, circulo8, 20); 
        grafo.addCamino(circulo6, circulo7, 10); 
        grafo.addCamino(circulo7, circulo10, 10);
        grafo.addCamino(circulo11, circulo8, 15);
        grafo.addCamino(circulo9, circulo12, 5);
        grafo.addCamino(circulo12, circulo11, 10);
        grafo.addCamino(circulo11, circulo10, 20);
        grafo.addCamino(circulo10, circulo13, 15);
        grafo.addCamino(circulo14, circulo11, 10);
        grafo.addCamino(circulo15, circulo12, 10);
        grafo.addCamino(circulo13, circulo14, 10);
        grafo.addCamino(circulo14, circulo15, 10);
        grafo.addCamino(circulo18, circulo3, 10);
        grafo.addCamino(circulo2, circulo17, 10);
        grafo.addCamino(circulo16, circulo1, 10);
        grafo.addCamino(circulo18, circulo17, 5);
        grafo.addCamino(circulo17, circulo16, 10);
        grafo.addCamino(circulo19, circulo18, 10);
        grafo.addCamino(circulo25, circulo24, 10);
        grafo.addCamino(circulo24, circulo23, 20);
        grafo.addCamino(circulo23, circulo22, 15);
        grafo.addCamino(circulo22, circulo21, 10);
        grafo.addCamino(circulo21, circulo18, 15);
        grafo.addCamino(circulo21, circulo20, 10);
        grafo.addCamino(circulo3, circulo22, 10);
        grafo.addCamino(circulo23, circulo4, 10);
        grafo.addCamino(circulo9, circulo24, 20);
        grafo.addCamino(circulo25, circulo12, 10);
        grafo.addCamino(circulo26, circulo27, 20);
        grafo.addCamino(circulo27, circulo28, 5);
        grafo.addCamino(circulo28, circulo29, 10);
        grafo.addCamino(circulo22, circulo26, 15);
        grafo.addCamino(circulo27, circulo23, 10);
        grafo.addCamino(circulo24, circulo28, 15);
        grafo.addCamino(circulo29, circulo25, 15);
        grafo.addCamino(circulo35, circulo34, 20);
        grafo.addCamino(circulo34, circulo33, 20);
        grafo.addCamino(circulo34, circulo40, 20);
        grafo.addCamino(circulo33, circulo32, 20);
        grafo.addCamino(circulo32, circulo31, 5);
        grafo.addCamino(circulo31, circulo30, 15);
        grafo.addCamino(circulo16, circulo30, 5);
        grafo.addCamino(circulo31, circulo1, 15);
        grafo.addCamino(circulo6, circulo32, 15);
        grafo.addCamino(circulo33, circulo7, 5);
        grafo.addCamino(circulo10, circulo34, 10);
        grafo.addCamino(circulo35, circulo13, 10);
        grafo.addCamino(circulo30, circulo36, 10);
        grafo.addCamino(circulo36, circulo37, 15);
        grafo.addCamino(circulo37, circulo38, 5);
        grafo.addCamino(circulo38, circulo39, 15);
        grafo.addCamino(circulo39, circulo40, 15);
        grafo.addCamino(circulo40, circulo41, 10);
        grafo.addCamino(circulo41, circulo42, 10);
        grafo.addCamino(circulo37, circulo31, 15);
        grafo.addCamino(circulo32, circulo38, 20);
        grafo.addCamino(circulo39, circulo33, 20);
        grafo.addCamino(circulo41, circulo35, 10);
        grafo.addCamino(circulo48, circulo47, 15);
        grafo.addCamino(circulo47, circulo46, 15);
        grafo.addCamino(circulo46, circulo45, 20);
        grafo.addCamino(circulo47, circulo46, 15);
        grafo.addCamino(circulo45, circulo44, 10);
        grafo.addCamino(circulo44, circulo43, 10);
        grafo.addCamino(circulo38, circulo44, 20);
        grafo.addCamino(circulo45, circulo39, 10);
        grafo.addCamino(circulo40, circulo46, 10);
        grafo.addCamino(circulo42, circulo48, 20);
        grafo.addCamino(circulo43, circulo49, 15);
        grafo.addCamino(circulo44, circulo50, 10);
        grafo.addCamino(circulo51, circulo45, 15);
        grafo.addCamino(circulo49, circulo50, 5);
        grafo.addCamino(circulo50, circulo51, 20);

    }
}

package hangman;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;

import javafx.stage.Stage;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Korsn
 */
public class Main extends Application {

    
    Crtanje c;
    Group p = new Group();
    Scene scene = new Scene(p, 400, 400);
     TextInputDialog input = new TextInputDialog();
   

    @Override
    public void start(Stage primaryStage) throws Exception {
   
       c= new Crtanje();
      
        primaryStage.setResizable(false);
        p.getChildren().add(c);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HangMan");
        primaryStage.show();
        
       

      //  startGame();
    }

   

    public static void main(String[] args) {
        launch(args);
    }

}

package main;

import controller.MainController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage stage;
	public static Scene scene;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	stage=primaryStage;
    	VBox root= FXMLLoader.load(getClass().getResource("/fxml/Frame.fxml"));
    	scene=new Scene(root);
    	stage.setTitle("NoteBook");
    	stage.setScene(scene);
//        stage.setResizable(false);
        stage.getIcons().add(new Image("file:images/pencil.png"));
        stage.show();
        
 
    }
}

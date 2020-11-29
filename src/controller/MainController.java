package controller;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import controller.actions.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class MainController implements Initializable{
	public static File file=null;
	
	@FXML
    private TextField file_name_shower;
	
    @FXML
    private MenuItem copy_item;

    @FXML
    private Menu menu_file;

    @FXML
    private HBox under_box;

    @FXML
    private MenuItem clear_item;
    
    @FXML
    private TextField time_shower;

    @FXML
    private MenuItem savefile_item;

    @FXML
    private ComboBox<String> size_chooser;
    
    @FXML
    private MenuItem exit_item;

    @FXML
    private VBox MainFrame;

    @FXML
    private MenuItem newfile_item;

    @FXML
    private MenuItem paste_item;

    @FXML
    private Menu menu_edit;

    @FXML
    private TextArea edit_box;

    @FXML
    private Menu menu_help;

    @FXML
    private MenuItem about__item;
    
    @FXML
    private MenuBar menus;

    @FXML
    private MenuItem openfile_item;

    @FXML
    private MenuItem cut_item;
    
    @FXML
    private MenuItem selectall_item;

    @FXML
    void create_new_file(ActionEvent event) {
    	main.Main.stage.setTitle("New File");
    	edit_box.setText("");
    	file_name_shower.setText("");
    }

    @FXML
    void open_a_file(ActionEvent event) {
    	OpenFile.open_file(main.Main.stage, edit_box);
    	if (file!=null)
    	file_name_shower.setText(file.getName());
    }

    @FXML
    void save_a_file(ActionEvent event) {
    	try {
			SaveFile.save_file(main.Main.stage,edit_box.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void exit_program(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void select_all(ActionEvent event) {
    	edit_box.selectAll();
    }

    @FXML
    void copy(ActionEvent event) {
    	edit_box.copy();
    }

    @FXML
    void paste(ActionEvent event) {
    	edit_box.paste();
    }

    @FXML
    void cut(ActionEvent event) {
    	edit_box.cut();
    }

    @FXML
    void clear(ActionEvent event) {
    	edit_box.setText("");;
    }
     
    @FXML
    void show_about(ActionEvent event) {
    	Stage stage=new Stage();
    	BorderPane root=new BorderPane();
    	
    	WebView view=new WebView();
    	WebEngine engine=view.getEngine();
    	String url=MainController.class.getResource("/html/ReadMe.html").toExternalForm();
    	engine.load(url);
    	root.setLeft(view);
    	
    	stage.setScene(new Scene(root));
    	stage.setTitle("Intro");
    	stage.setWidth(360);
    	stage.setHeight(400);
    	stage.initOwner(Main.stage);
    	stage.initModality(Modality.WINDOW_MODAL);
    	stage.show();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new TimeShower(time_shower);
		main.Main.stage.setOnCloseRequest(e->{
			if (!edit_box.getText().equals("")) {
				e.consume();
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Do you want to save the file?");
				alert.setHeaderText("Do you want to save the file?");
				ButtonType yesBtn=new ButtonType("Save");
				ButtonType noBtn=new ButtonType("Don't Save");
				ButtonType cancleBtn=new ButtonType("Cancle",ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(yesBtn,noBtn,cancleBtn);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get()==yesBtn)
					try {
						SaveFile.save_file(main.Main.stage,edit_box.getText());System.exit(0);
					} catch (Exception e1) {	
						e1.printStackTrace();
					} 
				else if (result.get()==noBtn)System.exit(0);
				else alert.close();
			}else System.exit(0);
				
			});
		
		ObservableList<String> list=FXCollections.observableArrayList(
				"8",
				"10",
				"12",
				"14",
				"16",
				"18",
				"20",
				"32",
				"36",
				"45",
				"56",
				"72",
				"100");
		size_chooser.setItems(list);
		size_chooser.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->edit_box.setFont(Font.font(Double.parseDouble(newValue))));
	}
}




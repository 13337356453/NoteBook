package controller.actions;

import java.io.FileInputStream;
import controller.MainController;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class OpenFile {
	public static void open_file(Stage stage,TextArea area) {
		FileChooser chooser=new FileChooser();
		chooser.setTitle("Choose Your File");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),new ExtensionFilter("All File", "*.*"));
		MainController.file=chooser.showOpenDialog(stage);
		String content=null;
		if (MainController.file!=null) {
			Long length=MainController.file.length();
			String name=MainController.file.getName();
			byte[] filecontent=new byte[length.intValue()];
			try {
				FileInputStream in=new FileInputStream(MainController.file);
				in.read(filecontent);
				content=new String(filecontent,"UTF-8");
				in.close();
				main.Main.stage.setTitle(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		area.setText(content);
		
	} 
}

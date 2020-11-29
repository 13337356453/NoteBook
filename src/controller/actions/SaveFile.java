package controller.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import controller.MainController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class SaveFile {
	public static void save_file(Stage stage,String content) throws Exception {
		String title=stage.getTitle();
		BufferedWriter writer=null;
		FileChooser chooser=new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),new ExtensionFilter("All File", "*.*"));
		chooser.setTitle("Save");
		if (title.equals("NoteBook") || title.equals("New File")) {
			File f=chooser.showSaveDialog(stage);
			if (f!=null) {
				if (!f.exists())
					f.createNewFile();
				writer=new BufferedWriter(new FileWriter(f));
				writer.write(content);
				writer.close();	
			}
		} else {
			File f=MainController.file;
			writer=new BufferedWriter(new FileWriter(f));
			writer.write(content);
			writer.close();
		}
	}
}

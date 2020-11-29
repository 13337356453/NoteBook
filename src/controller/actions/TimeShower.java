package controller.actions;

import java.util.Date;
import javafx.scene.control.TextField;

public class TimeShower {
	String time=null;
	public TimeShower(TextField field) {
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					Date date=new Date();
					time=String.format("%tT", date);
					field.setText("          "+time);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
}

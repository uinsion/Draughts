import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class draughts extends Application {
	// overridden init method
	public void init() {
		//sp_mainlayout = new StackPane();
		//rc_reversi = new ReversiControl();
		
		//==================================
		
	}
	
	// overridden start method
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Draughts");
		primaryStage.setScene(new Scene(sp_mainlayout,800,800));
		primaryStage.show();
	}
	
	// overridden stop method
	public void stop() {
		
	}
	
	// entry point into our program for launching our javafx applicaton
	public static void main(String[] args) {
		launch(args);
	}
	
	// private fields for a stack pane and a reversi control
	private StackPane sp_mainlayout;
	private ReversiControl rc_reversi;
	
}

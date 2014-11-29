import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

//class definition for a custom reversi control
class DraughtsControl extends Control {
	// constructor for the class
	public DraughtsControl() {
		setSkin(new DraughtsControlSkin(this));
		db_board = new DraughtsBoard(); 
		getChildren().add(db_board);
		
		setOnMouseClicked(new EventHandler<MouseEvent>() { 
			@Override
			public void handle(MouseEvent event) {
				
			}
		});
	}
	
	// overridden version of the resize method
	@Override
	public void resize(double width, double height) {
		
		super.resize(width, height); 
		db_board.resize(width, height);
	}
	
	// private fields of a reversi board
	DraughtsBoard db_board;
}


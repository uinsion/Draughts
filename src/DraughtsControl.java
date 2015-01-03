import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

//class definition for a custom reversi control
class DraughtsControl extends Control {
	
	DraughtsBoard db_board;
	
	public DraughtsControl() {
		setSkin(new DraughtsControlSkin(this));
		db_board = new DraughtsBoard(); 
		getChildren().add(db_board);
		
		setOnMouseClicked(new EventHandler<MouseEvent>() { 
			@Override
			public void handle(MouseEvent event) {

				db_board.act(event.getX(), event.getY());	
			}
		});
		
		setOnKeyPressed(new EventHandler<KeyEvent>() { 
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE) db_board.resetGame();
			} 
		});
	}
	
	// overridden version of the resize method
	@Override
	public void resize(double width, double height) {
		
		super.resize(width, height); 
		db_board.resize(width, height);
	}
}


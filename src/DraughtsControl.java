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
				int piecenumber = db_board.getpiece(event.getX(), event.getY());
				
				if (piecenumber == db_board.current_player){ 
						db_board.select(event.getX(), event.getY());
						return;
				}
				
				if (db_board.can_move==true){
					db_board.move_piece(event.getX(), event.getY());
				}
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


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

public class DraughtsBoard {
	
	private Rectangle [][] Background;
	private double cell_width;
	private double cell_height;
	
	public DraughtsBoard (){
		Background = new Rectangle [8][8];
		
		createBackground();
	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		
		cell_width = width / 8.0;
		cell_height = height / 8.0;
		
	}
	
	public void createBackground(){	
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
			}
		}	
	}
}

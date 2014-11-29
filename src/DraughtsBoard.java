import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraughtsBoard extends Pane {
	
	private Rectangle [][] Background;
	private DraughtsPiece[][] render;
	private double cell_width;
	private double cell_height;
	
	public DraughtsBoard (){
		Background = new Rectangle [8][8];
		
	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		
		cell_width = width / 8.0;
		cell_height = height / 8.0;
		
		createBackground();
	}
	
	public void createBackground(){	
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Background[i][j] = new Rectangle();
				Background[i][j].setWidth(cell_width); Background[i][j].setHeight(cell_height);
				Background[i][j].relocate(i * cell_width, j * cell_height);
				
				if ((i+j) % 2== 0)
					Background[i][j].setFill(Color.BROWN);
				else
					Background[i][j].setFill(Color.BEIGE);
				
				this.getChildren().add(Background[i][j]);
			}
		}	
	}
}

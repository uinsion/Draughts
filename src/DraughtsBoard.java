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
		
		initialiseBackground();
		initialiseRender();
		resetGame();
	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		
		cell_width = width / 8.0;
		cell_height = height / 8.0;
		
		resizeBackground();
		pieceResizeRelocate();
	}
	
	public void initialiseBackground(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Background[i][j] = new Rectangle();
				
				if ((i+j) % 2== 0)
					Background[i][j].setFill(Color.GREEN);
				else
					Background[i][j].setFill(Color.WHITE);
				
				this.getChildren().add(Background[i][j]);
			}
		}	
	}
	
	public void resizeBackground(){	
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Background[i][j].setWidth(cell_width); Background[i][j].setHeight(cell_height);
				Background[i][j].relocate(i * cell_width, j * cell_height);
			}
		}	
	}
	
	private void resetGame(){
		resetRender();
		//RED
		render[0][0].setPiece(1);render[2][0].setPiece(1);render[4][0].setPiece(1);render[6][0].setPiece(1);
		render[1][1].setPiece(1);render[3][1].setPiece(1);render[5][1].setPiece(1);render[7][1].setPiece(1);
		render[0][2].setPiece(1);render[2][2].setPiece(1);render[4][2].setPiece(1);render[6][2].setPiece(1);
		
		//BLACK
		render[0][5].setPiece(2);render[2][5].setPiece(2);render[4][5].setPiece(2);render[6][5].setPiece(2);
		render[1][6].setPiece(2);render[3][6].setPiece(2);render[5][6].setPiece(2);render[7][6].setPiece(2);
		render[0][7].setPiece(2);render[2][7].setPiece(2);render[4][7].setPiece(2);render[6][7].setPiece(2);
		
	}
	
	private void resetRender() {
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				render[i][j].setPiece(0); 
	}
	
	private void initialiseRender() {

		render = new DraughtsPiece[8][8]; 
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) { 
				render[i][j] = new DraughtsPiece(0);
				this.getChildren().add(render[i][j]);
			}
	}
	
	private void pieceResizeRelocate() {
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				render[i][j].relocate(i * cell_width, j * cell_height);
				render[i][j].resize(cell_width, cell_height);
			}	
		}
	}
}

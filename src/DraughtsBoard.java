import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraughtsBoard extends Pane {
	
	private Rectangle [][] Background;
	private DraughtsPiece[][] render;
	private double cell_width;
	private double cell_height;
	public int current_player;
	int indexx;
	int indexy;
	DraughtsPiece selected_piece;
	public boolean can_move;
	
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
					Background[i][j].setFill(Color.BROWN);
				else
					Background[i][j].setFill(Color.BEIGE);
				
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
		render[1][0].setPiece(2);render[3][0].setPiece(2);render[5][0].setPiece(2);render[7][0].setPiece(2);
		render[0][1].setPiece(2);render[2][1].setPiece(2);render[4][1].setPiece(2);render[6][1].setPiece(2);
		render[1][2].setPiece(2);render[3][2].setPiece(2);render[5][2].setPiece(2);render[7][2].setPiece(2);
		
		//BLACK
		render[0][5].setPiece(1);render[2][5].setPiece(1);render[4][5].setPiece(1);render[6][5].setPiece(1);
		render[1][6].setPiece(1);render[3][6].setPiece(1);render[5][6].setPiece(1);render[7][6].setPiece(1);
		render[0][7].setPiece(1);render[2][7].setPiece(1);render[4][7].setPiece(1);render[6][7].setPiece(1);
		
		current_player = 1;
		
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
	public int getpiece(double x, double y){
		indexx = (int) (x / cell_width); 
		indexy = (int) (y / cell_height);
		
		int PieceIndex = render[indexx][indexy].getPiece();
		return PieceIndex;
	}
	
	public void select(double x, double y){
		indexx = (int) (x / cell_width); 
		indexy = (int) (y / cell_height);
		
		if (selected_piece!=null)
			selected_piece.Dehighlight();
		
		render[indexx][indexy].Highlight();
		
		selected_piece = render[indexx][indexy];
		
		can_move = true;
	}
	
	public void move_piece(double x, double y){
		indexx = (int) (x / cell_width); 
		indexy = (int) (y / cell_height);
		
		if (current_player == 1)
			if(render[indexx+1][indexy+1]!=selected_piece || render[indexx-1][indexy+1]!=selected_piece)
				return;
		
			render[indexx][indexy].setPiece(current_player);
			selected_piece.Dehighlight();
			selected_piece.setPiece(0);
			
			can_move = false;
				
		
	}
}

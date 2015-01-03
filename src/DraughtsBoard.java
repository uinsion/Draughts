import java.util.Arrays;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraughtsBoard extends Pane {
	
	private Rectangle [][] Background;
	private DraughtsPiece[][] render;
	private double cell_width;
	private double cell_height;
	public int current_player;
	public int opposing;
	int indexx;
	int indexy;
	DraughtsPiece selected_piece;
	int selectedxi;
	int selectedyi;
	int jumpoverxi;
	int jumpoveryi;
	public boolean can_move;
	int[][] possible_moves;
	boolean[][] must_jump;
	boolean jump_selecter;
	int first_x_surr;
	int first_y_surr;
	int second_x_surr;
	int second_y_surr;
	
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
	
	public void resetGame(){
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
		opposing = 2;
		
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
	
	private void swapPlayers() {
		int tempPlayer = current_player;
		current_player = opposing;
		opposing = tempPlayer;
	}
	
	public int getPiece(int x, int y){
		
		int PieceIndex = render[x][y].getPiece();
		return PieceIndex;
	}
	
	public void act (double x, double y){
		indexx = (int) (x / cell_width); 
		indexy = (int) (y / cell_height);
		
		int piecenumber = getPiece(indexx, indexy);
		
		if (piecenumber == current_player){ 
			if (jump_selecter==true){
				if (must_jump[indexx][indexy]==false)
					return;
			}
			select(indexx, indexy);
			return;
		}
		
		if (can_move == true){
			move (indexx,indexy);
		}
	}
	
	public void select(int x, int y){
		
		if (selected_piece!=null){
			selected_piece.Dehighlight();
		}	
		
		render[x][y].Highlight();
		
		selected_piece = render[x][y];
		selectedxi = x;
		selectedyi = y;
		
		check_move_possibilities(x, y);
		
		can_move = true;
	}
	
	public void check_move_possibilities(int x, int y){
		possible_moves = new int[8][8];
	
		for (int i=-1;i<2;i=i+2){
			if ((current_player==1 && i==-1) || (current_player==2 && i==1)){
				for (int j=-1;j<2;j=j+2){
					
					first_x_surr = x+j;
					second_x_surr = x+2*j;
					first_y_surr = y+i;
					second_y_surr = y+2*i;
					
					if (first_x_surr>=0 && first_x_surr<8 && first_y_surr>=0 && first_y_surr<8){
					
						if (render[first_x_surr][first_y_surr].getPiece()==0){
							possible_moves[first_x_surr][first_y_surr]=1;
							continue;
						}
					}
					else
						continue;
					
					if (second_x_surr>=0 && second_x_surr<8 && second_y_surr>=0 && second_y_surr<8){
						if (render[first_x_surr][first_y_surr].getPiece()==opposing && render[second_x_surr][second_y_surr].getPiece()==0)
							possible_moves[second_x_surr][second_y_surr]=2;
					}
				}
			}
		}
	}
		
	
	public void has_to_jump (){
		must_jump = new boolean[8][8];
		jump_selecter = false;
		
		for (int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				
				if (render[i][j].getPiece()==current_player){
					for (int k=-1;k<2;k=k+2){
						if ((current_player==1 && k==-1) || (current_player==2 && k==1)){
							for (int l=-1;l<2;l=l+2){
								
								first_x_surr = i+l;
								second_x_surr = i+2*l;
								first_y_surr = j+k;
								second_y_surr = j+2*k;
								
								if ( second_x_surr>=0 && second_x_surr<8 && second_y_surr>=0 && second_y_surr<8){
								
									if (render[first_x_surr][first_y_surr].getPiece()==opposing && render[second_x_surr][second_y_surr].getPiece()==0){
										must_jump[i][j]=true;
										jump_selecter = true;									
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	

	public void move (int x, int y){
		
		if (possible_moves[x][y]==1){
			render[x][y].setPiece(current_player);
			selected_piece.setPiece(0);
		}
		else if (possible_moves[x][y]==2)
			jump(x, y);
		else
			return;
		
		selected_piece.Dehighlight();
		selected_piece = null;
		can_move = false;
		swapPlayers();
		has_to_jump();
		
	}
	
	public void jump (int x, int y){
		
		render[x][y].setPiece(current_player);
		selected_piece.setPiece(0);
		
		jumpoverxi = selectedxi+((x-selectedxi)/2);
		jumpoveryi = selectedyi+((y-selectedyi)/2);	
		render[jumpoverxi][jumpoveryi].setPiece(0);
		
		
	}
}

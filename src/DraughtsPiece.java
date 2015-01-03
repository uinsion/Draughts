import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.effect.Bloom;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

//class definition for a reversi piece
class DraughtsPiece extends Group {
	
	private Ellipse piece;
	private Translate t;
	private int player;	
	
	public DraughtsPiece(int player) {
		this.player = player;
		t = new Translate();
		
		piece = new Ellipse();
		getChildren().addAll(piece);
		piece.getTransforms().add(t);
		
		piece.setFill(Color.TRANSPARENT);

	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width, height); 
		
		piece.setCenterX(width / 2); piece.setCenterY(height / 2);
		piece.setRadiusX(width / 2); piece.setRadiusY(height / 2);

	}
	
	@Override
	public void relocate(double x, double y) {
		super.relocate(x, y); 
		t.setX(x); t.setY(y);
	}
	
	public void setPiece(final int type) {
		
		switch (type){
		case 0: piece.setFill(Color.TRANSPARENT);
				break;
		case 1: piece.setFill(Color.GREEN);
				break;
		case 2: piece.setFill(Color.DARKMAGENTA);
			break;
		}
			
	}
	
	public int getPiece(){
		Color c = (Color) piece.getFill();
		int pieceindex;
		
		if (c == Color.TRANSPARENT){
			pieceindex = 0;
		} 
		else if (c == Color.GREEN){
			pieceindex = 1;
		}
		else {
			pieceindex = 2;
		}
		return pieceindex;	
	}
	
	public void Highlight(){
		Bloom bloom = new Bloom();
		bloom.setThreshold(0.01);
		
		piece.setEffect(bloom);
	}
	
	//deletes the highlight
	public void Dehighlight(){	
		piece.setEffect(null);
	}

	
}

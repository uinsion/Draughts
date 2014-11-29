import javafx.scene.control.Skin; 
import javafx.scene.control.SkinBase;

//class definition for a skin for the reversi control
//NOTE: to keep JavaFX happy we dont use the skin here
class DraughtsControlSkin extends SkinBase<DraughtsControl> implements Skin<DraughtsControl> {
	// default constructor for the class
	public DraughtsControlSkin(DraughtsControl rc) {
		super(rc);
	}
}

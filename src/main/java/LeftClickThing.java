import java.awt.Color;


public class LeftClickThing implements ThingBehavior {

    public Color getColor() {
	// TODO Auto-generated method stub
	return Color.RED;
    }
    
    public Color getRibbonColor() {
	// TODO Auto-generated method stub
	return Color.YELLOW;
    }

    public boolean isClickEnough() {
	// TODO Auto-generated method stub
	return true;
    }

    public boolean isRightClickEnough() {
	// TODO Auto-generated method stub
	return false;
    }

}

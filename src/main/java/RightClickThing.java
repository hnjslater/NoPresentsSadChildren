import java.awt.Color;


public class RightClickThing implements ThingBehavior {

    public Color getColor() {
	return Color.GREEN;
    }
    public Color getRibbonColor() {
	// TODO Auto-generated method stub
	return Color.BLUE;
    }
    public boolean isClickEnough() {
	return false;
    }

    public boolean isRightClickEnough() {
	return true;
    }

}

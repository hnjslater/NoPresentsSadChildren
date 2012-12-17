import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Child {
    Image normal;
    Image happy;
    int x;
    int presents;
    Game game;
    
    // ooooo magic numbers...
    static int[][] present_positions = { {16,670}, {38,670}, {60,670}, {29,648}, {51, 648}, {38,626}};
    List<Color> wrapping_colors;
    List<Color> ribbon_colors;
    
    public boolean finished() {
	return this.presents > 5;
    }
    
    public Child(Game game, int x) {
	this.game = game;
	this.x = x;
	this.wrapping_colors = new ArrayList<Color>();
	this.ribbon_colors = new ArrayList<Color>();
    }

    public void paint(Graphics g) {
	if (normal == null) {
	    java.io.InputStream is1 = getClass().getResourceAsStream("child1.png");
	    java.io.InputStream is2 = getClass().getResourceAsStream("child1-happy.png");
	    try {
		normal = ImageIO.read(is1);
		happy = ImageIO.read(is2);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	}
	if (finished())
	    g.drawImage(happy, x, 670-93, null);
	else {
	    if (Math.random() > 0.8)
		g.drawImage(normal, x, 670-103, null);
	    else
		g.drawImage(normal, x, 670-93, null);
	}
	for (int i = 0; i < this.presents && i  < 6; i++) {
	    Thing.paintPresent(g, false,  ribbon_colors.get(i), wrapping_colors.get(i), x+present_positions[i][0], present_positions[i][1], 20, 20);
	}
	
    }
    
    public void receive(Thing t) {
	this.presents++;
	wrapping_colors.add(t.getBehavior().getColor());
	ribbon_colors.add(t.getBehavior().getRibbonColor());
    }
}

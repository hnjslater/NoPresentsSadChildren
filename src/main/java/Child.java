import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Child {
    Image normal;
    Image happy;
    int x;
    int prizes;
    int presents;
    Game game;
    
    public boolean finished() {
	return this.presents > 5;
    }
    
    public Child(Game game, int x) {
	this.game = game;
	this.x = x;
	this.prizes = 0;
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
    }
    
    public void receive(Thing t) {
	this.presents++;
    }
}

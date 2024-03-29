import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Thing {
    public int x,y,w,h;

    private boolean alive;
    private boolean clicked;
    private Rectangle rect;
    private Game game;
    private ThingBehavior behavior;

    public Thing(Game game) {
	this.game = game;
	reset();
    }

    public void paint(Graphics g) {
	if (clicked)
	    this.kill();
	y+=10;
	rect.y = y;

	paintPresent(g, clicked, behavior.getRibbonColor(), behavior.getColor(), x, y, w, h);
    }
    
    static void paintPresent(Graphics g, boolean clicked, Color ribbonColor, Color wrappingColor, int x, int y, int w, int h) {
	if (!clicked)
	    g.setColor(wrappingColor);
	else
	    g.setColor(Color.BLACK);
	g.fillRect(x,y,w,h);

	g.setColor(ribbonColor);
	int r = 20;
	if (h <= 20 || w <= 20)
	    r = 6;
	if (h < 40 || w < 40) 
	    r= 10;
	
	
	int rl = w/2 -r/2;
	int rt = h/2 -r/2;
	g.fillRect(x, y+rt, w, r);
	g.fillRect(x+rl, y, r, h);	
    }

    public void give(Child c) {
	this.kill();
	c.receive(this);
    }

    public void leftClick(int x, int y) {
	if (rect.contains(x,y) && behavior.isClickEnough())
	    clicked = true;

    }
    public void rightClick(int x, int y) {
	if (rect.contains(x,y) && behavior.isRightClickEnough())
	    clicked = true;

    }

    public boolean isAlive() {
	return alive;
    }

    public void kill() {
	alive = false;
    }

    public void reset() {
	w = (int)(Math.random() * 80.0) + 30;
	h = (int)(Math.random() * 80.0) + 30;
	do {
	    x = (int)(Math.random() * (1000.0-w));
	} while (game.nearest(x).finished() && game.living_children.size() > 0);
	y = 0;
	rect = new Rectangle(x,y,w,h);
	alive = true;
	clicked = false;
	behavior = game.getRandomBehaviour();
    }

    public ThingBehavior getBehavior() {
	return this.behavior;
    }

}


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class AppletRunner extends Applet implements MouseListener{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Canvas c = new Canvas();
    Game game = new Game();
    Image bg, win, lose;
    int frames = 0;
    public AppletRunner() {
	c.setMinimumSize(new Dimension(1000, 700));
	c.setPreferredSize(new Dimension(1000, 700));
	this.add(c);
	c.addMouseListener(this);
    }
    public void init() {
	
	java.io.InputStream is1 = getClass().getResourceAsStream("background.png");
	java.io.InputStream is2 = getClass().getResourceAsStream("fail.png");
	java.io.InputStream is3 = getClass().getResourceAsStream("win.png");
	try {
	    bg = ImageIO.read(is1);
	    lose = ImageIO.read(is2);
	    win = ImageIO.read(is3);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    @Override
    public void start() {
	c.createBufferStrategy(2);
	javax.swing.Timer t = new javax.swing.Timer(100, new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frames++;
		BufferStrategy bc = c.getBufferStrategy();
		Graphics g = bc.getDrawGraphics();
		g.drawImage(bg, 0, 0, null);
		if (frames > 600 && !game.living_children.isEmpty()) {
		    g.drawImage(win, 0, 0, null);

		}
		
		
		else if (!game.living_children.isEmpty()) {
		    if (frames % 50 == 0) 
			game.things.add(new Thing(game));
		    if (frames % 300 == 0)
			game.nextLevel();
		    for (Thing t : game.things) {
			if (t.y > (700-t.h)) {
			    t.give(game.nearest(t.x));
			    newAudioClip(getClass().getResource("yay.au")).play();
			}
			t.paint(g);
		    }

		    Iterator<Child> iter = game.living_children.iterator();
		    while (iter.hasNext()) {
			Child c = iter.next();
			if (c.finished()) {
			    iter.remove();
			    game.dead_children.add(c);
			}
			else {
			    c.paint(g);
			}
		    }

		    for (Child c: game.dead_children) {
			c.paint(g);
		    }


		    for (Thing t: game.things) {
			if (!t.isAlive())
			    t.reset();
		    }
		}
		else {
		    g.drawImage(lose, 0, 0, null);
		}

		bc.show();
		g.dispose();

	    }
	});

	t.start();
    }
    public void mouseClicked(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }
    public void mousePressed(MouseEvent e) {
	for (Thing t : game.things) {
	    if (e.getButton() == MouseEvent.BUTTON1)
		t.leftClick(e.getX(), e.getY());
	    else if (e.getButton() == MouseEvent.BUTTON3)
		t.rightClick(e.getX(), e.getY());
	}

    }
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub

    }


}


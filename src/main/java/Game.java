import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {
    public List<Thing> things;
    public List<Child> dead_children;
    public List<Child> living_children;
    public List<Child> children;
    public List<ThingBehavior> behaviors;
    private int level = 0;
    private Random randomGenerator;
    public Game() {
	this.things = new ArrayList<Thing>();
	this.children = new ArrayList<Child>();
	this.living_children = new ArrayList<Child>();
	this.dead_children = new ArrayList<Child>();
	this.behaviors = new ArrayList<ThingBehavior>();
	this.randomGenerator = new Random();
	this.behaviors.add(new LeftClickThing());

	for (int i = 1; i <= 5; i++) {   
	    living_children.add(new Child(this,i*200-150));
	}
	children.addAll(living_children);
	for (int i = 0; i < 5; i++) {
	    things.add(new Thing(this));
	}
    }
    Child nearest(int x) {
	return children.get(x/200);
    }
    ThingBehavior getRandomBehaviour() {
	if (behaviors.size() == 0)
	    return behaviors.get(0);
	else
	    return behaviors.get((int)(randomGenerator.nextInt(behaviors.size())));
    }
    void nextLevel() {
	if (level == 0) {
	    level++;
	    behaviors.add(new RightClickThing());
	}
    }
}

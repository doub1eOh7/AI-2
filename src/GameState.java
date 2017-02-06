import java.lang.Math;
import java.util.ArrayList;
public class GameState {
	private double cost = 0.0;
	private double h = 0.0;
	private GameState parent;
	private int x, y;
	public static Model model;
	private static double maxSpeed = 0;
	
	GameState()
	{
		
	}
	
	public static void initializeMaxSpeed()
	{
		for(int x = 0; x < Model.XMAX; x+=10)
		{
			for(int y = 0; y < Model.YMAX; y+=10)
			{
				if(model.getTravelSpeed(x, y) > maxSpeed)
					maxSpeed = model.getTravelSpeed(x, y);
			}
		}
	}
	
	public static void initializeModel(Model newModel)
	{
		model = newModel;
	}
	
	@Override public boolean equals(Object other)
	{
		boolean equal = false;
		if(other instanceof GameState)
		{
			GameState state = (GameState)other;
			if(this.x == state.getX() && this.y == state.getY())
				equal = true;
		}
		return equal;
	}
	
	GameState(double cost, GameState parent, float x, float y)
	{
		this.cost = cost;
		this.parent = parent;
		this.x = Math.round(x/10)*10;
		this.y = Math.round(y/10)*10;
	}
	
	GameState(GameState oldState)
	{
		this.cost = oldState.getCost();
		this.parent = oldState.getParent();
		this.x = Math.round(oldState.getX()/10)*10;
		this.y = Math.round(oldState.getY()/10)*10;
	}
	
	public boolean isValid()
	{
		boolean flag = true;
		if(this.getX() > Model.XMAX)
			flag = false;
		if(this.getX() < 0)
			flag = false;
		if(this.getY() > Model.YMAX)
			flag = false;
		if(this.getY() < 0)
			flag = false;
		return flag;
	}
	
	public GameState transition(int dx, int dy)
	{
		int newX = this.x + dx;
		int newY = this.y + dy;
		double cost = 0;
		if(newX >= 0 && newY >= 0 && newX <= Model.XMAX && newY <= Model.YMAX)
			cost = this.computeCost(newX, newY);
		GameState nextState = new GameState(this.getCost() + cost, this, newX, newY);
		
		return nextState;
	}
	
	public ArrayList<GameState> allTransitions()
	{
		ArrayList<GameState> nextLocations = new ArrayList<GameState>();
		GameState right = this.transition(10, 0);
		GameState left = this.transition(-10, 0);
		GameState up = this.transition(0, -10);
		GameState down = this.transition(0, 10);
		GameState upright = this.transition(10, -10);
		GameState upleft = this.transition(-10, -10);
		GameState downright = this.transition(10, 10);
		GameState downleft = this.transition(-10, 10);
		if(right.isValid())
			nextLocations.add(right);
		if(left.isValid())
			nextLocations.add(left);
		if(up.isValid())
			nextLocations.add(up);
		if(down.isValid())
			nextLocations.add(down);
		if(upright.isValid())
			nextLocations.add(upright);
		if(upleft.isValid())
			nextLocations.add(upleft);
		if(downright.isValid())
			nextLocations.add(downright);
		if(downleft.isValid())
			nextLocations.add(downleft);
		
		return nextLocations;

	}
	
	//Compute Cost
	public double computeCost(float x, float y)
	{
		double newCost = 0;
		//System.out.println("Loc: " + x + ", " + y);
		double speed = model.getTravelSpeed(x, y);
		double dx = this.getX() - x;
		double dy = this.getY() - y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		newCost = distance / speed;
		return newCost;
	}
	
	//Compute Hueristic
	public double computeH(float x, float y)
	{
		double newH = 0;
		double dx = this.getX() - model.getDestinationX();
		double dy = this.getY() - model.getDestinationY();
		double distance = Math.sqrt(dx*dx + dy*dy);
		newH = distance / maxSpeed;
		return newH;
	}
	
	public void setH(double h)
	{
		this.h = h;
	}
	
	public double getH()
	{
		return this.h;
	}

	//Check LocationEquals
	public boolean locationEquals(GameState other)
	{
		//int xx = (int)(this.x * 0.1f);
		//int yy = (int)(this.y * 0.1f);
		//int otherxx = (int)(other.getX() * 0.1f);
		//int otheryy = (int)(other.getY() * 0.1f);
		if(this.x == other.getX() && this.y == other.getY())
		{
			return true;
		}
		return false;
	}
	
	//Getters
	public double getCost()
	{
		return this.cost;
	}
	
	public GameState getParent()
	{
		return this.parent;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	//Setters
	public void setCost(double cost)
	{
		this.cost = cost;
	}
	
	public void setParent(GameState parent)
	{
		this.parent = parent;
	}
	
	public void setX(float X)
	{
		this.x = Math.round(X/10)*10;
	}
	
	public void setY(float Y)
	{
		this.y = Math.round(Y/10)*10;
	}
	
	public void prettyPrint()
	{
		System.out.println("---------------------------");
		System.out.println(this);
		System.out.println("cost: " + this.cost);
		System.out.println("parent: " + this.parent);
		System.out.println("location: " + this.x + ", " + this.y);
		System.out.println("---------------------------");
	}
	
}

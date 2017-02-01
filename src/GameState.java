
public class GameState {
	private double cost = 0.0;
	private GameState parent;
	private Location location;
	public static Model model;
	
	GameState()
	{
		
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
			if(this.location.equals(((GameState) other).getLocation()))
				equal = true;
		}
		return equal;
	}
	
	GameState(double cost, GameState parent, Location location)
	{
		this.cost = cost;
		this.parent = parent;
		this.location = location;
	}
	
	//Compute Cost
	public double computeCost(Location nextLocation)
	{
		double newCost = 0;
		Model tempModel = new Model(model);
		newCost = tempModel.getTravelSpeed(nextLocation.getX(), nextLocation.getY());
		newCost = 1 / newCost;
		return newCost;
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
	
	public Location getLocation()
	{
		return this.location;
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
	
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	public void prettyPrint()
	{
		System.out.println("---------------------------");
		System.out.println(this);
		System.out.println("cost: " + this.cost);
		System.out.println("parent: " + this.parent);
		System.out.println("location: " + this.getLocation().getX() + ", " + this.getLocation().getY());
		System.out.println("---------------------------");
	}
	
}

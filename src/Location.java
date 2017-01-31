import java.util.ArrayList;

public class Location {
	private int x = 0;
	private int y = 0;
	
	Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	Location(Location s)
	{
		this.x = s.getX();
		this.y = s.getY();
	}
	
	public boolean equals(Location other)
	{
		boolean equal = true;
		if(other.x != this.x)
			equal = false;
		else if(other.y != this.y)
			equal = false;
		
		return equal;
	}
	
	public Location transition(int dx, int dy)
	{
		int newX = this.x + dx;
		int newY = this.y + dy;
		Location newLoc = new Location(newX, newY);
		
		return newLoc;
	}
	
	public ArrayList<Location> allTransitions()
	{
		ArrayList<Location> nextLocations = new ArrayList<Location>();
		Location right = this.transition(1, 0);
		Location left = this.transition(-1, 0);
		Location up = this.transition(0, -1);
		Location down = this.transition(0, 1);
		nextLocations.add(right);
		nextLocations.add(left);
		nextLocations.add(up);
		nextLocations.add(down);
		
		return nextLocations;

	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int[] getCoordinates()
	{
		int[] coordinates = new int[2];
		coordinates[0] = this.x;
		coordinates[1] = this.y;
		return coordinates;
	}
	
	public boolean isEqual(Location location2)
	{
		int location2X = location2.getX();
		int location2Y = location2.getY();
		
		boolean equals = true;
		if(location2X != this.x)
			equals = false;
		if(location2Y != this.y)
			equals = false;
		return equals;
	}

}
